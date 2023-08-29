package io.happykraken.usejunit.libraries.helpers;

import io.cucumber.spring.CucumberTestContext;
import io.happykraken.usejunit.libraries.Base;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class TheInternet extends Base {

	public void login(String username, String password) {
		By byUser = By.name("username");
		By byPass = By.name("password");
		By byBtn = By.cssSelector("form button[type='submit']");

		driver.findElement(byUser).sendKeys(username);
		driver.findElement(byPass).sendKeys(password);
		driver.findElement(byBtn).click();
	}
}
