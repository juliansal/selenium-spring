package io.happykraken.cucumber.helpers;

import io.happykraken.cucumber.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.fail;

public class PageInteractions extends Base {
	public void login(String username, String password) {
		By byUser = By.name("username");
		By byPass = By.name("password");
		By byBtn = By.cssSelector("form button[type='submit']");

		webdriver.findElement(byUser).sendKeys(username);
		webdriver.findElement(byPass).sendKeys(password);
		webdriver.findElement(byBtn).click();
	}

	public void navigateTo(String url) {
		webdriver.navigate().to(url);
	}

	public void clickLink(String name) {
		By byLink = By.tagName("a");

		webdriver
				.findElements(byLink)
				.stream()
				.filter(WebElement::isDisplayed)
				.filter(el -> el.getText().contentEquals(name))
				.findFirst()
				.ifPresentOrElse(WebElement::click, () -> fail("Failed to find link to click"));
	}
}
