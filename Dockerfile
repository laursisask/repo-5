FROM jsii/superchain:1-buster-slim-node16

USER root
RUN mkdir /cdk

COPY ./requirements.txt /cdk/
COPY ./entrypoint.sh /usr/local/bin/

WORKDIR /cdk
RUN npm i -g aws-cdk &&\
    ln -s /usr/bin/pip3 /usr/bin/pip &&\
    pip install -r requirements.txt &&\
    pip install awscli >=1.18.140

ENTRYPOINT ["entrypoint.sh"]
