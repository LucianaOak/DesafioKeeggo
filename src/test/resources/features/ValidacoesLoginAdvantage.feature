#language:pt
@advantageDemo
Funcionalidade: Validacoes AdvantageDemo Login

  Contexto: 
    Dado que esteja na pagina "http://advantageonlineshopping.com/#/"

  @validacoesLogin
  Esquema do Cenario: Realizar Login <cenario>
    Quando a modal de login e exibida e os campos preenchidos com os valores
      | username | <username> |
      | password | <password> |
    Entao o botao sign in esta desabilitado

    Exemplos: 
      | cenario                | username      | password    |
      | com username em branco |               | Password123 |
      | com password em branco | lucianaTeste2 |             |

  @validacoesLogin2
  Esquema do Cenario: Realizar Login <cenario>
    Quando a modal de login e exibida e os campos preenchidos com os valores
      | username | <username> |
      | password | <password> |
    E o botao de login e acionado
    Entao a mensagem de erro "<msg>" e exibida

    Exemplos: 
      | cenario                              | username        | password    | msg                                  |
      | login incorreto                      | lucianaTeste234 | Password123 | Incorrect user name or password.     |
      | login bloqueado e password incorreto | lucianaTeste2   | asdfg       | Incorrect user name or password. |
