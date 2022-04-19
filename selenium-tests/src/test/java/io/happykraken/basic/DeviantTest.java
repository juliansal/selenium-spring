package io.happykraken.basic;

import io.cucumber.spring.CucumberContextConfiguration;
import io.happykraken.basic.libraries.helpers.DeviantArtHomePage;
import io.happykraken.basic.libraries.helpers.DeviantArtLogin;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class DeviantTest {
    @Autowired
    private WebDriver webDriver;

}
