package framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//div[@id='stickyHeader']")
    private WebElement headMenu;


    public StartPage checkTittle() {
        Assertions.assertEquals(driverManager.getWebDriver().getTitle(),
                "OZON — интернет-магазин. Миллионы товаров по выгодным ценам",
                "Открыта не та страница");
        return pageManager.getStartPage();
    }

    public SearchPage clickSearchInput(String product) {
        WebElement searchInput = headMenu.findElement(By.xpath("./div/div/form/div/input[@name='text']"));
        waitUntilElementToBeClickable(searchInput).sendKeys(product, Keys.ENTER);
        Assertions.assertEquals(product, searchInput.getAttribute("value"), "Поле поиска заполнено некорректно");
        return pageManager.getSearchPage();
    }
}
