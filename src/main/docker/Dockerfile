FROM java:8
ADD springboot-demo.jar /springboot-demo.jar
RUN bash -c 'touch /springboot-demo.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/springboot-demo.jar"]
MAINTAINER wang.qingsong