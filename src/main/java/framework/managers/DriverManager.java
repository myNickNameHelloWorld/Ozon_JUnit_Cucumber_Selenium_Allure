package framework.managers;

import framework.utils.PropsConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class DriverManager {
    private static DriverManager INSTANCE = null;
    private WebDriver webDriver;
    TestPropManager testPropManager = TestPropManager.getInstance();

    private DriverManager() {

    }

    public static DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            initDriver();
        }
        return webDriver;
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    private void initDriver() {

        if (testPropManager.getProperty(PropsConst.TYPE_BROWSER).equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", testPropManager.getProperty(PropsConst.PATH_GECKO_DRIVER));
            webDriver = new FirefoxDriver();
        } else if (testPropManager.getProperty(PropsConst.TYPE_BROWSER).equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", testPropManager.getProperty(PropsConst.PATH_CHROME_DRIVER));
            webDriver = new ChromeDriver();
        } else if (testPropManager.getProperty(PropsConst.TYPE_BROWSER).equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            capabilities.setVersion("75.0");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);

            try {
                webDriver = new RemoteWebDriver(
                        URI.create("http://164.92.227.174:4444/wd/hub").toURL(),
                        capabilities
                );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

}
