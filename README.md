<a name="readme-top"></a>
<div align="center">

![REST Assured](stellarburgers.png)

</div>
<div align="center">
    <h3 align="center">Part II: тестирование API</h3>
    <p align="center">
        Вторая часть дипломного проекта курса "qa-automation-engineer-java".
        <br/>
        <br/>
    </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#tests">Tests</a></li>
    <li><a href="#tech-stack">Tech Stack</a></li>
  </ol>
</details>

### About The Project

Необходимо протестировать API для <a href="https://stellarburgers.nomoreparties.site/">Stellar Burgers</a>.
Документация <a href="https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf">API</a>.

<b>Создание пользователя:</b>
* создать уникального пользователя;
* создать пользователя, который уже зарегистрирован;
* создать пользователя и не заполнить одно из обязательных полей.
    
<b>Логин пользователя:</b>
* логин под существующим пользователем;
* логин с неверным логином и паролем.

<b>Изменение данных пользователя:</b>
* с авторизацией;
* без авторизации.
<p>Для обеих ситуаций нужно проверить, что любое поле можно изменить. Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.</p>
  
<b>Создание заказа:</b>
* с авторизацией;
* без авторизации;
* с ингредиентами;
* без ингредиентов;
* с неверным хешем ингредиентов.

<b>Получение заказов конкретного пользователя:</b>
* авторизованный пользователь;
* неавторизованный пользователь.

### Usage

Для запуска тестов в терминале выполнить команду `mvn clean test`. 
Для запуска отчета Allure выполнить команду `mvn allure:serve`

### Tests
* `UserRegistrationTest` - регистрация пользователя
* `UserRegistrationWithoutRequiredFieldTest` - регистрация пользователя без обязательных полей
* `UpdateUserTest` - обновление информации пользователя
* `ExistingUserAuthorizationTest` - регистрация существующего пользователя 
  

* `CreateOrderTest` - создание заказа
* `GettingUserOrders` - получение заказов

### Tech Stack

[![Java11][java]][javadoc-url]
[![JUnit4][junit]][junit-url]
[![Allure][Allure]][Allure-url]
[![Gson][Gson]][Gson-url]
[![REST Assured][REST_Assured]][rest-assured-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[javadoc-url]: https://docs.oracle.com/en/java/javase/11/docs/api/index.html
[java]: https://img.shields.io/badge/Java_11-FF2D20?style=for-the-badge&logo=oracle&logoColor=white
[junit-url]: https://junit.org/junit4/
[junit]: https://img.shields.io/badge/JUnit_4-20232A?style=for-the-badge
[Allure-url]: https://docs.qameta.io/allure/
[Allure]: https://img.shields.io/badge/Allure-a5d37e?style=for-the-badge
[rest-assured-url]: https://rest-assured.io/
[REST_Assured]: https://img.shields.io/badge/rest_assured-00bb76?style=for-the-badge
[Gson-url]: https://github.com/google/gson
[Gson]: https://img.shields.io/badge/Gson-4e6f58?style=for-the-badge&logo=google&logoColor=white
