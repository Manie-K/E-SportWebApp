FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="esport-webapp"
LABEL org.opencontainers.image.authors="Maciej Góralczyk"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple webapp about esport organizations and players, backend gateway module."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV GATEWAY_HOST=localhost:8080

ENV PLAYER_SERVICE_APPNAME="esport-players-service"
ENV ORGANIZATION_SERVICE_APPNAME="esport-organizations-service"

EXPOSE 8080

COPY target/ESportWebApp_Gateway-${VERSION}.jar /opt/esport-webapp-gateway/esport-webapp-gateway.jar

CMD ["java", "-jar", "/opt/esport-webapp-gateway/esport-webapp-gateway.jar"]
