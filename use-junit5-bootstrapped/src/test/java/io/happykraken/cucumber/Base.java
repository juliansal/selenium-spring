package io.happykraken.cucumber;

import org.openqa.selenium.WebDriver;

public class Base {
	public static WebDriver webdriver;

	public static WebDriver getWebDriver() {
		return webdriver;
	}

	public static void setWebDriver(WebDriver webDriver) {
		Base.webdriver = webDriver;
	}
}
