FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /usr/src/SchoolApplication

ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jdk-noble
COPY . /usr/src/SchoolApplication
WORKDIR /usr/src/SchoolApplication
EXPOSE 8080
COPY --from=build /usr/src/SchoolApplication/target/*.jar SchoolManagementApplication.jar
ENTRYPOINT ["java", "-jar", "SchoolManagementApplication.jar"]

#ENTRYPOINT ["java", "SchoolManagementApplication.class"]
#ENTRYPOINT ["java", "-jar", "SchoolManagementApplication.jar"]

#CMD ["java", "SchoolManagementApplication"]

#RUN ./mvnw clean package -DskipTests

# Executa o JAR gerado
#CMD ["java", "-jar", "target/SchoolManagement-0.0.1-SNAPSHOT.jar"]
