#language:pt
@CadastrosAdvantage
Funcionalidade: AdvantageDemo
	Contexto:
		Dado que esteja na pagina "http://advantageonlineshopping.com/#/"
    
	@cadastroAdvantageDemo
  Cenario: Realizar Cadastro
    Quando acesso a nova conta e realizado
    Entao a pagina create account e exibida
    Quando os dados sao preenchido com os valores
      | username        | lucianaTeste      |
      | email           | luciana@teste.com |
      | password        | Password123       |
      | confirmPassword | Password123       |
      | firstName       | Luciana           |
      | lastName        | Carvalho          |
      | phone           |         454545454 |
      | country         | Brazil            |
      | city            | Sao Paulo         |
      | Address         | Rua teste         |
      | state           | Sao Paulo         |
      | postal          |             12121 |
    Quando os termos sao aceitos e o registro realizado
    Entao a validacao do login e realizada com o usuario "lucianaTeste"
		
	@loginAdvantageDemo
	Cenario: Realizar Login
		Quando a modal de login e exibida e os campos preenchidos com os valores
		| username        | lucianaTeste      |
		| password        | Password123       |
		E o botao de login e acionado
		Entao a validacao do login e realizada com o usuario "lucianaTeste"
	
	@deletarUsuario
	Cenario: Deletar Usuario
		Quando a modal de login e exibida e os campos preenchidos com os valores
		| username        | lucianaTeste      |
		| password        | Password123       |
		E o botao de login e acionado
		Entao a validacao do login e realizada com o usuario "lucianaTeste"
		Quando acesso myAccount no menu usuario
		E a exclusao e realizada
		