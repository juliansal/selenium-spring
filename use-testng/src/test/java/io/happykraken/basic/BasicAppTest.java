package io.happykraken.basic;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicAppTest {
    @Autowired
    private WebDriver webDriver;

}
