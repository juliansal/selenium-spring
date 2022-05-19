package io.happykraken.basic.libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;

public abstract class Base {
    @Autowired
    public WebDriver driver;

    @PostConstruct
    public void InitBase() {
        PageFactory.initElements(driver, this);
    }

    protected void navigate(String url) {
        driver
                .navigate()
                .to(url);
    }

}
