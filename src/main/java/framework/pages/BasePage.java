package framework.pages;

import framework.managers.DriverManager;
import framework.managers.PageManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

    public BasePage() {
        PageFactory.initElements(driverManager.getWebDriver(), this);
    }

    @FindBy(xpath = "//input[@type='checkbox']/..")
    private List<WebElement> allCheckbox;

    @FindBy(xpath = "//div[@data-widget='searchResultsFiltersActive']/div/div/div/button/span/div/span")
    private List<WebElement> listActiveFilter;

    @FindBy(xpath = "//div[contains(text(), 'Бренды')]/../div/div/div/div/input")
    private WebElement searchBrand;

    @FindBy(xpath = "//div[contains(text(), 'Бренды')]/../div/div[2]")
    private WebElement brand;


    protected SearchPage checkboxBrand(String nameCheckbox) {
        int n = listActiveFilter.size();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@data-widget='searchResultsFiltersActive']/div/div/div/button/span/div/span"), n));
        waitUntilElementToBeClickable(searchBrand).click();
        actionsActions(searchBrand);
        searchBrand.sendKeys(nameCheckbox);
        if (brand.getText().toLowerCase().contains(("Ничего").toLowerCase())) {
            waitUntilElementToBeClickable(searchBrand).click();
        } else {
            WebElement el = brand.findElement(By.xpath(".//div/a[1]"));
            waitUntilElementToBeClickable(el).click();
            boolean checkFlag = wait.until(ExpectedConditions.elementToBeSelected(el.findElement(By.xpath("./label/input"))));
            Assertions.assertTrue(checkFlag, "Дополнительное условие '" + nameCheckbox + "' не выбрано");
        }
        return pageManager.getSearchPage();
    }

    protected SearchPage clickCheckbox(String nameCheckbox) {
        int n = listActiveFilter.size();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@data-widget='searchResultsFiltersActive']/div/div/div/button/span/div/span"), n));
        for (WebElement element : allCheckbox) {
            if (element.getText().toLowerCase().contains((nameCheckbox).toLowerCase())) {
                WebElement el1 = element.findElement(By.xpath("./div[1]"));
                waitUntilElementToBeClickable(el1).click();
                boolean checkFlag = wait.until(ExpectedConditions.elementToBeSelected(el1.findElement(By.xpath("./../input"))));
                Assertions.assertTrue(checkFlag, "Дополнительное условие '" + nameCheckbox + "' не выбрано");
                return pageManager.getSearchPage();
            }
        }
        Assertions.fail("Дополнительное условие '" + nameCheckbox + "' отсутствует на странице");
        return pageManager.getSearchPage();
    }

    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driverManager.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUntilVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected Boolean waitUntilInvisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected int strToInt(String str) {
        str = str.replaceAll("[^0-9]", "");
        return Integer.parseInt(str);
    }

    protected void actionsActions(WebElement element) {
        Actions actions = new Actions(driverManager.getWebDriver());
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
    }
}
