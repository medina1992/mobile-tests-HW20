package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserStackDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;
import tests.utils.AttachmentHelper;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserStackDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open(); // запускает сессию и привязывает WebDriver
    }

    @AfterEach
    void addAttachments() {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();

        // 🔽 Вызов методов для Allure
        AttachmentHelper.attachScreenshot(driver);
        AttachmentHelper.attachPageSource(driver);
        AttachmentHelper.attachLogs("Test finished on device: " + driver.getCapabilities().getCapability("deviceName"));

        // 🔽 Вставка ссылки на видео сессии из BrowserStack
        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        AttachmentHelper.attachVideoLink(sessionId);

        // ❗ Только после всех аттачей
        closeWebDriver();
    }
}
