FROM java:8u66-jdk
MAINTAINER cy
ADD target/zipkin-server-1.0-SNAPSHOT.jar /opt/zipkin-server-1.0-SNAPSHOT.jar
EXPOSE 9005
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/zipkin-server-1.0-SNAPSHOT.jar