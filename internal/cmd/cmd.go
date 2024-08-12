package cmd

import (
	"strings"

	"github.com/spf13/cobra"

	"bucket-simple-server/internal/cmd/run"
	"bucket-simple-server/internal/cmd/version"
)

const (
	descriptionShort = `A daemon for Kubernetes to kill target resources under user-defined conditions`
	descriptionLong  = `
	A daemon for Kubernetes to kill target resources under user-defined conditions.
	Conditions are so powerful that they can be defined using Helm template.
	`
)

func NewRootCommand(name string) *cobra.Command {
	c := &cobra.Command{
		Use:   name,
		Short: descriptionShort,
		Long:  strings.ReplaceAll(descriptionLong, "\t", ""),
	}

	c.AddCommand(
		version.NewCommand(),
		run.NewCommand(),
	)

	return c
}
