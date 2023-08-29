package io.happykraken.cucumber.helpers;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

	public static WebDriver startChromeInstance() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--headless");

		return new ChromeDriver(chromeOptions);
	}

	public static void endInstance(Scenario scenario, WebDriver webdriver) {
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
