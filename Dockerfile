FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia el .jar compilado
COPY target/*.jar app.jar

# Copia el wallet a la imagen (queda fijo dentro de /app/wallet)
COPY src/main/resources/wallet ./wallet

# Configura Oracle Wallet
ENV TNS_ADMIN=/app/wallet
ENV JAVA_TOOL_OPTIONS="-Doracle.net.tns_admin=/app/wallet"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
