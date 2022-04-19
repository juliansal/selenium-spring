package io.happykraken.basic.libraries;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverLibrary {
    private WebDriver webdriver;

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        webdriver = new ChromeDriver(chromeOptions);

        return webdriver;
    }

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "safari")
    public WebDriver getSafariDriver() {
        WebDriverManager.safaridriver().setup();
        webdriver = new SafariDriver();

        return webdriver;
    }

    @Bean
    public void driverSettings() {
        System.out.println("******** Initialize Driver Settings *********");
        webdriver
                .manage()
                .window()
                .setSize(new Dimension(1400, 1400));
        webdriver
                .manage()
                .timeouts()
                .implicitlyWait(100, TimeUnit.SECONDS);
    }

    @Bean
    public Wait<WebDriver> getWait() {
        return new WebDriverWait(webdriver, 20);
    }

    @Bean
    public Actions getActions() {
        return new Actions(webdriver);
    }

}
