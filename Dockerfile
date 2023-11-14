FROM openjdk:11
WORKDIR /app
COPY target/*.jar /app/gestion-station-ski-1.0.jar
EXPOSE 8089
CMD ["java","-jar","/app/gestion-station-ski-1.0.jar"]