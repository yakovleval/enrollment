# Вступительное задание в школу бэкенд-разработки Яндекс
## Сборка и запуск
### docker-compose:
```sh
docker-compose up -d
```

### native:
Требуется java 17 или выше и postgresql 13.3 или выше

```sh
./gradlew bootJar
java -jar build/libs/market-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://<host>:<port>/postgres --spring.datasource.username=<postgres_username> --spring.datasource.password=<postgres_password>
```
Так же реквизиты базы данных можно указать в файле `application.properties`.

при сборке и запуске тестов (например с помощью `./gradlew build` или `./gradlew test`) приложение пытается подключиться к базе данных, описанной в файле `application.properties`. Перед запуском тестов база данных должна быть пустой, иначе тесты могут не пройти. 
