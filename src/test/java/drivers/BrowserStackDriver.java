package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {

    @Override
    @Nonnull
    public WebDriver createDriver(org.openqa.selenium.Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("app", "bs://sample.app"); // Замените на реальный app ID
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
        bstackOptions.setCapability("video", true);

        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL for BrowserStack", e);
        }
    }
}
