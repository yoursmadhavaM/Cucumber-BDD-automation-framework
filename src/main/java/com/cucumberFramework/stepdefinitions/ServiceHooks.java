package com.cucumberFramework.stepdefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.cucumberFramework.enums.Browsers;
import com.cucumberFramework.helper.LoggerHelper;
import com.cucumberFramework.testBase.TestBase;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class ServiceHooks {

	TestBase testBase;

	Logger log = LoggerHelper.getLogger(ServiceHooks.class);

	@Before
	public void initializeTest() {
		testBase = new TestBase();
		// Use system property -Dbrowser=CHROME|FIREFOX|EDGE, or default to Firefox
		String browser = System.getProperty("browser", Browsers.FIREFOX.name());
		testBase.selectBrowser(browser);
	}

	@After
	public void endTest(Scenario scenario) {
		if (TestBase.driver == null) {
			return;
		}
		if (scenario.isFailed()) {
			try {
				log.info(scenario.getName() + " is Failed");
				final byte[] screenshot = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException e) {
				e.printStackTrace();
			}
		} else {
			try {
				log.info(scenario.getName() + " is pass");
				scenario.embed(((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BYTES), "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TestBase.driver.quit();
	}
}
