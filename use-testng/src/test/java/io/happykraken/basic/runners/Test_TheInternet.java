package io.happykraken.basic.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@CucumberOptions(
        features = {"src/test/java/io/happykraken/basic/features"},
        glue = "io.happykraken.basic.libraries",
        tags = "@TheInternet",
        plugin = {
                "json:target/cucumber/TheInternet/cucumber.json",
                "junit:target/cucumber/TheInternet/cucumber.xml"
        }
)
public class Test_TheInternet extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
