package io.happykraken.basic;

import io.happykraken.basic.libraries.helpers.DeviantArtHomePage;
import io.happykraken.basic.libraries.helpers.DeviantArtLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
class DeviantArtTest {

    @Autowired
    private WebDriver driver;

    @Autowired
    DeviantArtHomePage deviantArtHomePage;

    @Autowired
    DeviantArtLogin deviantArtLogin;

    @BeforeEach
    void background() {
        deviantArtHomePage.navigateToHomePage();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }

    @Test
    void visitUserMenuPage() {
        deviantArtHomePage.clickLogin();
        deviantArtLogin.login();
        deviantArtHomePage.clickUserMenu();
    }
}