# Bahamas client challenge

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .



## Pre requisites
jdk 8 or 11+ \
maven 3.6.2+ \
docker for image deployment (e.g. mysql db, webApp)

## Deploying MySql db instance
(Following documention https://hub.docker.com/_/mysql) \
Pull the docker image of the db
````
docker pull mysql
````
then deploy image instance
````
docker run --name testDB -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123QWEasd -d mysql:latest
````
## Creating a deployable docker image
First run 
````
mvn package
````

Then build docker image using (while being in path of the project folder)
````
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/getting-started-jvm .
````

To deploy image, either use the following, with debug port disabled:
````
docker run -i --link testDB:mysql --rm -p 8080:8080 quarkus/getting-started-jvm
````
or enable the port, by using:
````
docker run -i --link testDB:mysql --rm -p 8080:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" quarkus/getting-started-jvm
````


# Alternative deployment methods given by Quarkos.io
## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `getting-started-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/getting-started-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/getting-started-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.