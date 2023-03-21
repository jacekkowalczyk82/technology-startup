# My notes regarding Kafka

https://kafka.apache.org/intro


https://hellokube.dev/posts/three-ways-zookeepeerless-kafka/

https://karinavarela.me/2021/02/27/kafka-and-topics-in-2min/
https://www.redhat.com/sysadmin/compose-kubernetes-podman

https://kafka.apache.org/

https://developer.confluent.io/quickstart/kafka-docker/
https://hellokube.dev/posts/three-ways-zookeepeerless-kafka/



## Kafka with docker componse by confluent

https://developer.confluent.io/quickstart/kafka-docker/

https://developer.confluent.io/get-started/python/#kafka-setup

https://developer.confluent.io/get-started/python/#create-project


## podman compose 

https://unixcop.com/kafka-and-zookeeper-contains-podman/

https://www.redhat.com/sysadmin/compose-kubernetes-podman



## Start kafka container

docker compose up
docker compose up -d


mkdir py-kafka && cd py-kafka

python -m venv env1

source env1/bin/activate

docker compose exec broker \
  kafka-topics --create \
    --topic py-playtube \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1


https://docs.confluent.io/kafka-clients/python/current/overview.html#initialization

producer.produce(topic, key="key", value="value")

