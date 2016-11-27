FROM openjdk:8
EXPOSE 8080
COPY target/shop-1.0-fat.jar /usr/verticles/
WORKDIR /usr/verticles/
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar shop-1.0-fat.jar"]
