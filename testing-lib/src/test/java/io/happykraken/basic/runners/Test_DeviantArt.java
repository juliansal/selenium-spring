package io.happykraken.basic.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/io/happykraken/basic/features"},
        glue = "io.happykraken.basic.libraries",
        tags = "@DeviantArt"
)
public class Test_DeviantArt extends AbstractTestNGCucumberTests {


}
