FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="esport-webapp"
LABEL org.opencontainers.image.authors="Maciej Góralczyk"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple webapp about esport organizations and players, backend organizations module."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:esportorganizationdb
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true
ENV PLAYER_SERVICE_URL = "localhost:8080"

EXPOSE 8080

COPY target/ESportWebApp_Organization-${VERSION}.jar /opt/esport-webapp-organization/esport-webapp-organization.jar

CMD ["java", "-jar", "/opt/esport-webapp-organization/esport-webapp-organization.jar"]
