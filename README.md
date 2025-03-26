"# E-SportWebApp" 

## INFO
This project was made for Internet Service Architecture course at university.

Backend -> Spring Boot  
Frontend -> Angular

# How to Run the Project

## Prerequisites
Ensure you have the following installed:
- Docker
- Docker Compose
- Windows OS (for build.bat script)
- Maven

## Steps to Run

1. *Run the build script*

   build.bat

2. *Start the services using Docker Compose*

   docker compose up -d


## Default Ports
Make sure these ports are available before running the services. You can change them in the docker-compose.yml file if needed:

| Service                | Default Port |
|------------------------|-------------|
| Application           | 8085      |
| Eureka Discovery Service | 8090      |

Once the services are up and running, you can access them through the specified ports.

Enjoy! 🚀
