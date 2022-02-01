package framework.pages;

import framework.product.Product;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//aside/*")
    private List<WebElement> columnSubMenu;

    @FindBy(xpath = "//input[@type='checkbox']/..")
    private List<WebElement> allCheckbox;

    @FindBy(xpath = "//div[contains(@class, 'container nh')]/div/div/div/div/div/div/div/button")
    private List<WebElement> allButtonBuy;

    @FindBy(xpath = "//span[contains(text(), 'Магнитогорск')]")
    private WebElement jsScroll;

    @FindBy(xpath = "//div[@id='stickyHeader']/div/a/span[@class='f-caption--bold u6b']")
    private WebElement checkBasket;

    @FindBy(xpath = "//div[@value='Уцененный товар']")
    private WebElement forWait;

    @FindBy(xpath = "//div[contains(text(), 'Бренды')]/../div/span")
    private WebElement allBrands;


    public SearchPage priceTo(String nameMenu, Integer value) {
        for (WebElement element : columnSubMenu) {
            if (element.getText().toLowerCase().contains((nameMenu.toLowerCase()))) {
                WebElement priceTo = element.findElement(By.xpath("./div[contains(text(), 'Цена')]/../div/div/div/div/p[contains(text(), 'до')]/../input"));
                waitUntilElementToBeClickable(priceTo).click();
                actionsActions(priceTo);
                priceTo.sendKeys(value.toString());
                Assertions.assertEquals(value.toString(), priceTo.getAttribute("value"), "Введена некорректная цена");
                return this;
            }
        }
        Assertions.fail("Поле с названием '" + nameMenu + "' не найдено.");
        return pageManager.getSearchPage();
    }

    public SearchPage allBrandsClick() {
        waitUntilElementToBeClickable(allBrands).click();
        return pageManager.getSearchPage();
    }


    public SearchPage clickCheckbox(String nameCheckbox) {
        sleep(1500);
//        int n = allCheckbox.size();
//        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//input[@type='checkbox']/.."), n));
        for (WebElement element : allCheckbox) {
            if (element.getText().toLowerCase().contains((nameCheckbox).toLowerCase())) {
                WebElement el1 = element.findElement(By.xpath("./div[1]"));
                waitUntilElementToBeClickable(el1).click();
                boolean checkFlag = wait.until(ExpectedConditions.elementToBeSelected(el1.findElement(By.xpath("./../input"))));
                Assertions.assertTrue(checkFlag, "Дополнительное условие '" + nameCheckbox + "' не выбрано");
                return pageManager.getSearchPage();
            }
        }
        // Убрал assert, чтобы при отсутвии определенного бренда продолжал добавлять нужное
        //Assertions.fail("Дополнительное условие '" + nameCheckbox + "' отсутствует на странице");
        return pageManager.getSearchPage();
    }


    public SearchPage addProduct(int count) {
        waitUntilInvisibilityOf(forWait);
        scrollToElementJs(jsScroll);
        waitUntilVisibilityOf(jsScroll);
        int i = 1;
        int n = 1;
        for (WebElement element : allButtonBuy) {
            if (i % 2 == 0 && i <= count * 2) {
                WebElement productName = element.findElement(By.xpath("./../../../../../../div/div/a/span"));
                WebElement productPrice = element.findElement(By.xpath("./../../../../../div/span[@style='color: rgb(249, 17, 85);' or @style='color: rgb(0, 26, 52);'][1]"));
                Product.list.add(new Product(productName.getText(), strToInt(productPrice.getText())));
                waitUntilVisibilityOf(element).click();
                wait.until(ExpectedConditions.textToBePresentInElement(checkBasket, n + ""));
                n++;
            } else if (i > count * 2) {
                return this;
            }
            i++;
        }
        return pageManager.getSearchPage();
    }

    public SearchPage addProduct() {
        waitUntilInvisibilityOf(forWait);
        scrollToElementJs(jsScroll);
        waitUntilVisibilityOf(jsScroll);
        int i = 1;
        int n = 1;
        int size = allButtonBuy.size();
        for (WebElement element : allButtonBuy) {
            if (i % 2 == 0 && i <= size) {
                WebElement productName = element.findElement(By.xpath("./../../../../../../div/div/a/span"));
                WebElement productPrice = element.findElement(By.xpath("./../../../../../div/span[@style='color: rgb(249, 17, 85);' or @style='color: rgb(0, 26, 52);'][1]"));
                Product.list.add(new Product(productName.getText(), strToInt(productPrice.getText())));
                waitUntilVisibilityOf(element).click();
                wait.until(ExpectedConditions.textToBePresentInElement(checkBasket, n + ""));
                n++;
            } else if (i > size) {
                return this;
            }
            i++;
        }
        return pageManager.getSearchPage();
    }

    public BasketPage clickBasket() {
        waitUntilElementToBeClickable(checkBasket.findElement(By.xpath("./.."))).click();
        return pageManager.getBasketPage();
    }
}

