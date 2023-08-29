package io.happykraken.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.happykraken.cucumber.helpers.PageAssertions;
import io.happykraken.cucumber.helpers.PageInteractions;

public class Steps extends Base {
	PageInteractions pageInteractions = new PageInteractions();
	PageAssertions pageAssertions = new PageAssertions();

	@And("wait {} seconds")
	public void wait_seconds(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("I log in using credentials:")
	public void i_log_in_using_credentials(DataTable credentials) {

		credentials
				.asMaps()
				.stream()
				.findFirst()
				.ifPresent(row -> {
					String username = row.get("username");
					String password = row.get("password");

					pageInteractions.login(username, password);
				});
	}

	@Given("I navigate to {string}")
	public void i_navigate_to(String url) {
		pageInteractions.navigateTo(url);
	}

	@Given("I click on {string}")
	public void i_click_on(String linkName) {
		pageInteractions.clickLink(linkName);
	}

	@Then("I should see {string}")
	public void i_should_see(String text) {
		pageAssertions.shouldSeeText(text);
	}
}
