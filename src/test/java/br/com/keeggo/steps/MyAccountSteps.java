package br.com.keeggo.steps;

import br.com.keeggo.pages.MyAccountPage;
import br.com.keeggo.utils.WordUtils;
import io.cucumber.java.pt.Quando;

public class MyAccountSteps {

	MyAccountPage myAccountPage = new MyAccountPage();
	
	@Quando("a exclusao e realizada")
	public void aExclusaoERealizada() {
	    myAccountPage.clickDeleteAccount();
	    
	    WordUtils.adicionaTituloPrint("Quando a exclusao e realizada");

	    myAccountPage.clickBtnYes();
	}



	

}
