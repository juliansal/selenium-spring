package io.happykraken.basic.libraries;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Configuration
public class WebDriverLibrary {
    private WebDriver webdriver;

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "remoteChrome")
    public WebDriver getRemoteChromeDriver() throws MalformedURLException {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.ALL);
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.PERFORMANCE, Level.ALL);

        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logs);
        chromeOptions.setExperimentalOption("w3c", false);

        webdriver = WebDriverManager
                .chromedriver()
                .remoteAddress("http://192.168.1.230:4444")
                .capabilities(chromeOptions)
                .create();

        return webdriver;
    }

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
    @ConditionalOnProperty(name = "driver.settings", havingValue = "chrome")
    public void driverSettings() {
        System.out.println("******** Initialize Driver Settings *********");
        webdriver
                .manage()
                .window()
                .setSize(new Dimension(1400, 1400));
        webdriver
                .manage()
                .timeouts()
                .implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Bean
    public Wait<WebDriver> getWait() {
        return new WebDriverWait(webdriver, 15);
    }

    @Bean
    public Actions getActions() {
        return new Actions(webdriver);
    }

}
