package io.happykraken.usejunit.libraries;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {
	@Autowired
	private WebDriver webdriver;

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			System.out.println("Scenario fail. Take a picture.");
			try {
				byte[] screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "screenshot");
			} catch (Exception e) {
				System.out.println("**** The Screenshot Failed ****" + e.getMessage());
			}
		}

		if (webdriver != null) {
			webdriver.quit();
		}

		System.out.println("****** Browser is quiting. ******");
	}
}
