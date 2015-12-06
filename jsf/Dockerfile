FROM java:openjdk-8-jdk
ADD target/JaxRS-swarm.jar /opt/JaxRS-swarm.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/JaxRS-swarm.jar"]