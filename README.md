
# Projeto autorizador da VR

Projeto responsável por cadastrar, consultar saldo e realizar transação de cartão de crédito


## Descrição Técnica
Neste projeto foram implementadas as features de criar cartão, obter saldo de cartão e realizar uma transação.
Foram utilizadas as tecnologias Java 21, Spring MVC, Spring Security, Spring Data, Mockito, MongoDB e Docker.
A feature de realizar transação utilizou o padrão de projeto **Strategy** para realizar uma série de validações com implementações de validadores de alta coesão e baixo acoplamento.
Nos validadores, **Strategy**, foram implementados a interface Order para garantir que o Spring injete e execute as classes de validação na ordem correta.
Nas demais features não foi necessário implementar padrão de projeto pois não houve necessidade devido a simplicidade delas.
Para o controle de transação duplicada, foi utilizada a anotação **@Version** que realiza o Lock Otimista no banco de dados.
## Documentação da API

#### Cria um novo cartão

```http
  POST /cartoes
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `Authorization`| `string/header` | **Obrigatório** Usuário e senha    |
| `numeroCartao` | `string/body` | **Obrigatório**. Número do cartão  |
| `senha`        | `string/body` | **Obrigatório**.  Senha do cartão  |

#### Consulta saldo do cartão

```http
  GET /cartoes/{numeroDoCartao}
```

| Parâmetro      | Tipo       | Descrição                           |
| :----------    | :--------- | :---------------------------------- |
| `Authorization Basic`| `string/header` | **Obrigatório** Usuário e senha    |
| `numeroCartao` | `string/PathVariable` | **Obrigatório**. Número do cartão|

#### Realizar uma trasação

```http
  POST /transacoes
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Authorization Basic`| `string/header` | **Obrigatório** Usuário e senha    |
| `numeroCartao` | `string/body` | **Obrigatório**. Número do cartão  |
| `senhaCartao`        | `string/body` | **Obrigatório**.  Senha do cartão  |
| `valor`        | `string/body` | **Obrigatório**.  Valor da transação  |


