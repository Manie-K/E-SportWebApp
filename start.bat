@echo off
cd ESportWebApp_Gateway
call mvn clean package spring-boot:repackage -DskipTests
cd ..
cd ESportWebApp_Organization
call mvn clean package spring-boot:repackage -DskipTests
cd ..
cd ESportWebApp_Player
call mvn clean package spring-boot:repackage -DskipTests
cd ..
cd angular-frontend
call ng build --configuration production
cd ..
call docker-compose up