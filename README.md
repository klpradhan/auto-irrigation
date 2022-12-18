# auto-irrigation
Automatic Irrigation System

There are 4 different microservices developed along with one discovery server (configured to run on port: 8761)
http://localhost:8761/

![image](https://user-images.githubusercontent.com/30713961/208299162-30769f93-9a15-4d25-b661-d35641147736.png)

1) Plot service : Used to create new plot land, add new sensor device and remove a plot from irrigation system
2) Device service : Used create and manage sensor devices. Device status : ON/OFF. Device availability : OPEN / CLOSE (already used in a plot)
3) Irrigation Service : Run irrigation on a plot, retry device status if found not available, generate alerts
4) Alert service: Used to register or create new alerts

Project setup:
Database used: Postgres
Coding language: Spring Boot (Java 11)
Liquibase framework: used to create DB schema and tables automatically 
Build: Maven build

Build and run steps:
1) Run maven package for all individual service folder to generate required executable jars
2) Start service by running executable jars under specific target folders: 
    e.g. java -jar discovery-server-0.0.1-SNAPSHOT.jar
3) Log location: currently set as D:/LOG
   Please change according to your test env.
4) Test scripts: please find required postman collections under "Documents" folder

ERD Diagram:
![image](https://user-images.githubusercontent.com/30713961/208300027-e6379f54-f13c-4647-969e-19fe74002c88.png)

