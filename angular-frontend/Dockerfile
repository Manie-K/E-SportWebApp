FROM nginx:1.23.3

LABEL org.opencontainers.image.title="esport-webapp"
LABEL org.opencontainers.image.authors="Maciej Góralczyk"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple webapp about esport organizations and players, angular frontend module."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="1.0.0-SNAPSHOT"

ENV API_URL=http://host.docker.internal:8081/api

EXPOSE 80

ADD dist/angular-frontend/browser /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
