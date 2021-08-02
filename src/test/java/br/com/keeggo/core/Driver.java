package br.com.keeggo.core;

import java.io.File;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	public static WebDriver driver;
	public static WebDriverWait wait;
	private static int numberPrint = 0;

	public static void abreNavegador() {
		String path = "src/test/resources/drivers/";
		String navegador = System.getProperty("navegador");
		escolheNavegador(path, navegador);

		wait = new WebDriverWait(driver, 20);

		Driver.driver.manage().window().maximize();
	}

	public static void escolheNavegador(String path, String navegador) {
		try {
			switch (navegador) {
			case "chrome":
				chrome(path);
				break;
			case "firefox":
				firefox(path);
				break;
			case "ie":
				ie(path);
				break;
			case "edge":
				edge(path);
				break;

			default:
				chrome(path);
				break;
			}
		} catch (Exception e) {
			chrome(path);
		}
	}

	private static WebDriver chrome(String path) {
		WebDriverManager.chromedriver().setup();
		Driver.driver = new ChromeDriver();
		return Driver.driver;
	}

	private static WebDriver firefox(String path) {
		WebDriverManager.firefoxdriver().setup();
		Driver.driver = new FirefoxDriver();
		return Driver.driver;
	}

	private static WebDriver ie(String path) {
		WebDriverManager.iedriver().setup();
		// System.setProperty("webdriver.ie.driver", path+"IEDriverServer.exe");
		Driver.driver = new InternetExplorerDriver();
		return Driver.driver;
	}

	private static WebDriver edge(String path) {
		WebDriverManager.edgedriver().setup();
		Driver.driver = new EdgeDriver();
		return Driver.driver;
	}

	public static void fechaNavegador() {
		Driver.driver.quit();
	}

	public static String printScreen() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		int _numberPrint = getNumberPrint() + 1;

		try {
			FileUtils.copyFile(srcFile, new File("imgs\\screenshot_" + _numberPrint + ".png"));
			setNumberPrint(_numberPrint);
		} catch (Exception e) {
			System.err.println(e);
		}
		return "imgs\\screenshot_" + _numberPrint + ".png";
	}

	private static void setNumberPrint(int _numberPrint) {
		numberPrint = _numberPrint;

	}

	private static int getNumberPrint() {
		return numberPrint;
	}

}
