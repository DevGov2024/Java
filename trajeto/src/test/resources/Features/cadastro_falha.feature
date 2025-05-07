# language: pt
Funcionalidade: Cadastro de novo trajeto (Falhas de validação)
  Como usuário da API
  Quero cadastrar um novo trajeto
  Para que o sistema valide os dados corretamente

  Cenário: Falha no cadastro do trajeto devido a campo faltante
    Dado que eu tenha os seguintes dados da entrega:
      | campo          | valor                |
      | nomeMotorista  | Gabriel Henrique     |
      | statusTrajeto  | Seguro               |
      | dataTrajeto    | 2024-08-22           |
    Quando eu enviar a requisição para o endpoint "/entregas" de cadastro de trajetos
    Então o status code da resposta deve ser 422

  Cenário: Falha no cadastro do trajeto devido a dados inválidos
    Dado que eu tenha os seguintes dados da entrega:
      | campo          | valor                |
      | nomeRua        | @@@                  |
      | nomeMotorista  | Gabriel Henrique     |
      | statusTrajeto  | Seguro               |
      | dataTrajeto    | 2024-08-22           |
    Quando eu enviar a requisição para o endpoint "/entregas" de cadastro de trajetos
    Então o status code da resposta deve ser 400
