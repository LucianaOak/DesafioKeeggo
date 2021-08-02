package br.com.keeggo.pages;

import br.com.keeggo.maps.CreateAccountMap;

public class CreateAccountPage {

	
	CreateAccountMap createAccountMap = new CreateAccountMap();
	
	public String getTitle() {
		return createAccountMap.header
				.visibilityOf().getText();
	}
	
	public CreateAccountPage setUserName(String username) {
		createAccountMap.userName.sendKeys(username);
		return this;
	}
	
	public CreateAccountPage setEmail(String email) {
		createAccountMap.email.sendKeys(email);
		return this;
	}
	
	public CreateAccountPage setPassword(String password) {
		createAccountMap.password.sendKeys(password);
		return this;
	}
	
	public CreateAccountPage setConfirmPassword(String password) {
		createAccountMap.confirmPassword.sendKeys(password);
		return this;
	}
	
	public CreateAccountPage setFirstName(String firstName) {
		createAccountMap.firstName.sendKeys(firstName);
		return this;
	}
	
	public CreateAccountPage setLastName(String lastName) {
		createAccountMap.lastName.sendKeys(lastName);
		return this;
	}
	
	public CreateAccountPage setPhone(String phone) {
		createAccountMap.phone.sendKeys(phone);
		return this;
	}
	
	public CreateAccountPage selectCountry(String country) throws InterruptedException {
		createAccountMap.country.selectTextWait(country, 6);
		return this;
	}
	
	public CreateAccountPage setCity(String city) {
		createAccountMap.city.sendKeys(city);
		return this;
	}
	
	public CreateAccountPage setAddress(String address) {
		createAccountMap.address.sendKeys(address);
		return this;
	}
	
	public CreateAccountPage setState(String state) {
		createAccountMap.state.sendKeys(state);
		return this;
	}
	
	public CreateAccountPage setPostalCode(String postalCode) {
		createAccountMap.postalCode.sendKeys(postalCode);
		return this;
	}
	
	public CreateAccountPage clickIagree() {
		createAccountMap.iAgree.click();
		return this;
	}
	
	public void clickRegister() {
		createAccountMap.btnRegister.click();
	}
	
	public boolean isRegister() {
		return createAccountMap.btnRegister.isEnabled();
	}
	
}
