##Описание проекта
Проект представляет собой два микросервиса взаимодеийствующие между собой посредством Kafka: KafkaProducer и KafkaConsumer.

Producer имеет ендпоинт POST /metrics на который приходят метрики работы приложения, после чего Producer логирует входные данные и записывает их в топик Kafka "metrics-topic".

Consumer читает топик Kafka "metrics-topic", логирует считанное сообщение, запрашивает по id метрики значение из базы, после чего логирует изменение значения поля value, затем сохраняет новое значение в базе.

Consumer имеет 2 ендпоинта пля получения данных из базы:
GET /metrics: Возвращает значение всех метрик из базы.
GET /metrics/{id}: Возвращает значение метрики по ее id.

Для корректной работы микросервисов необходимо предварительно запустить docker образ Kafka командой:

```console
docker-compose up -d
```
##Конфигурация

Конфигурация Kafka:
```console
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"

  kafka:
    image: wurstmeister/kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

Конфигурация топика "metrics-topic":

	Количество партиций: 1

	replication factor:1
