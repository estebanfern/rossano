FROM openjdk:17-alpine
WORKDIR /app
COPY target/rossano-1.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]

#FROM openjdk:17-alpine
#ADD target/rossano-1.jar /usr/share/app.jar
#ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app.jar"]
#services:
#  postgres:
#    image: 'postgres:latest'
#    environment:
#      - 'POSTGRES_DB=mydatabase'
#      - 'POSTGRES_PASSWORD=secret'
#      - 'POSTGRES_USER=myuser'
#    ports:
#      - '5432'
