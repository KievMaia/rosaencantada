FROM alpine:3.17
RUN apk add --no-cache openjdk17-jre
COPY target/rosaencantada.jar rosaencantada.jar