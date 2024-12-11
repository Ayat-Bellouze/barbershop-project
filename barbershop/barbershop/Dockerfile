# Build stage
FROM maven:3-openjdk-21 AS build
WORKDIR /app

# Copier les fichiers du projet dans l'image
COPY . .

# Construire le projet avec Maven
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk-slim
WORKDIR /app

# Copier l'application construite depuis le stage de build
COPY --from=build /app/target/barbershop-0.0.1-SNAPSHOT.war barbershop.war

# Exposer le port sur lequel l'application écoute
EXPOSE 9091

# Commande d'exécution
ENTRYPOINT ["java", "-jar", "barbershop.war"]
