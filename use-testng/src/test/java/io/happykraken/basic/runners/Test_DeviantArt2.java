package io.happykraken.basic.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/io/happykraken/basic/features"},
        glue = "io.happykraken.basic.libraries",
        tags = "@DeviantArt2",
        plugin = {
                "json:target/cucumber/DeviantArt2/cucumber.json",
                "junit:target/cucumber/DeviantArt2/cucumber.xml",
                "html:target/cucumber/DeviantArt2/index.html",
                "pretty"
        }
)
public class Test_DeviantArt2 extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
