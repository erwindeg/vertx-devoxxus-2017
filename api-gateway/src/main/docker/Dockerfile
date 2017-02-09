FROM openjdk:8
COPY target/api-gateway-1.0-fat.jar /usr/verticles/
WORKDIR /usr/verticles/
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar api-gateway-1.0-fat.jar"]
