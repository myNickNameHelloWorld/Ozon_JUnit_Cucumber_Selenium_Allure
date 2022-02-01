package framework.utils;

import framework.managers.DriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Date;

public class MyAllureListener implements TestWatcher {
    protected DriverManager driverManager = DriverManager.getInstance();

    public byte[] getScreenshot() {
        return ((TakesScreenshot) driverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment("Screenshot" + new Date(), "image/png", "png", getScreenshot());
    }
}
