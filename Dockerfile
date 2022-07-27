FROM adoptopenjdk/openjdk11:latest
COPY target/todo-0.0.1-SNAPSHOT.jar todo-0.0.1.jar
ENTRYPOINT ["java","-jar","/todo-0.0.1.jar"]