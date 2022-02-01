package framework.pages;

import framework.managers.DriverManager;
import framework.managers.PageManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getWebDriver(), Duration.ofSeconds(10), Duration.ofMillis(1000));

    @FindBy(xpath = "//input[@type='checkbox']/..")
    private List<WebElement> allCheckbox;



    @FindBy(xpath = "//div[contains(text(), 'Бренды')]/../div/div/div/a")
    private List<WebElement> listAllBrands;

    public BasePage() {
        PageFactory.initElements(driverManager.getWebDriver(), this);
    }

    protected void selectItem(List<WebElement> webElements, String value) {
        for (WebElement element : webElements) {
            if (element.getText().toLowerCase().contains((value).toLowerCase())) {
                waitUntilElementToBeClickable(element).click();
                return;
            }
        }
        Assertions.fail("Меню с названием '" + value + "' отсутствует на странице");
    }

    protected void fillInputField(WebElement field, String value) {
        waitUntilElementToBeClickable(field).click();
        field.sendKeys(value);
    }

    protected void checkField(WebElement element, String xPath, String value) {
        WebElement check = element.findElement(By.xpath(xPath));
        wait.until(ExpectedConditions.textToBePresentInElement(check, value));
        String checkStr = check.getText().replaceAll("[^\\,\\s\\d]+", "");
        Assertions.assertEquals(checkStr, value, "Сумма отличается");
    }

//    protected Boolean waitUntilTextToBePresent(WebElement check, String value) {
//        return wait.until(ExpectedConditions.textToBePresentInElement(check, value));
//    }



    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driverManager.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected WebElement clickElementJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driverManager.getWebDriver();
        js.executeScript("arguments[0].click();", element);
        return element;
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUntilVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }



//    protected WebElement waitUntilTextToBePresent(WebElement element, String text) {
//        return wait.until(ExpectedConditions.visibilityOf(element));
//    }

    protected Boolean waitUntilInvisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected int strToInt(String str) {
        str = str.replaceAll("[^0-9]", "");
        return Integer.parseInt(str);
    }

    protected void waitUntilSwitchText(WebElement element) {
        String str = element.getText();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, str)));
    }

    protected void actionsActions(WebElement element) {
        Actions actions = new Actions(driverManager.getWebDriver());
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
    }

//    public void actionsActions(WebElement element) {
//        Actions actions = new Actions(driverManager.getWebDriver());
//        actions.moveToElement(element).click(element).build().perform();
//    }

    protected void sleep(int millsec) {
        try {
            Thread.sleep(millsec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
