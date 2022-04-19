package io.happykraken.basic.libraries.helpers;

import io.happykraken.basic.libraries.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Page extends Base {

    @FindBy(tagName = "a")
    private List<WebElement> link;

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void clickLink(String name) {
        link.stream()
                .filter(WebElement::isDisplayed)
                .filter(el -> el.getText().contentEquals(name))
                .findFirst()
                .ifPresentOrElse(WebElement::click, () -> Assert.fail("Link was not present"));
    }

    public void shouldSeeText(String text) {
        By byText = By.xpath("//*[contains(text(), '"+text+"')]");

        assertThatCode(() -> driver.findElement(byText))
                .doesNotThrowAnyException();

        assertThat(driver.findElement(byText).isDisplayed())
                .isTrue();
    }
}
