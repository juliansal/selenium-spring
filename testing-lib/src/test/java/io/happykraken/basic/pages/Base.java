package io.happykraken.basic.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class Base {

    @Autowired
    public WebDriver driver;

    @Autowired
    public Wait<WebDriver> wait;

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
