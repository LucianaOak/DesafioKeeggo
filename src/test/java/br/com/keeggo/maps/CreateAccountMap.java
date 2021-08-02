package br.com.keeggo.maps;

import br.com.keeggo.core.Element;
import br.com.keeggo.enums.ByValue;

public class CreateAccountMap {

	public Element header = new Element("h3[translate='CREATE_ACCOUNT']");
	public Element userName = new Element(ByValue.name, "usernameRegisterPage");
	public Element email = new Element(ByValue.name, "emailRegisterPage");
	public Element password = new Element(ByValue.name, "passwordRegisterPage");
	public Element confirmPassword = new Element(ByValue.name, "confirm_passwordRegisterPage");
	public Element firstName = new Element(ByValue.name, "first_nameRegisterPage");
	public Element lastName = new Element(ByValue.name, "last_nameRegisterPage");
	public Element phone = new Element(ByValue.name, "phone_numberRegisterPage");
	public Element country = new Element(ByValue.name, "countryListboxRegisterPage");
	public Element city = new Element(ByValue.name, "cityRegisterPage");
	public Element address = new Element(ByValue.name, "addressRegisterPage");
	public Element state = new Element(ByValue.name, "state_/_province_/_regionRegisterPage");
	public Element postalCode = new Element(ByValue.name, "postal_codeRegisterPage");
	public Element iAgree = new Element(ByValue.name, "i_agree");
	public Element btnRegister = new Element("#register_btnundefined");
	
	
	
	
}
