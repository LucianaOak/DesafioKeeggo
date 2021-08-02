package br.com.keeggo.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import br.com.keeggo.pages.PrincipalPage;
import br.com.keeggo.utils.Utils;
import br.com.keeggo.utils.WordUtils;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PrincipalSteps {

	PrincipalPage principalPage = new PrincipalPage();

	@Quando("acesso a nova conta e realizado")
	public void acessoANovaContaERealizado() throws InterruptedException {
		principalPage
		.clickUserLink()
		.clickNewAccount();
	}

	@Entao("a validacao do login e realizada com o usuario {string}")
	public void aValidacaoDoLoginERealizadaComOUsuario(String userName) {
		assertEquals(userName, principalPage.getUser());
		WordUtils.adicionaTituloPrint("Entao a validacao do login é realizada com o usuario: "+ userName);
		
	}

	@Quando("a modal de login e exibida e os campos preenchidos com os valores")
	public void aModalDeLoginEExibidaEOsCamposPreenchidosComOsValores(Map<String, String> map) {
		map = Utils.trataMap(map);
		
		principalPage
		.clickUserLink()
		.setUserName(map.get("username"))
		.setPassword(map.get("password"));
		
		WordUtils.adicionaTituloPrint("Quando a modal de login e exibida e os campos preenchidos com os valores");
		
	}
	
	@Quando("o botao de login e acionado")
	public void oBotaoDeLoginEAcionado() {
	    principalPage.clickSignIn();
	}



	
	@Quando("acesso myAccount no menu usuario")
	public void acessoMyAccountNoMenuUsuario() {
	    principalPage
	    .clickUser()
	    .clickMyAccount();
	}

	@Entao("o botao sign in esta desabilitado")
	public void oBotaoSignInEstaDesabilitado() {
	
		assertFalse(principalPage.isSignIn());
		WordUtils.adicionaTituloPrint("Entao o botao sign in esta desabilitado");
	}
	
	@Entao("a mensagem de erro {string} e exibida")
	public void aMensagemDeErroEExibida(String erro) {
	    assertEquals(erro, principalPage.getMsgErro());
	    
	    WordUtils.adicionaTituloPrint("Entao a mensagem de erro "+erro+" e exibida");
	}






		



}
