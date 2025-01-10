FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="esport-webapp"
LABEL org.opencontainers.image.authors="Maciej Góralczyk"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple webapp about esport organizations and players, backend discovery service module."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV EUREKA_INSTANCE_HOSTNAME=localhost
ENV EUREKA_CLIENT_FETCH_REGISTRY=false
ENV EUREKA_CLIENT_REGISTER_WITH_EUREKA=false
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8080/eureka

ENV EUREKA_SERVER_ENABLE_SELF_PRESERVATION=false
ENV EUREKA_SERVER_EVICTION_INTERVAL_TIMER_IN_MS=2000

EXPOSE 8080

COPY target/ESportWebApp_EurekaDiscovery-${VERSION}.jar /opt/esport-webapp-eureka/esport-webapp-eureka.jar

CMD ["java", "-jar", "/opt/esport-webapp-eureka/esport-webapp-eureka.jar"]
