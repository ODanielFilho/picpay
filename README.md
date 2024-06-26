<p align="center" width="100%">
    <img width="30%" src="https://github.com/ODanielFilho/picpay/blob/main/images/picpay-logo.jpg"> 
</p>


<h3 align="center">
  Desafio Backend do PicPay
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">

</p>




## Como interagir com o banco de dados?
- Utilizamos o [Beekeeper Community](https://github.com/beekeeper-studio/beekeeper-studio/releases/tag/v4.1.13)

## Como interagir com a API?
- Utilizamos o [Postman](https://https://www.postman.com/)

## :rocket: Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL
* Docker
* Spring Cloud Open Feign
* ControllerAdvice & Problem Details
* Hibernate Validator

:mag: Baixe o projeto e teste você mesmo na prática.

## Instalação

Instalação das dependências do projeto

```bash
mvn clean install
```
## Run

Criar uma conexão no MySQL para rodar o banco de dados. Após isso os parâmetros **username** e **password** da sua conexão devem ser informados no arquivo application.properties
```bash
spring.datasource.username=root
spring.datasource.password=
```

## Docker

Será necessário também definir um usuário e uma senha para o banco no arquivo **docker-compose.yml**
```bash
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: picpay_db
      MYSQL_USER: root
      MYSQL_PASSWORD:
```
Por padrão o **USER** está configurado como **root**

Após isso, executar o comando:

```bash
docker compose up -d
```

Developed by Daniel Filho