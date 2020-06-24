FROM openjdk:8-jre-alpine
#EXPOSE 8090 7082
COPY ./target/ms-indices-economicos-1.0.0.jar .
ENTRYPOINT ["/bin/sh","-c","java -jar ./ms-indices-economicos-1.0.0.jar"]