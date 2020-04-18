FROM openjdk:11.0
VOLUME /tmp
EXPOSE 8080
##ADD target/*.jar app.jar
ARG DEPENDENCY=/target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
##ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
ENTRYPOINT ["java","-cp","app:app/lib/*","com.hello.HelloWorldServiceApplication"]