# Usa un'immagine base ufficiale di Java
FROM openjdk:17-jdk-slim

# Imposta la directory di lavoro all'interno del container
WORKDIR /app

# Copia il file JAR generato dal progetto nella directory di lavoro
COPY target/to-doList-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta su cui Spring Boot sta ascoltando (8080 di default)
EXPOSE 8081

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]
