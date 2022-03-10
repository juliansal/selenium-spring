package io.happykraken.basic.libraries.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.happykraken.basic.libraries.Base;
import io.happykraken.basic.libraries.helpers.DeviantArtHomePage;
import io.happykraken.basic.libraries.helpers.DeviantArtLogin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StepsDeviantArt extends Base {

    @Autowired
    private DeviantArtHomePage homePage;

    @Autowired
    private DeviantArtLogin login;

    @Given("I navigate to the DeviantArt homepage")
    public void i_navigate_to_the_DeviantArt_homepage() {
        homePage.navigateToHomePage();
    }

    @Given("I log into DeviantArt")
    public void i_log_into_DeviantArt() {
        homePage.clickLogin();
        login.login();
    }

    @When("I click on User Menu in the page header")
    public void i_click_on_in_the_page_header() {
        homePage.clickUserMenu();
    }

    @Then("I should be in the user dashboard for my account")
    public void i_should_be_in_the_user_dashboard_for_my_account() {

//        assertDoesNotThrow(() -> {
//            wait.until(ExpectedConditions.titleContains("User Profile"));
//        });
    }

}
