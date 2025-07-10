package tests.utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AttachmentHelper {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(AndroidDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page Source", type = "text/xml")
    public static String attachPageSource(AndroidDriver driver) {
        return driver.getPageSource();
    }

    @Attachment(value = "Device Logs", type = "text/plain")
    public static String attachLogs(String logs) {
        return logs;
    }

    @Attachment(value = "BrowserStack video", type = "text/html", fileExtension = ".html")
    public static String attachVideoLink(String sessionId) {
        String videoUrl = "https://app-automate.browserstack.com/sessions/" + sessionId + ".json";
        return "<html><body><a href='" + videoUrl + "' target='_blank'>Watch Video</a></body></html>";
    }
}
