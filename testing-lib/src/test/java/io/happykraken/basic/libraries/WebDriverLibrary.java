package io.happykraken.basic.libraries;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverLibrary {

    @Bean
    public WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();

        return new ChromeDriver();
    }

    @Bean
    public void driverSettings() {
        getChromeDriver()
                .manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Bean
    public Wait<WebDriver> getWait() {
        return new WebDriverWait(getChromeDriver(), 20);
    }

    @Bean
    public Actions getActions() {
        return new Actions(getChromeDriver());
    }
}
