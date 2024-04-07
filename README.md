# SberProject

Запуск докера осуществляется запуском команды из основной папки:
```
docker-compose up
```

И после этого по порту 8080 можно будет иметь доступ к нашему сервису.
Пример запроса:
```
http://localhost:8080/api/v1/get-factorial
```
и в _body_ передать:
```
{
    "factorial_num": 9
}
```
Скрин:
![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](./images/postman-test.png)
