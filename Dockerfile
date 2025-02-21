FROM openjdk:21
COPY target/student-system.jar student-system.jar
EXPOSE 8090
CMD ["java", "-jar", "student-system.jar"]