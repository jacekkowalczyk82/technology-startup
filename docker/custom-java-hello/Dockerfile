FROM jacekkowalczyk82/java_hello:v1

workdir /opt/build 

COPY run.sh ./
RUN chmod 755 ./run.sh

ENTRYPOINT ["bash", "-c", "/opt/build/run.sh" ]

# build 
# docker build -t jacekkowalczyk82/custom_java_hello . 
# docker image tag jacekkowalczyk82/custom_java_hello:latest jacekkowalczyk82/custom_java_hello:v1

# running 
# docker run jacekkowalczyk82/custom_java_hello:v1 
