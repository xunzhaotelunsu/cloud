FROM java:8u66-jdk
MAINTAINER cy
ADD target/config-server-1.0-SNAPSHOT.jar /opt/config-server-1.0-SNAPSHOT.jar
EXPOSE 9009
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/config-server-1.0-SNAPSHOT.jar