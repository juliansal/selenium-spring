package io.happykraken.basic.libraries.helpers;

import io.happykraken.basic.libraries.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeviantArtHomePage extends Base {

    @FindBy(css = "a[href*='/users/login']")
    private WebElement linkLogin;

    @FindBy(css = "header button")
    private List<WebElement> buttons;

    @Value("${app.url}")
    private String url;

    public void navigateToHomePage() {
        navigate(url);
    }

    public void clickLogin() {
        Actions actions = new Actions(driver);
        actions
                .click(linkLogin)
                .build()
                .perform();

        System.out.println("Clicking on Log In button");
    }

    public void clickUserMenu() {
        Actions actions = new Actions(driver);
        Optional<WebElement> linkUserMenu = buttons
                .stream()
                .filter(WebElement::isDisplayed)
                .filter(el -> el.getText().equals("User Menu"))
                .findFirst();

        actions
                .click(linkUserMenu.get())
                .build()
                .perform();

        System.out.println("Clicking on User Menu button");
    }

}
