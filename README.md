# Demo shop with Spring

## Description 
This is a small project to practice some concepts a fuse them with my previous knowledge (and some experiments).

Hints and feedback are much appreciated!

Here I will be using Spring Boot with Kafka (producer),Aspects, MVC...
No tests available... yet.

There is no real data persistence (except for Kafka events); the application uses an in memory database for learning purposes.


## Requirements
- Java 1.8
- Maven
- Docker
- Apache Kafka

## Installation
You need Apache kafka running on port 9092 on your local machine (customizable).

There is a `docker-compose.yaml` (disclaimer! not my creation!) in the root of the project, so you can use
`docker compose up -d`.

After that, it is as simple as running a Spring boot application
`mvn spring-boot:run`


## How to use
There is a sample file `rest-requests.http` with a few examples of **add**,  **remove** and **get**  methods

    curl -X POST --location "http://localhost:8888/products" \
    -H "Content-Type: application/json" \
    -d "{
    "name": "Jordan",
    "brand": "Nike",
    "quality": "GOOD",
    "stock": 6
    }"
