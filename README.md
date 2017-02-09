#In all cluster.xml files, replace Erwins-MBP.home with you machines hostname (TODO: only 1 cluster.xml)
<property name="zookeeper_url">Erwins-MBP.home:2181</property>

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

docker-machine start openshift
docker-machine stop openshift

#remove openshift
docker-machine rm -f openshift