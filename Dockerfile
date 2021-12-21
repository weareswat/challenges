FROM maven:3.6.3-jdk-11

ENV jar_name=bahamas-challenge-app

RUN mkdir /var/app

COPY . /var/app

WORKDIR /var/app

RUN mvn clean install

CMD java -jar target/$jar_name.jar