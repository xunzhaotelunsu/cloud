FROM java:8u66-jdk
MAINTAINER cy
ADD target/oauth2-server-1.0-SNAPSHOT.jar /opt/oauth2-server-1.0-SNAPSHOT.jar
EXPOSE 9010
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/oauth2-server-1.0-SNAPSHOT.jar