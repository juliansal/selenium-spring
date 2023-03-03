package io.happykraken.basic.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/io/happykraken/basic/features"},
        glue = "io.happykraken.basic.libraries",
        tags = "@DeviantArt",
        plugin = {
                "json:target/cucumber/DeviantArt/cucumber.json",
                "junit:target/cucumber/DeviantArt/cucumber.xml",
                "html:target/cucumber/DeviantArt/index.html",
                "pretty"
        }
)
public class Test_DeviantArt extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
