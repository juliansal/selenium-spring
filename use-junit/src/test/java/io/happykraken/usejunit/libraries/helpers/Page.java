package io.happykraken.usejunit.libraries.helpers;


import io.cucumber.spring.CucumberTestContext;
import io.happykraken.usejunit.libraries.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class Page extends Base {

	public void navigateTo(String url) {
		navigate(url);;
	}

	public void clickLink(String name) {
		By byLink = By.tagName("a");

		driver
				.findElements(byLink)
				.stream()
				.filter(WebElement::isDisplayed)
				.filter(el -> el.getText().contentEquals(name))
				.findFirst()
				.ifPresentOrElse(WebElement::click, () -> fail("Failed to find link to click"));
	}

	public void shouldSeeText(String text) {
		By byText = By.xpath("//*[contains(text(), '"+text+"')]");

		assertThatCode(() -> driver.findElement(byText))
				.doesNotThrowAnyException();

		assertThat(driver.findElement(byText).isDisplayed())
				.isTrue();
	}
}
