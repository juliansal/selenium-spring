package io.happykraken.basic;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = BasicApp.class)
public class DeviantArtTestNG_2 extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebDriver driver;

    @Autowired
    private DeviantTest deviantTest;

    @AfterTest
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void visitUserMenuPage() {
        deviantTest.visitUserMenuPage();
    }

    @BeforeClass(alwaysRun = true)
    @BeforeSuite(alwaysRun = true)
    @Override
    public void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }
}
