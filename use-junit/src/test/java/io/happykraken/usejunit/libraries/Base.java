package io.happykraken.usejunit.libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

public abstract class Base {
	@Autowired
	public WebDriver driver;

	@PostConstruct
	public void InitBase() {
		PageFactory.initElements(driver, this);
	}

	protected void navigate(String url) {
		driver
				.navigate()
				.to(url);
	}

//	@Bean
//	public Wait<WebDriver> getWait() {
//		return new WebDriverWait(driver, 15);
//	}
//
//	@Bean
//	public Actions getActions() {
//		return new Actions(driver);
//	}

}
