FROM openjdk:11
EXPOSE 8089
ADD target/gestion-station-ski-1.0.jar app/gestion-station-ski-1.0.jar
ENTRYPOINT ["java","-jar","app/gestion-station-ski-1.0.jar"]