FROM java:8u66-jdk
MAINTAINER cy
ADD target/zuul-gateway-1.0-SNAPSHOT.jar /opt/zuul-gateway-1.0-SNAPSHOT.jar
EXPOSE 9004
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/zuul-gateway-1.0-SNAPSHOT.jar