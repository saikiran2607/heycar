FROM openjdk:8

ARG JAR_FILE
ADD target/${JAR_FILE} /heycar-dealer-listing.jar
EXPOSE 8083/tcp
ENTRYPOINT ["java", "-jar","/heycar-dealer-listing.jar"]
