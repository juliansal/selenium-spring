package io.happykraken.basic;

import io.happykraken.basic.pages.DeviantArtHomePage;
import io.happykraken.basic.pages.DeviantArtLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicAppTests {

    @Autowired
    private WebDriver driver;

    @Autowired
    DeviantArtHomePage deviantArtHomePage;

    @Autowired
    DeviantArtLogin deviantArtLogin;

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void visitUserMenuPage() {
        deviantArtHomePage.navigateToHomePage();
        deviantArtHomePage.clickLogin();
        deviantArtLogin.login();
        deviantArtHomePage.clickUserMenu();
    }
}