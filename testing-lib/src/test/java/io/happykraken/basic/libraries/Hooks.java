package io.happykraken.basic.libraries;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {
    @Autowired
    private static WebDriver driver;

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario fail. Take a picture.");
        }
        System.out.println("****** Browser is quiting. ******");
        if (driver != null) {
            driver.quit();
        }
    }

}
