package io.happykraken.cucumber.helpers;

import io.happykraken.cucumber.Base;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class PageAssertions extends Base {

	public void shouldSeeText(String text) {
		By byText = By.xpath("//*[contains(text(), '"+text+"')]");

		assertThatCode(() -> webdriver.findElement(byText))
				.doesNotThrowAnyException();

		assertThat(webdriver.findElement(byText).isDisplayed())
				.isTrue();
	}
}
