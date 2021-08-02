package br.com.keeggo.pages;

import br.com.keeggo.maps.PrincipalMap;

public class PrincipalPage {

	PrincipalMap principalMap = new PrincipalMap();
	
	public PrincipalPage clickUserLink() {
		
		principalMap.loader.visibilityOf().invisibilityOf();
		principalMap.follow.visibilityOf();
		principalMap.userLink.visibilityOf().click();
		return this;
	}
	
	public PrincipalPage clickUser() {
		
		principalMap.userLink.visibilityOf().click();
		return this;
	}
	
	public PrincipalPage clickNewAccount() throws InterruptedException {
		Thread.sleep(1500);
		
		principalMap.newAccount.visibilityOf().click();
		return this;
	}
	
	public PrincipalPage setUserName(String userName) {
		principalMap.userName.sendKeys(userName);
		return this;
	}
	
	public PrincipalPage setPassword(String password) {
		principalMap.password.visibilityOf().sendKeys(password);
		return this;
	}
	
	public PrincipalPage clickSignIn() {
		principalMap.signIn.visibilityOf()
		.elementToBeClickable()
		.click();
		return this;
	}
	
	public PrincipalPage clickMyAccount() {
		principalMap.myAccount.click();
		return this;
	}
	
	public PrincipalPage clickLogout() {
		principalMap.logout.click();
		return this;
	}
	
	public String getUser() {
		return principalMap.UserLogged.visibilityOf().getText();
	}
	
	public boolean isSignIn() {
		return principalMap.signIn.isEnabled();
	}
	
	public String getMsgErro() {
		Thread.currentThread();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return principalMap.msgErro.getText();
	}
}
