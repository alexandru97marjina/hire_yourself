ARG NODE_VERSION=10
FROM node:$NODE_VERSION

# add more arguments from CI to the image so that `$ env` should reveal more info

ARG CI_BUILD_ID
ARG CI_BUILD_REF
ARG CI_REGISTRY_IMAGE
ARG CI_BUILD_TIME
ARG NG_CLI_VERSION=latest

ENV CI_BUILD_ID=$CI_BUILD_ID CI_BUILD_REF=$CI_BUILD_REF CI_REGISTRY_IMAGE=$CI_REGISTRY_IMAGE \
    CI_BUILD_TIME=$CI_BUILD_TIME \
    NG_CLI_VERSION=$NG_CLI_VERSION

RUN yarn global add @angular/cli@$NG_CLI_VERSION && rm -rf $(yarn cache dir)

# Create a directory where our app will be placed
RUN mkdir -p /var/www/app

# Change directory so that our commands run inside this new directory
WORKDIR /var/www/app

# Expose the port the app runs in
EXPOSE 4200

COPY start.sh /usr/local/bin

# Serve the app
CMD ["/usr/local/bin/start.sh"]
