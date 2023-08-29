package io.happykraken.cucumber.runners;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.happykraken.cucumber.Base;
import io.happykraken.cucumber.helpers.Hooks;
import org.junit.platform.suite.api.*;
import org.openqa.selenium.*;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber/FormAuthentication.html, json:target/cucumber/FormAuthentication.json, junit:target/cucumber/FormAuthentication.xml")
@IncludeTags({"FormAuthentication"})
public class FormAuthenticationTest {
	private WebDriver webdriver;

	@Before
	public void setup() {
		webdriver = Hooks.startChromeInstance();
		Base.setWebDriver(webdriver);
	}

	@After
	public void tearDown(Scenario scenario) {
		Hooks.endInstance(scenario, webdriver);
	}
}
