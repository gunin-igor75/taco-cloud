### Платформа по формированию рецептов taco

Реализация платформы
- Авторизация и аутентификация пользователей.
- Распределение ролей между пользователями: пользователь и администратор*.

Запуск cassandra:
Создание сети cassandra-net
````
docker network create cassandra-net
````

Запуск cassandra контейнера в docker
````
docker run --name my-cassandra  --network cassandra-net  -p 9042:9042  -d cassandra:latest
````

Запуск консоли cassandra
````
docker run -it --network cassandra-net --rm cassandra cqlsh my-cassandra
````

Создание пространства ключей tacocloud с репликацией -1, надежностью записи
````
cqlsh> create keyspace tacocloud
 ... with replication={‘class’:’SimpleStrategy’, ‘replication_factor’:1}
 ... and durable_writes=true;
````

Для запуска проекта предварительно в корне проекта содать файл .env  для инициализации 
переменных, указанных в application.properties и docker-compose.yaml
Запуск проекта из среды docker:
``` 
docker compose up -d
```
Запуск jar файла 
```
java -jar ads-0.0.1-SNAPSHOT.jar
```

В проекте применены следующие технолонии
- java 17
- spring boot
- spring mvc
- spring data jpa
- spring security
- postgresql 15.3
- liquibase

Проект покрыт тестами на 88%

Разработчики проекта
- Гунин Игорь

