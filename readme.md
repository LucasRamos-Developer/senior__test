## Senior Test

Para executar execute:
```
mvn spring-boot:run
```

Para executar os testes:
```
mvn test
```


### Routes

#### Lista de Cidades Paginada

`GET /cidades/`

| parameter      | description                    |
|:---------------|:-------------------------------|
| `page`         | Numero |


URL de Exemplo:
  http://localhost:8080/cidades?page=1





#### Procurar Cidade por CEP

`GET /cidades/search/`

| parameter      | description                    |
|:---------------|:-------------------------------|
| `cep`          | CEP Numero |


**Observações:** Quando não encontra uma cidade com o CEP, cadastr automaticamente.

URL de Exemplo:
  `http://localhost:8080/cidades/search?cep=01001000`






#### Traçar Rota por CEP

`GET cidades/tracking-route/`

| parameter      | description                    |
|:---------------|:-------------------------------|
| `ceps`          | Array CEP Numero |


**Observações:** Lista as cidades na mesma ordem que os ceps informados. Se tiver a mesma cidade com o mesmo CEP, respeita a ordem do primeiro cep da cidade encontrado.

URL de Exemplo:
  `http://localhost:8080/cidades/tracking-route?ceps=01001000,89209473,89201010,22750009`