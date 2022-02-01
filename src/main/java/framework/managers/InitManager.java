package framework.managers;

import java.time.Duration;

import static framework.utils.PropsConst.IMPLICITLY_WAIT;
import static framework.utils.PropsConst.PAGE_LOAD_TIMEOUT;

public class InitManager {
    private static final TestPropManager props = TestPropManager.getInstance();

    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        driverManager.getWebDriver().manage().window().maximize();
        driverManager.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT))));
        driverManager.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT))));
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
