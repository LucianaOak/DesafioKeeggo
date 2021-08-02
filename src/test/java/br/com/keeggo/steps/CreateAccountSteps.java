package br.com.keeggo.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import br.com.keeggo.pages.CreateAccountPage;
import br.com.keeggo.utils.Utils;
import br.com.keeggo.utils.WordUtils;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class CreateAccountSteps {
	CreateAccountPage createAccountPage = new CreateAccountPage();
	
	@Entao("a pagina create account e exibida")
	public void aPaginaCreateAccountEExibida() {
	    assertEquals("CREATE ACCOUNT", createAccountPage.getTitle());
	    WordUtils.adicionaTituloPrint("Entao a pagina create account e exibida");

	}
	@Quando("os dados sao preenchido com os valores")
	public void osDadosSaoPreenchidoComOsValores(Map<String, String> map) throws InterruptedException {
		map = Utils.trataMap(map);
		
		
		createAccountPage.setUserName(map.get("username"))
	    .setEmail(map.get("email"))
	    .setPassword(map.get("password"))
	    .setConfirmPassword(map.get("confirmPassword"))
	    .setLastName(map.get("lastName"))
	    .setPhone(map.get("phone"))
	    .selectCountry(map.get("country"))
	    .setCity(map.get("city"))
	    .setState(map.get("state"))
	    .setAddress(map.get("postal"));
		
		WordUtils.adicionaTituloPrint("Quando os dados sao preenchido com os valores");
	}
	
	@Quando("os dados dos campos obrigatorios sao preenchido com os valores")
	public void osDadosDosCamposObrigatoriosSaoPreenchidoComOsValores(Map<String, String> map) {
		map = Utils.trataMap(map);
		createAccountPage.setUserName(map.get("username"))
	    .setEmail(map.get("email"))
	    .setPassword(map.get("password"))
	    .setConfirmPassword(map.get("confirmPassword"));
	    
		WordUtils.adicionaTituloPrint("Quando os dados dos campos obrigatorios sao preenchido com os valores");

	}



	
	@Quando("os termos sao aceitos e o registro realizado")
	public void osTermosSaoAceitosEORegistroRealizado() {
	    createAccountPage.clickIagree()
	    .clickRegister();
	}
	
	@Entao("o botao register deve manter desabilitado")
	public void oBotaoRegisterDeveManterDesabilitado() {
	    assertFalse("Botao Register esta habilitado", createAccountPage.isRegister());
	    WordUtils.adicionaTituloPrint("Entao o botao register deve manter desabilitado");

	}



}
