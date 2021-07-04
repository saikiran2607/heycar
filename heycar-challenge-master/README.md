# heycar-Dealer-Listing

Details about Hey Car Interview Question.

Softwares Used

JDK 8
Docker
Mockito
Spring Boot 2.1.3.RELEASE
H2 in memory Database
Lombok API
Swagger API
MapStruts


Requirements to Run this application:

    - JDK 8

    - docker needs to be installed for the dockerfile to be built successfully.

Commands for Up and running:

    - mvn clean install, This will build the project and Creates an Image.
    
    - docker run -p 8083:8083 --name heycardealerlisting oldtimerza/heycar-dealer-listing

Testing and reports:

    - mvn test

    - mvn jacoco:report , this will create a readable reports html file at target/site/jacoco/index.html
    
Extras:
      Swagger 2 api documentation.
     
     http://localhost:8083/v2/api-docs ---> Once application is up (This URL will given JSON Details)
     http://localhost:8083/swagger-ui.html ---> Once application is up (This URL will give API Documentation. From this we can Hit our Endpoints).
    
    - dockerfile-maven and Dockerfile to create docker image for project.

Notes along the way:

    - Spring Boots in Memory Database Helps me to store the Data quickly with very minimum configuartion in yaml file

    - Code reuse is important, so Services and Controllers are kept separate to allow Services to be reused in multiple Controllers.

    - I find Lombok Very useful which Helps by removing boiler plate getter and setter code.

    - Spring initializr(https://start.spring.io/) Help me to starts the Project with minimum time.

    - Postman tool helped to to test all my Endpoint very quickely

    - Make it work first and I started concentrating on Code refactory

Problems encountered:

    - Non standard CSV format required a bit of extra mapping work done.

Things I would like to add:
    - Securing the Rest Endpoints

    - Docker image deployment to docker hub for mvn deploy.
