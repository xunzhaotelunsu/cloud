FROM java:8u66-jdk
MAINTAINER cy
ADD target/account-1.0-SNAPSHOT.jar /opt/activiti-1.0-SNAPSHOT.jar
EXPOSE 9014
CMD java -Djava.security.egd=file:/dev/./urandom -jar opt/activiti-1.0-SNAPSHOT.jar