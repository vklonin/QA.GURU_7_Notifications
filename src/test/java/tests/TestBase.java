package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Attach;
import static config.Credentials.credentialsConfig;

public class TestBase {

    @BeforeAll
    static void setup(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.remote = "https://"+ credentialsConfig.login() +":"+ credentialsConfig.password() + "@" + System.getProperty("remoteWD"); //"https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }

    @AfterEach
    void addAttachments(){
        Attach.screenshotAs("last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
