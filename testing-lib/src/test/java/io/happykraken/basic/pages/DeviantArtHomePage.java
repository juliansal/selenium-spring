package io.happykraken.basic.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviantArtHomePage extends Base {
    @Autowired
    private Actions actions;

    @FindBy(css = "a[href*='/users/login']")
    private WebElement linkLogin;

    @FindBy(tagName = "button")
    private List<WebElement> buttons;

    @Value("${app.url}")
    private String url;

    public void navigateToHomePage() {
        navigate(url);
    }

    public void clickLogin() {
        linkLogin.click();

        System.out.println("Clicking on Log In button");
    }

    public void clickUserMenu() {
        WebElement linkUserMenu = buttons
                .stream()
                .filter(WebElement::isDisplayed)
                .filter(el -> el.getText().equals("User Menu"))
                .findFirst().get();

        actions
                .moveToElement(linkUserMenu)
                .pause(500)
                .click();

        System.out.println("Clicking on User Menu button");
    }


}
