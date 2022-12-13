# Musala Soft Drone Assignment
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

## Assignment
#### Task Description
We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has:
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task.

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone;


## Setup Instructions
#### Requirements
Your machine should have the following installed:
- Docker
- Java SDK 17
- Maven
- Git
- IDE (preferaby IntelliJ IDEA)

##### *Step 1*
Clone the repository from Github using the following command in your terminal
```ssh 
git clone https://github.com/sewalups/drone-assignment.git
```
##### *Step 2*
Make sure you have the docker on your working machine running database running before firing this command below which is supposed to start up the postreSQL database in a containe
```docker
docker-compose up -d
```
To configure the database, we shall have to ssh straight into the running container so that we can create the database in question.
```ssh
 docker exec -it postgres bash
```
Now, depending on the configurations you set up for yourself in the application.properties and in the docker-compose.yml file, we shall use those to enter the postgreSQL console. but currently, the default username currently is *sewalusteven*.

If nothing has been changed proceed with this command.
```ssh
psql -U sewalusteven
```
This should take you to the console where you will then create the database using the following SQL query
```sql
 CREATE DATABASE dronedb;
```
After which you can verify it's creation by running
```
\l
```
Once, you confirm the database's existence, then you can exit out from both the SQL and docker bash by typing the following command twice.
```
exit
```
##### *Step 3*
After, you are done with the previous step, it is time to install all  maven dependencies by running
```
mvn install
```
After installing the depencies, we can then run the java -jar command provided the executable was packaged as a jar file, followed by the path to the targetted executable, this usually goes in the /target directory. so the completed command should look something close to this
```
java -jar target/droneproject-0.0.1-SNAPSHOT.jar
```
This will start a java servlet on port 8080.You will then be able to access the application on the following default URL:
**http://localhost:8080**

#### Available Endpoints
- api/v1/drone [POST - register drone]
- api/v1/drone/{droneId}/_load [POST - load drone]
- api/v1/drone/{droneId}/_medications [GET - check drone for load]
- api/v1/drone//_available [GET - check for available drones]
- api/v1/drone/{droneId}/_battery [GET -  get drone battery]
- api/v1/medication [GET -  all medications available]


> For any questions concerning the project or its set up: reach out to me on my email: sewalusteven@gmail.com

**Note**
Did not have enough time to flesh out the tests and some regex
