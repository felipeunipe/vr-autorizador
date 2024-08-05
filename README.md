
# Projeto autorizador da VR

Projeto respons�vel por cadastrar, consultar saldo e realizar transa��o de cart�o de cr�dito


## Descri��o T�cnica
Neste projeto foram implementadas as features de criar cart�o, obter saldo de cart�o e realizar uma transa��o.
Foram utilizadas as tecnologias Java 21, Spring MVC, Spring Security, Spring Data, Mockito, MongoDB e Docker.
A feature de realizar transa��o utilizou o padr�o de projeto **Strategy** para realizar uma s�rie de valida��es com implementa��es de validadores de alta coes�o e baixo acoplamento.
Nos validadores, **Strategy**, foram implementados a interface Order para garantir que o Spring injete e execute as classes de valida��o na ordem correta.
Nas demais features n�o foi necess�rio implementar padr�o de projeto pois n�o houve necessidade devido a simplicidade delas.
Para o controle de transa��o duplicada, foi utilizada a anota��o **@Version** que realiza o Lock Otimista no banco de dados.
## Documenta��o da API

#### Cria um novo cart�o

```http
  POST /cartoes
```

| Par�metro   | Tipo       | Descri��o                           |
| :---------- | :--------- | :---------------------------------- |
| `Authorization`| `string/header` | **Obrigat�rio** Usu�rio e senha    |
| `numeroCartao` | `string/body` | **Obrigat�rio**. N�mero do cart�o  |
| `senha`        | `string/body` | **Obrigat�rio**.  Senha do cart�o  |

#### Consulta saldo do cart�o

```http
  GET /cartoes/{numeroDoCartao}
```

| Par�metro      | Tipo       | Descri��o                           |
| :----------    | :--------- | :---------------------------------- |
| `Authorization Basic`| `string/header` | **Obrigat�rio** Usu�rio e senha    |
| `numeroCartao` | `string/PathVariable` | **Obrigat�rio**. N�mero do cart�o|

#### Realizar uma trasa��o

```http
  POST /transacoes
```

| Par�metro   | Tipo       | Descri��o                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Authorization Basic`| `string/header` | **Obrigat�rio** Usu�rio e senha    |
| `numeroCartao` | `string/body` | **Obrigat�rio**. N�mero do cart�o  |
| `senhaCartao`        | `string/body` | **Obrigat�rio**.  Senha do cart�o  |
| `valor`        | `string/body` | **Obrigat�rio**.  Valor da transa��o  |


