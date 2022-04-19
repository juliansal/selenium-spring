package io.happykraken.basic.libraries.helpers;

import io.happykraken.basic.libraries.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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

    public void login() {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        Actions actions = new Actions(driver);

        actions
                .click(btnLogin)
                .build()
                .perform();

        System.out.println("Logging into DeviantArt with " + username);
    }

}
