package io.happykraken.basic.libraries;

import io.cucumber.java.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;

public class Hooks {
    @Autowired
    private WebDriver driver;

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario fail. Take a picture.");
            driver
                    .manage()
                    .window()
                    .setSize(new Dimension(1000, 1000));
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (Exception e) {
                System.out.println("**** The Screenshot Failed ****" + e.getMessage());
            }
        }
        System.out.println("****** Browser is quiting. ******");
    }
}
