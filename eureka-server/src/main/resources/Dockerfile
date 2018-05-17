FROM java:8u66-jdk
MAINTAINER cy
ADD target/eureka-server-1.0-SNAPSHOT.jar /opt/eureka-server-1.0-SNAPSHOT.jar
EXPOSE 9000
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/eureka-server-1.0-SNAPSHOT.jar