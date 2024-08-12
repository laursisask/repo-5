package run

import (
	"bucket-simple-server/internal/config"
	"bucket-simple-server/internal/globals"
	"bucket-simple-server/internal/processor"
	"fmt"
	"log"
	"net/http"
	"strconv"
	"time"

	"github.com/spf13/cobra"
)

const (
	descriptionShort = `Execute synchronization process`

	descriptionLong = `
	Run execute synchronization process`

	//

	//
	ConfigFlagErrorMessage          = "impossible to get flag --config: %s"
	ConfigNotParsedErrorMessage     = "impossible to parse config file: %s"
	LogLevelFlagErrorMessage        = "impossible to get flag --log-level: %s"
	DisableTraceFlagErrorMessage    = "impossible to get flag --disable-trace: %s"
	SyncTimeFlagErrorMessage        = "impossible to get flag --sync-time: %s"
	UnableParseDurationErrorMessage = "unable to parse duration: %s"
)

func NewCommand() *cobra.Command {
	cmd := &cobra.Command{
		Use:                   "run",
		DisableFlagsInUseLine: true,
		Short:                 descriptionShort,
		Long:                  descriptionLong,

		Run: RunCommand,
	}

	//
	cmd.Flags().String("log-level", "info", "Verbosity level for logs")
	cmd.Flags().Bool("disable-trace", true, "Disable showing traces in logs")
	cmd.Flags().String("config", "bucket-simple-server.yaml", "Path to the YAML config file")

	return cmd
}

// RunCommand TODO
// Ref: https://pkg.go.dev/github.com/spf13/pflag#StringSlice
func RunCommand(cmd *cobra.Command, args []string) {

	configPath, err := cmd.Flags().GetString("config")
	if err != nil {
		log.Fatalf(ConfigFlagErrorMessage, err)
	}

	// Init the logger and store the level into the context
	logLevelFlag, err := cmd.Flags().GetString("log-level")
	if err != nil {
		log.Fatalf(LogLevelFlagErrorMessage, err)
	}
	globals.Application.LogLevel = logLevelFlag

	disableTraceFlag, err := cmd.Flags().GetBool("disable-trace")
	if err != nil {
		log.Fatalf(DisableTraceFlagErrorMessage, err)
	}

	err = globals.SetLogger(logLevelFlag, disableTraceFlag)
	if err != nil {
		log.Fatal(err)
	}

	/////////////////////////////
	// EXECUTION FLOW RELATED
	/////////////////////////////

	globals.Application.Logger.Infof("starting BucketSimpleServer. Getting ready.")

	// Parse and store the config
	configContent, err := config.ReadFile(configPath)
	if err != nil {
		globals.Application.Logger.Fatalf(fmt.Sprintf(ConfigNotParsedErrorMessage, err))
	}
	globals.Application.Config = configContent

	//
	for {

		// Create the processor to handle requests
		processorObj, err := processor.NewProcessor()
		if err != nil {
			globals.Application.Logger.Infof("Failed to create a processor. Reason: ", err)
		}

		// Create the webserver to serve the requests
		mux := http.NewServeMux()
		mux.HandleFunc("/", processorObj.HandleRequest)

		//
		webserverListenStr := configContent.Spec.Webserver.Listener.Host + ":" +
			strconv.Itoa(configContent.Spec.Webserver.Listener.Port)

		globals.Application.Logger.Infof("Starting webserver on %s", webserverListenStr)

		err = http.ListenAndServe(webserverListenStr, mux)
		if err != nil {
			globals.Application.Logger.Infof("Server failed. Reason: ", err)
		}

		globals.Application.Logger.Infof("Server will be restarted in some moments")
		time.Sleep(5 * time.Second)
	}
}
