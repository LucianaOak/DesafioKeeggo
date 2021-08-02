package br.com.keeggo.maps;

import br.com.keeggo.core.Element;
import br.com.keeggo.enums.ByValue;

public class PrincipalMap {

	public Element follow = new Element("#follow");
	public Element UserLogged = new Element("#menuUserLink > .hi-user");
	public Element loader = new Element(".loader");
	public Element userLink = new Element("#menuUserLink");
	public Element newAccount = new Element(".create-new-account");
	public Element userName = new Element(ByValue.name, "username");
	public Element password = new Element(ByValue.name, "password");
	public Element signIn = new Element("#sign_in_btnundefined");
	public Element myAccount = new Element("#loginMiniTitle > label[translate='My_account']");
	public Element logout = new Element("#loginMiniTitle > label[translate='Sign_out']");
	public Element msgErro = new Element("#signInResultMessage");
}
