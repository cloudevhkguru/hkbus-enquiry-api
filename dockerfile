FROM openjdk:11
WORKDIR /app
COPY ./target/hkbus-enquiry-api.jar /app
EXPOSE 8080
CMD ["java","-jar", "hkbus-enquiry-api.jar"]