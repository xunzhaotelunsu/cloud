FROM java:8u66-jdk
MAINTAINER cy
ADD target/admin-server-1.0-SNAPSHOT.jar /opt/admin-server-1.0-SNAPSHOT.jar
EXPOSE 8888
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/admin-server-1.0-SNAPSHOT.jar