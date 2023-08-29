package io.happykraken.usejunit.libraries.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.happykraken.usejunit.libraries.helpers.Page;
import org.springframework.beans.factory.annotation.Autowired;

public class StepsPage {
	@Autowired
	private Page page;

	@Given("I navigate to {string}")
	public void i_navigate_to(String url) {
		page.navigateTo(url);
	}

	@Given("I click on {string}")
	public void i_click_on(String linkName) {
		page.clickLink(linkName);
	}

	@Then("I should see {string}")
	public void i_should_see(String text) {
		page.shouldSeeText(text);
	}
}
