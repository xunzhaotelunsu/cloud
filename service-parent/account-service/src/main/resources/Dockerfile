FROM java:8u66-jdk
MAINTAINER cy
ADD target/account-service-1.0-SNAPSHOT.jar /opt/account-service-1.0-SNAPSHOT.jar
EXPOSE 9011
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/account-service-1.0-SNAPSHOT.jar