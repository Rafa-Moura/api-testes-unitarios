# api-testes-unitarios
Api desenvolvida para o estudo dos testes unitários e documentação com o swagger

## PROJETO

A Api tem como propósito realizar o cadastro de um usuário recebendo Nome, Email e Password em seu corpo. Os dados enviados são armazenados no banco de dados H2.

Para criação dos testes unitários, foram utilizadas as ferramentas: JUnit5 e Mockito. 

Para documentação, foi utilizado o Swagger com a dependência do Spring-fox-starter.

![image](https://user-images.githubusercontent.com/55858052/210592924-47fd9650-734f-4ef3-a5cd-052ccbd9989b.png)

### Estrutura do projeto e documentação

A API conta com os endpoints básicos de um CRUD (Create, Read, Update and Delete) dos usuários com o acréscimo de um endpoint a mais, o endpoint de busca por ID.

![image](https://user-images.githubusercontent.com/55858052/210593330-9cd11fa5-addb-423d-a543-7e180babd48e.png)


### Informações para clone e utilização do swagger.


Em seu terminal, digite: git clone https://github.com/Rafa-Moura/api-testes-unitarios.git

Navegue até a pasta que foi criada através do terminal ou abra a pasta em sua IDE ou editor com suporte a MAVEN de sua preferência.

Caso tenha aberto a pasta pelo terminal, digite o comando: mvn spring-boot:run

Após isso, poderá acessar o swagger através do path: localhost:8080/swagger-ui/

### Informações para utilização do postman

O path base da aplicação é: /api

Sendo assim, para acessar os endpoints baste criar conforme o base: localhost:8080/api...

| Method | Url | Description |
| ------ | --- | ----------- |
| POST   | http://localhost:8080/api   | Insere usuário no banco de dados |
| GET    | http://localhost:8080/api   | Lista todos os usuário cadastrados |
| GET    | http://localhost:8080/api/{id}| Lista um usuário pelo iD |
| PUT    | http://localhost:8080/api/{id}| Atualiza um usuário pelo ID|
| DELETE | http://localhost:8080/api/{id}| Deleta um usuáriopelo ID|

### Modelos de Request e Response
![image](https://user-images.githubusercontent.com/55858052/210595044-0ab1bd61-c2cb-4e88-bebb-73333bb258b2.png)

