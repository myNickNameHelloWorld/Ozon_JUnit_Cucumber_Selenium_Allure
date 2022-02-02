package framework.steps;

import framework.managers.DriverManager;
import framework.managers.InitManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Hooks {
    protected DriverManager driverManager = DriverManager.getInstance();

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("failureScreenshot", "image/png", addScreenshot(), "png");
        }
        InitManager.quitFramework();
    }

    private InputStream addScreenshot() {
        byte[] screenshot = ((TakesScreenshot) driverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }
}
