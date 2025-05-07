# language: pt
Funcionalidade: Consulta de trajeto
  Como usuário da API
  Quero consultar um trajeto previamente cadastrado
  Para verificar se os dados estão corretamente armazenados

  Cenário: Consulta bem-sucedida do trajeto cadastrado
    Dado que eu tenha o ID do trajeto cadastrado
    Quando eu enviar a requisição para o endpoint "/entregas/{id}" para obter detalhes do trajeto
    Então o status code da resposta deve ser 200
    E o campo "nomeMotorista" deve ser "Gabriel Henrique"
    E o campo "statusTrajeto" deve ser "Seguro"
