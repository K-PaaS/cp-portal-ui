FROM gradle:8.10.0-jdk17 as builder
WORKDIR /build
COPY . /build
RUN gradle clean build -x test --parallel

# APP
FROM registry.k-paas.org/library/openjdk:17-jdk-alpine
ARG CI_PROJECT_DIR=/home/1000
ARG JAR_FILE=container-platform-ui.war

RUN apk upgrade --no-cache
RUN addgroup -S 1000 && adduser -S 1000 -G 1000
RUN mkdir -p ${CI_PROJECT_DIR}
COPY --from=builder /build/build/libs/${JAR_FILE} ${CI_PROJECT_DIR}/${JAR_FILE}
RUN chown -R 1000:1000 ${CI_PROJECT_DIR}

## import self signed certificate with keytool
COPY 180.210.83.161.nip.io.crt /tmp/180.210.83.161.nip.io.crt
RUN keytool -import -trustcacerts -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt -alias cpcert -file /tmp/180.210.83.161.nip.io.crt
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/home/1000/container-platform-ui.war"]
