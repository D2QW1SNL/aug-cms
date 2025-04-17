FROM registry.cn-hangzhou.aliyuncs.com/ah_champion/openjdk:8-jdk
MAINTAINER rjy@rjy.com

RUN mkdir -p /java
ADD ./aug-cms-web/target/aug-cms-web-0.0.1-SNAPSHOT.jar /java/cms.jar

ENV TZ="Asia/Shanghai"
ENV JVM_ARGS=""
ENV JVM_OPTS="-Xms512M -Xmx512M -Xmn256M -XX:ParallelGCThreads=2"

WORKDIR /java/

ENTRYPOINT ["sh","-c","java $JVM_OPTS $JVM_ARG -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8900 -Djava.security.egd=file:/dev/./urandom -server -jar /java/cms.jar"]
VOLUME /tmp