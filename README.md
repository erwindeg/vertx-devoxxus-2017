#Building (builds docker images)
mvn clean install

#Start zookeeper 
docker run -p 2181:2181 zookeeper:3.4.9 

#Running individual docker containers
docker run -it products
docker run -it -p 8000:8000 shop


#Or with docker compose
docker-compose up