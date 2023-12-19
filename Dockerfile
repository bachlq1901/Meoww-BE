FROM maven:3.6.3-jdk-8

WORKDIR /app

COPY . .

RUN mvn clean package -Dmaven.test.skip=true

CMD ["java", "-jar", "target/Meow-BE-0.0.1-SNAPSHOT.jar"]
