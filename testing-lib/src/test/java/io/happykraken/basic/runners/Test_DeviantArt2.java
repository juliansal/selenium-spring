package io.happykraken.basic.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.happykraken.basic.libraries.Hooks;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/io/happykraken/basic/features"},
        glue = "io.happykraken.basic.libraries",
        tags = "@DeviantArt"
)
public class Test_DeviantArt2 extends AbstractTestNGCucumberTests {


}
