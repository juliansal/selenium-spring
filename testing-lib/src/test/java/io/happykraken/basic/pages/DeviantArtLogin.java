package io.happykraken.basic.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeviantArtLogin extends Base {

    @FindBy(css = "input#username")
    private WebElement usernameField;

    @FindBy(css = "input#password")
    private WebElement passwordField;

    @FindBy(css = "button#loginbutton span")
    private WebElement btnLogin;

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    public DeviantArtLogin login() {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        btnLogin.click();

        System.out.println("Logging into DeviantArt with " + username);
        return new DeviantArtLogin();
    }

}
