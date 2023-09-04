# correções
- [x] novo post com zero votes está dando erro

# ajustes
- [x] remover módulos/configurações não utilizadas

# adições
- [] adicionar docker
- [] adicionar mysql para produção
- [] adicionar documentação dos endpoints ao README
- [] adicionar documentação de setup da aplicação

# testes
- post
  - model
    1. [x] validar parametros obrigatórios
    2. [x] validar associação (has_mnay) com voto
    3. [x] validar contador up votes
    4. [x] validar contador down votes
    5. [x] validar score quando não tem votos
    8. [x] validar quantidade de votos
    7. [x] validar score (subiu / caiu - antes e depois)
    8. [x] validar diferença de score ({"up_votes": 1, "down_votes": 0} x {"up_votes": 4, "down_votes": 3})
    8. [x] validar diferença de score mesmo quando o percentual de up/down são iguais ({"up_votes": 60, "down_votes": 40} x {"up_votes": 6, "down_votes": 4})
  - requests
    1. [] validar parametros
    2. [] validar o payload
    3. [] validar a lista por score

- vote
  - model
    1. [x] validar parametros obrigatórios
    2. [x] validar associação (belongs_to) com post
  - requests
    1. [] validar parametros
    2. [] validar o payload
