package io.happykraken.basic.libraries;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {
    @Autowired
    private WebDriver driver;

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario fail. Take a picture.");
        }
        System.out.println("****** Browser is quiting. ******");
//        if (driver != null) {
//            driver.quit();
//        }
    }

}
