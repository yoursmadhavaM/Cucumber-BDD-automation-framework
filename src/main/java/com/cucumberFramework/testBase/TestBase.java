package com.cucumberFramework.testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cucumberFramework.enums.Browsers;
import com.cucumberFramework.enums.OS;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {

	public  static WebDriver driver;

	public WebDriver selectBrowser(String browser) {
		String os = System.getProperty("os.name").toLowerCase();
		boolean isWindows = os.contains(OS.WINDOW.name().toLowerCase());

		if (browser.equalsIgnoreCase(Browsers.CHROME.name())) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			if (isWindows) driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase(Browsers.FIREFOX.name())) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			if (isWindows) driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase(Browsers.EDGE.name())) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			if (isWindows) driver.manage().window().maximize();
		}
		return driver;
	}
}
