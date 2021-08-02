package br.com.keeggo.steps;


import br.com.keeggo.core.Driver;
import br.com.keeggo.utils.WordUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;

public class GenericSteps {

	@Before
	public void abreNavegador(Scenario cenario) throws Throwable {
		Driver.abreNavegador();
		WordUtils.adicionaTituloCenario(cenario, "AdvantageDemo", "Keeggo");
	}
	
	@After
	public void fechaNavegador(Scenario cenario) throws Throwable {
		
		boolean erroEncontrado = false;
		if (!cenario.getStatus().toString().equals("PASSED")) {
			erroEncontrado = true;
			WordUtils.adicionaTituloPrint("Passo com erro:");
		}
		String[] data = WordUtils.addHora().split(" ");
		WordUtils.closeWord(cenario.getName(), erroEncontrado, data[0].replace("/", "-"));
		Driver.fechaNavegador();
	}
	
	@Dado("que esteja na pagina {string}")
	public void queEstejaNaPagina(String url) throws InterruptedException {
		Driver.driver.get(url);
		
	}
}
