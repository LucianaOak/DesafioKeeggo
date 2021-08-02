package br.com.keeggo.runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		tags = "",
		glue = "br/com/keeggo/steps",
		monochrome = true,
		plugin = {"json:target/reports/CucumberReports.json", "pretty"},
		snippets = SnippetType.CAMELCASE
		
		
		)

public class RunnerTest {
	@BeforeClass
	public static void iniciaExecucao() {
		//System.setProperty("navegador", "edge");
	}
}
