# language: pt
Funcionalidade: Cadastro de novo trajeto
  Como usuário da API
  Quero cadastrar um novo trajeto
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido do trajeto
    Dado que eu tenha os seguintes dados da entrega:
      | campo          | valor        |
      | nomeRua   | Viaduto do Chá            |
      | nomeMotorista | Gabriel Henrique    |
      | statusTrajeto  | Seguro |
      | dataTrajeto    | 2024-08-22   |
    Quando eu enviar a requisição para o endpoint "/entregas" de cadastro de trajetos
    Então o status code da resposta deve ser 201