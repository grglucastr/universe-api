FROM maven:3.8-openjdk-11

COPY . /usr/src/planets

WORKDIR /usr/src/planets

RUN mvn clean install

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "./target/universe-api.jar"]