### Скрипт осуществляет парсинг [реестра трудовых функций](https://profstandart.rosmintrud.ru/obshchiy-informatsionnyy-blok/natsionalnyy-reestr-professionalnykh-standartov/reestr-trudovyh-funkcij) и загрузку описания каждоый функции в таблицу Базы Данных.
#### Перед запуском необходимо произвести ряд действий в классе Main (src/main/java/Main)
1) Изменить URL-адрес на адрес своей БД (переменная URL)
2) Изменить логин учётной записи на свой (перменная LOGIN)
3) Изменить пароль от учетной записи на свой (переменная PASS)
4) Собрать файл (MAVEN) и запустить

#### После запуска должна появиться статусная строка, показывающая прогресс:
![Статусная строка](https://github.com/Neslaadki/professional_parser/blob/master/pages/screenshots/statusBar.png)
