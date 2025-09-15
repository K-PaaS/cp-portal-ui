#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=build/libs/*.war
#RUN addgroup -S 1000 && adduser -S 1000 -G 1000
#RUN mkdir -p /home/1000
#COPY ${JAR_FILE} /home/1000/container-platform-ui.war
#RUN chown -R 1000:1000 /home/1000
#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/home/1000/container-platform-ui.war"]

FROM gradle:8.10.0-jdk17 as builder
WORKDIR /build
COPY . /build
RUN gradle clean build -x test --parallel

FROM openjdk:17-jdk-alpine
ARG UI_PROJECT_DIR=/home/1000
ARG JAR_FILE=container-platform-ui.war

RUN apk upgrade --no-cache
RUN addgroup -S 1000 && adduser -S 1000 -G 1000
RUN mkdir -p ${UI_PROJECT_DIR}
COPY --from=builder /build/build/libs/${JAR_FILE} ${UI_PROJECT_DIR}/${JAR_FILE}
RUN chown -R 1000:1000 ${UI_PROJECT_DIR}

COPY cpcert.crt /tmp/cpcert.crt
RUN keytool -import -trustcacerts -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt -alias cpcert -file /tmp/cpcert.crt
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/home/1000/container-platform-ui.war"]
