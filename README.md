#Preparation
make sure you have the native Docker app running
install Java 8 and Maven

#Building (builds docker images)
mvn clean install

#Start zookeeper 
docker run -p 2181:2181 zookeeper:3.4.9 

#Running individual docker containers
docker run -it products

docker run -it -p 8000:8000 shop

#Or with docker compose
docker-compose up

#start openshift locally with docker
oc cluster up --image=registry.access.redhat.com/openshift3/ose --create-machine

#run docker images as root
oc adm policy add-cluster-role-to-user cluster-admin developer

docker-machine start openshift

docker-machine stop openshift

#remove openshift
docker-machine rm -f openshift


