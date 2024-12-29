FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="esport-webapp"
LABEL org.opencontainers.image.authors="Maciej Góralczyk"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple webapp about esport organizations and players, backend gateway module."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8081

ENV ESPORT_GATEWAY_HOST=localhost:8081
ENV ESPORT_PLAYER_SERVICE_URL=http://localhost:8080
ENV ESPORT_ORGANIZATION_SERVICE_URL=http://localhost:8080


EXPOSE 8081

COPY target/ESportWebApp_Gateway-${VERSION}.jar /opt/esport-webapp-gateway/esport-webapp-gateway.jar

CMD ["java", "-jar", "/opt/esport-webapp-gateway/esport-webapp-gateway.jar"]
