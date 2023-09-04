# correções
- novo post com zero votes está dando erro
- list de post deve considerar score + down votes quando forem iguais
- mensagens de erro da API "can't be blank"

# ajustes
- remover módulos/configurações não utilizadas

# adições
- adicionar mysql para produção
- adicionar docker
- adicionar documentação ao README

# testes
- post
  - model
    1. [x] validar parametros obrigatórios
    2. [x] validar associação (has_mnay) com voto
    3. [x] validar contador up votes
    4. [x] validar contador down votes
    5. [] validar score (subiu / caiu - antes e depois)
    6. [] validar score quando não tem votos
    7. [x] validar quantidade de votos
  - requests
    1. [] validar parametros
    2. [] validar o payload
    3. [] validar a lista por score

- vote
  - model
    1. [] validar parametros obrigatórios
    2. [] validar associação (belongs_to) com post
    2. [] validar o tipo do voto
  - requests
    1. [] validar parametros
    2. [] validar o payload
