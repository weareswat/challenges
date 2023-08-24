# Challenge - List Posts by Rating

 - Construa as imagens e inicie os contêineres com o seguinte comando:

```
    docker-compose up --build
```

 - Crie o banco de dados e execute as migrações:
```
   docker-compose run web rake db:create
   docker-compose run web rake db:migrate
```

 - Acesse a aplicação em seu navegador usando:
```
    http://localhost:3000
```

# Endpoint de Criação de Posts

 - Para facilitar a usabilidade, além dos endpoints de listagem de posts, upvote e downvote, este projeto inclui um endpoint de criação de posts acessível em:

```
    POST http://localhost:3000/posts
```

 - O endpoint aceita dados no formato JSON no corpo da requisição:

```
{
  "post": {
    "title": "Um post sobre programação",
    "upvotes": 100,
    "downvotes": 50
  }
}
```

# Testes
O projeto também conta com testes unitários e podem ser executados via comando:
```
    rails test
```