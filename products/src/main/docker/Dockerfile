FROM openjdk:8
COPY target/products-1.0-fat.jar /usr/verticles/
WORKDIR /usr/verticles/
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar products-1.0-fat.jar"]
