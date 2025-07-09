package tests;

import config.BrowserStackConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.MutableCapabilities;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class SearchTests {

    BrowserStackConfig config = ConfigFactory.create(BrowserStackConfig.class);

    @Test
    void successfulSearchTest() throws Exception {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("app", "bs://sample.app");
        caps.setCapability("deviceName", "Samsung Galaxy S22 Ultra");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12.0");

        caps.setCapability("browserstackLocal", true);

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", "medinaakhundova_tMCgRe");
        bstackOptions.setCapability("accessKey", "24HrDp1DSAdVrzYxuSfx");
        bstackOptions.setCapability("projectName", "BrowserStack Sample");
        bstackOptions.setCapability("buildName", "browserstack-build-1");
        bstackOptions.setCapability("sessionName", "first_test");

        caps.setCapability("bstack:options", bstackOptions);

        AndroidDriver driver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Кликаем на кнопку "Search Wikipedia", чтобы открыть поле ввода
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));
        searchButton.click();

        // Теперь ищем само поле ввода, например, по resource-id или другому уникальному локатору
        // Например, в приложении Wikipedia это поле обычно имеет resource-id "org.wikipedia.alpha:id/search_src_text"
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));

        // Вводим текст
        searchInput.sendKeys("Appium");

        Thread.sleep(5000);

        // Проверяем, что результаты появились
        List<WebElement> results = driver.findElements(AppiumBy.className("android.widget.TextView"));
        assert (!results.isEmpty());

        driver.quit();
    }

    @Test
    void simpleSearchTest() throws Exception {

        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("app", "bs://sample.app"); // поменяйте на свой app URL
        caps.setCapability("deviceName", "Samsung Galaxy S22 Ultra");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12.0");

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", "medinaakhundova_tMCgRe");
        bstackOptions.setCapability("accessKey", "24HrDp1DSAdVrzYxuSfx");
        caps.setCapability("bstack:options", bstackOptions);

        AndroidDriver driver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Ждем и кликаем кнопку поиска
        WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia"))
        );
        searchButton.click();

        // Ждем поле ввода и вводим "Appium"
        WebElement searchInput = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
        );
        searchInput.sendKeys("Appium");

        // Ждем, что появятся результаты — ищем хотя бы один TextView с текстом
        List<WebElement> results = wait.until(driver1 -> {
            List<WebElement> elems = driver1.findElements(AppiumBy.className("android.widget.TextView"));
            return elems.isEmpty() ? null : elems;
        });

        assert !results.isEmpty();

        driver.quit();
    }
}
