package io.happykraken.basic.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/io/happykraken/basic/features"},
        glue = "io.happykraken.basic.libraries",
        tags = "@TheInternet",
        plugin = {
                "message:target/cucumber/TheInternet/message.txt",
                "pretty",
                "json:target/cucumber/TheInternet/cucumber.json",
                "junit:target/cucumber/TheInternet/cucumber.xml",
                "html:target/cucumber/TheInternet/index.html"
        }
)
public class Test_TheInternet extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
