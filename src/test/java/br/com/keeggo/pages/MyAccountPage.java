package br.com.keeggo.pages;

import br.com.keeggo.maps.MyAccountMap;

public class MyAccountPage {

	MyAccountMap myAccountMap = new MyAccountMap();
	
	public MyAccountPage clickDeleteAccount() {
		myAccountMap.deleteAccount.visibilityOf().click();
		return this;
	}
	
	public void clickBtnYes() {
		myAccountMap.btnYes.click();
	}
	
}
