# DesafioKeeggo
# Como executar via IDE com Junit:
1. Importar na IDE de preferencia o projeto como maven java
2. Abrir o arquivo RunnerTest.java e executar via Junit

2.1 Ou executar o projeto como Junit

# Como executar via cucumber feature:
1. selecionar o arquivo em src/test/resources/feature/
2. executar como cucumber feature

# Como executar via linha de comando utilizando maven(necessario ter o maven configurado na maquina):
1. Abrir o terminal no diretorio do projeto
2. Executar o comando: mvn test

# Como executar via linha de comando utilizando maven, para gerar relatorio(necessario ter o maven configurado na maquina):
1. Abrir o terminal no diretorio do projeto
2. Executar o comando: mvn install(Obs, para geração do relatorio e necessario realizar o build do projeto)
3. abrir o arquivo: \target\reports\cucumber-html-reports\overview-features.html

# Evidencias
As evidencias são geradas em formato .docx.

Após execução elas ficam na raiz do projeto dentro diretorio evidencia, cada arquivo é gerado com o nome do cenario

# Executar em outro browser
A execução padrao esta setada para ser no navegador Chrome.

Para executar em outro navegador basta adicionar a propriedade -Dnavegador=browser desejado

Exemplo: mvn test -Dnavegador=edge ou mvn install -Dnavegador=edge

Valores aceitos: 

	chrome 

	firefox 

	ie

	edge

# Estrutura do Projeto
Pacotes:

	Core:
		Pacote responsavel pela parte do core da automação, como acesso ao driver e ações dos elementos

	Enums:
		Pacote responsavel para guardar qualquer Enum que seja necessário criar

	Maps:
		Pacote responsavel pela parte dos mapeamentos dos campos, onde os objetos dos campos são instanciadas utilizando a classe Element de core

	Pages:
		Pacote responsavel pela parte das ações das paginas, como preenchimento, cliques, retornos de conteudos.

	Runner:
		Pacote responsavel pela parte das classes de execuções utilizando JUnit e RunWith Cucumber.class

	Utils:
		Pacote responsavel para guardar todas as classes que contenham metodos que possam ser usadas por varios métodos ou classes

	Steps:
		Pacote responsavel para guardar as Classes de Steps, as quais são geradas pelo cucumber. Nessas classes são chamadas as ações das paginas
		Através das pages e validações.

	features:
		Diretório responsável para guardar os arquivos .feature, onde estão gravados os arquivos relacionados ao cucumber e as escrita em gherkin
	 
# Dependencias utilizadas

	POI:
		Utilizado para criar os arquivos do modelo office, no caso o docx nas evidencias.
	
	Cucumber:
		Dependencia responsável pela leitura dos arquivos .feature escritos em gherkin e realizacao da conversao em metodos.
	
	Webdrivermanager de bonigarcia:	
		Dependencia responsavel por gerenciar os drivers, evitando a necessidade de verificar a versao atual do browser, qual browser vai utilizar e buscar o driver para download.
	
	Maven:
		Responsavel por gerenciar as dependencias utilizadas no projeto.
		
	JUnit:
		Dependencia para realizar a execução dos testes atraves da linguagem Java.