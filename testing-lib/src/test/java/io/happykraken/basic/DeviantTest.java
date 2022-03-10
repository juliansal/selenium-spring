package io.happykraken.basic;

import io.happykraken.basic.libraries.helpers.DeviantArtHomePage;
import io.happykraken.basic.libraries.helpers.DeviantArtLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeviantTest {
    @Autowired
    DeviantArtHomePage deviantArtHomePage;

    @Autowired
    DeviantArtLogin deviantArtLogin;

    public void visitUserMenuPage() {
        deviantArtHomePage.navigateToHomePage();
        deviantArtHomePage.clickLogin();
        deviantArtLogin.login();
        deviantArtHomePage.clickUserMenu();
    }
}
