package br.com.keeggo.core;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import br.com.keeggo.enums.ByValue;

public class Element {

	private ByValue tipo;
	private String mapa;
	private LinkedHashMap<ByValue, By> map = new LinkedHashMap<>();
	
	public Element(ByValue tipo, String mapa) {
		this.tipo = tipo;
		this.mapa = mapa;
		setMap();
	}
	
	public Element(String mapa) {
		this.mapa = mapa;
	}
	
	private void setMap(){
		map.put(ByValue.id, By.id(mapa));
		map.put(ByValue.css, By.cssSelector(mapa));
		map.put(ByValue.name, By.name(mapa));
		map.put(ByValue.xpath, By.xpath(mapa));
		map.put(ByValue.className, By.className(mapa));
		map.put(ByValue.linkText, By.linkText(mapa));
	}
	
	//id, css, xpath, name, linkText, className
		
	public WebElement createElement() {
		if (tipo == null) {
			if(mapa.startsWith("/")) {
				return Driver.driver.findElement(By.xpath(mapa));
			}
			return Driver.driver.findElement(By.cssSelector(mapa));
		}
		return Driver.driver.findElement(map.get(tipo));
	}
	
	public List<WebElement> createElements(){
		if (tipo == null) {
			if(mapa.startsWith("/")) {
				return Driver.driver.findElements(By.xpath(mapa));
			}
			return Driver.driver.findElements(By.cssSelector(mapa));
		}
		
		return Driver.driver.findElements(map.get(tipo));
	}
	
	public void sendKeys(CharSequence... value) {
		createElement().sendKeys(value);
	}
	
	public String getText() {
		return createElement().getText();
	}
	
	public void clear() {
		createElement().clear();
	}
	
	public void click() {
		createElement().click();
	}
	
	public boolean isDisplayed() {
		return createElement().isDisplayed();
	}
	
	public boolean isEnabled() {
		return createElement().isEnabled();
	}
	
	public boolean isSelected() {
		return createElement().isSelected();
	}
	
	public void selectText(String value) {
		Select dropBox = new Select(createElement());
		dropBox.selectByVisibleText(value);
	}
	
	public void selectTextWait(String value, int seg) throws InterruptedException {
		Select dropBox = new Select(createElement());
		for (int i = 0; i < (seg * 2); i++) {
			try {
				dropBox.selectByVisibleText(value);
				return;
			} catch (Exception e) {
				Thread.currentThread();
				Thread.sleep(500);
			}
		}
		
	}
	
	public void selectValue(String value) {
		Select dropBox = new Select(createElement());
		dropBox.selectByValue(value);
	}
	
	public Element visibilityOf() {
		//Driver.wait.until(ExpectedConditions.visibilityOf(createElement()));
		
		By by;
		if (tipo == null) {
			if(mapa.startsWith("/")) {
				by = By.xpath(mapa);
			}else {
				by = By.cssSelector(mapa);
			}
		}else {
			by = map.get(tipo);
		}
		
		Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return this;
	}
	
	public void invisibilityOf() {
		Driver.wait.until(ExpectedConditions.invisibilityOf(createElement()));
		
	}
	
	public Element elementToBeClickable() {
		Driver.wait.until(ExpectedConditions.elementToBeClickable(createElement()));
		return this;
	}
	
	public void moveToElement() {
		Actions action = new Actions(Driver.driver);
		action.moveToElement(createElement()).perform();
		
	}
	
	public void switchTo() {
		Driver.driver.switchTo().frame(createElement());
	}
	
	
	
}
