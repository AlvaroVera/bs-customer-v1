ARG MS_NAME=management
FROM openjdk:11
ARG MS_NAME
ARG TARGET_FOLDER=/target
ARG PORT_APP=8080
WORKDIR /app
COPY $TARGET_FOLDER/bs-customers-v1-0.0.1-SNAPSHOT.jar .
ENV PORT $PORT_APP
EXPOSE $PORT
CMD ["java", "-jar", "bs-customers-v1-0.0.1-SNAPSHOT.jar"]
