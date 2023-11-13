FROM openjdk:11

EXPOSE 8089

WORKDIR /app  

COPY target/gestion-station-ski-1.0.jar /app/

ENTRYPOINT ["java", "-jar", "gestion-station-ski-1.0.jar"]
