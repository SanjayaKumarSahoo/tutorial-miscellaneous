Steps to build docker image,run,push through maven 

1 - $ mvn package docker:build

2 - View docker images

    $ sudo docker images  
        REPOSITORY                                           TAG                 IMAGE ID            CREATED             SIZE
        spring-boot-docker                                   latest              c747263a479e        11 seconds ago      194 MB


3 - Run docker

    $ docker run -p 8080:8080 -t spring-boot-docker

4 - Browse http://localhost:8080/hello, you should see as "Hello, I am running inside docker container!" in browser

5 - Pushing into docker hub (https://hub.docker.com/). (note : sanjayakumarsahoo is my docker hub id)

    a) create a tag    
       $ docker tag c747263a479e sanjayakumarsahoo/spring-boot-docker:latest

    b) $ docker login
    
    c) $ docker push sanjayakumarsahoo/spring-boot-docker
    
