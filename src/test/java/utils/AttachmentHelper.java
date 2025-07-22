package utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.BrowserstackHelper;


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
    public static String attachVideo(String sessionId) {
        String videoUrl = BrowserstackHelper.getBrowserstackVideoUrl(sessionId);
        return "<html><body><video width='100%' height='500' controls><source src='"
                + videoUrl
                + "' type='video/mp4'></video></body></html>";
    }
}
