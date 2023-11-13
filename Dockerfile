FROM openjdk:11
ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
EXPOSE 8089
CMD ["java","-jar","/gestion-station-ski-1.0.jar"]
