# testProject
# User Subscription Service API

## Описание

Микросервис для управления пользователями и их подписками на цифровые сервисы (Netflix, YouTube Premium и др.).

## Технологии

- Java 17
- Spring Boot 3.1.0
- PostgreSQL
- Swagger UI (документация API)
- Docker

## Установка и запуск

1. Собрать приложение:
```bash
mvn clean package
```

2. Запустить через Docker:
```bash
docker-compose up --build
```

## Доступ к API

- Swagger UI: http://localhost:8080/swagger-ui.html

## Примеры запросов cURL

### Управление пользователями

**Создать пользователя:**
```bash
curl -X POST http://localhost:8080/api/v1/users \
-H "Content-Type: application/json" \
-d '{
    "name": "John",
    "email": "john@example.com"
}'
```

**Получить пользователя:**
```bash
curl -X GET http://localhost:8080/api/v1/users/1
```

**Обновить пользователя:**
```bash
curl -X PUT http://localhost:8080/api/v1/users/1 \
-H "Content-Type: application/json" \
-d '{
    "name": "John Updated",
    "email": "john.updated@example.com"
}'
```

**Удалить пользователя:**
```bash
curl -X DELETE http://localhost:8080/api/v1/users/1
```

### Управление подписками

**Добавить подписку:**
```bash
curl -X POST http://localhost:8080/api/v1/users/1/subscriptions \
-H "Content-Type: application/json" \
-d '{
    "serviceName": "Netflix",
    "startDate": "2023-01-01",
    "monthlyFee": 9.99
}'
```

**Получить подписки пользователя:**
```bash
curl -X GET http://localhost:8080/api/v1/users/1/subscriptions
```

**Удалить подписку:**
```bash
curl -X DELETE http://localhost:8080/api/v1/users/1/subscriptions/1
```

**Топ-3 популярных подписок:**
```bash
curl -X GET http://localhost:8080/subscription/top
```

## Модели данных (Swagger)

### Пользователь (User)
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "subscriptions": [
    {
      "serviceName": "Netflix",
      "startDate": "2025-05-08",
      "monthlyFee": 9.99
    }
  ]
}
```

### Подписка (Subscription)
```json
{
  "serviceName": "Netflix",
  "startDate": "2025-05-08",
  "monthlyFee": 9.99
}
```

