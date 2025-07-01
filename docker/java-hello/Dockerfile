FROM openjdk:26-slim-bullseye

workdir /opt/build 

copy Hello.java ./

ENTRYPOINT ["java", "Hello.java"]

# build 
# docker build -t jacekkowalczyk82/java_hello . 
# docker image tag jacekkowalczyk82/java_hello:latest jacekkowalczyk82/java_hello:v1

# running 
# docker run jacekkowalczyk82/java_hello:v1 
