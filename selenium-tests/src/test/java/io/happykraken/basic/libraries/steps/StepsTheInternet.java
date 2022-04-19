package io.happykraken.basic.libraries.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.happykraken.basic.libraries.helpers.TheInternet;
import org.springframework.beans.factory.annotation.Autowired;

public class StepsTheInternet {

    @Autowired
    private TheInternet theInternet;

    @When("I log in using credentials:")
    public void i_log_in_using_credentials(DataTable credentials) {

        credentials
                .asMaps()
                .stream()
                .findFirst()
                .ifPresent(row -> {
                    String username = row.get("username");
                    String password = row.get("password");

                    theInternet.login(username, password);
                });
    }
}
