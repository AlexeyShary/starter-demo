## Общее описание
Реализация своего Spring Boot Starter для логирования HTTP запросов в приложении на базе Spring Boot.

http-logger-starter позволяет логировать все входящие запросы и исходящие ответы в приложении, к которому он подключен.

Основные моменты реализации:
- Механизм перехвата реализован через использование интерцепторов Spring
- Реализована автоконфигурация с использованием Spring Boot
- Реализована настройка уровня логирования и форматом вывода логов через файл конфигурации

http-logger-starter включает в себя юнит тесты для проверки корректности его работы

Состав репозитория:
- http-logger-starter - сам стартер
- sample-service - демо сервис, в котором используется http-logger-starter

## Quick start

1) Запуск докера с базой данных

```
docker-compose up
```

2) Запуск приложения sample-service через IDE

## Описание http-logger-starter

Пример логов из сервиса с подключенным http-logger-starter:

```
2024-05-21T12:05:10.289+03:00  INFO 25268 --- [nio-8080-exec-2] t.o.h.interceptor.LoggingInterceptor     : Incoming Request: Method: GET, URL: /etc/hi, Headers: [user-agent:"PostmanRuntime/7.38.0", accept:"*/*", postman-token:"1d4e02da-19b2-4768-be20-c113787780d5", host:"localhost:8080", accept-encoding:"gzip, deflate, br", connection:"keep-alive"]
2024-05-21T12:05:10.314+03:00  INFO 25268 --- [nio-8080-exec-2] t.o.h.interceptor.LoggingInterceptor     : Outgoing Response: Status: 200, Headers: [Content-Type:"text/plain;charset=UTF-8", Content-Length:"2", Date:"Tue, 21 May 2024 09:05:10 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"], Time Taken: 25 ms
```

Логируемая информация по входящим запросам:
- Метод запроса
- URL
- Заголовки

Логируемая информация по исходящим ответам:
- Статус ответа
- Заголовки
- Время обработки запроса

## Подключение http-logger-starter

Для установки http-logger-starter в локальный Maven репозиторий необходимо выполнить команду для http-logger-starter:

```
cd http-logger-starter
./mvnw install
cd ../
```

Для подключения http-logger-starter в локальный проект необходимо добавить зависимость:

```
<dependency>
    <groupId>t1.openschool</groupId>
    <artifactId>http-logging-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Конфигурирование

http-logger-starter содержит конфигурацию по умолчанию и будет работать автоматически после включения в зависимости.

Из сервиса-потребителя доступна конфигурация http-logger-starter через application.yml.

Пример yml со значениями по умолчанию:

```
logging:
  starter:
    enabled: true
    level: INFO
    print-request-headers: true
    print-response-headers: true
    print-response-time: true
```

#### Описание полей конфигурации

- enabled - (true / false) - включение или отключение http-logger-starter. Если отключить, то интерцептор не будет зарегистрирован в контексте приложения
- level - (TRACE / DEBUG / INFO) - уровень логов, который будет использовать http-logger-starter для выдачи своих сообщений
- print-request-headers - (true / false) - включение или отключение печати информации о заголовках запросов в сообщение
- print-response-headers - (true / false) - включение или отключение печати информации о заголовках ответов в сообщение
- print-response-time - (true / false) - включение или отключение печати информации о времени обработки запроса в сообщение


## Описание sample-service

В проекте реализованы контроллеры с CRUD методами по работе с сущностью User, а так же контроллер для etc запросов. Все существующие методы можно увидеть в коллекции Postman. Все методы перехватываются http-logger-starter и логируются.

http-logger-starter подключен в pom:

```
        <dependency>
            <groupId>t1.openschool</groupId>
            <artifactId>http-logging-starter</artifactId>
            <version>1.0.0</version>
        </dependency>
```

При проблемах с подключением http-logger-starter необходимо выполнить установку стартера в локальный репозиторий Maven:

```
cd http-logger-starter
./mvnw install
cd ../
```

## API sample-service

[Спецификация OpenAPI](./sample-service/api/api-docs.json)

При работающем приложении доступен Swagger:
- http://localhost:8080/swagger-ui/index.html#/

***

[Коллекция Postman](./sample-service/api/LoggingStarterDemo.postman_collection.json)

Коллекция Postman содержит запросы ко всем эндпоинтам приложения, необходимыми для проверки его работоспособности.

## Запуск приложения sample-service
### Вариант 1 - Запуск приложения и базы данных в Docker контейнерах

Сборка приложения и запуск через docker:

```
cd sample-service
./mvnw clean package
cd ../
docker-compose -f docker-compose-full.yml up
```

Поднимаемые контейнеры:

```
sample-service-app - Приложение - порты 8080:8080
sample-db - База данных PostgreSQL - порты 8081:5432
```

***

### Вариант 2 - Запуск приложения через IDE и Docker контейнер с базой данных

Запуск контейнера с базой данных:

```
docker-compose up
```

Поднимаемые контейнеры:

```
sample-db - База данных PostgreSQL - порты 8081:5432
```

Приложение можно запустить любым удобным способом, например из IDE.

***

Для удобства тестирования база данных очищается при каждом запуске приложения.
