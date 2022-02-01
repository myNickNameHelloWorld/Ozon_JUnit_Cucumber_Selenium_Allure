package framework.pages;

import framework.product.Product;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static framework.product.Product.getNames;

public class BasketPage extends BasePage {
    @FindBy(xpath = "//div[@class='aq5 aq6']/a/span")
    private List<WebElement> listProduct;

    @FindBy(xpath = "//div[@class='bm8']/div[@class='b0n']/div/button")
    private WebElement clickCloseBanner;

    @FindBy(xpath = "//span[contains(text(), 'Ваша корзина')]/..")
    private WebElement productInBasket;

    @FindBy(xpath = "//span[contains(text(), 'Удалить выбранные')]")
    private WebElement clickDelete;

    @FindBy(xpath = "//div[contains(text(), 'Удаление')]/../div/div/button")
    private WebElement yesClickDelete;

    @FindBy(xpath = "//h1[contains(text(), 'Корзина пуста')]")
    private WebElement checkBasketAfterDelete;



    public BasketPage checkBucket() {
        waitUntilElementToBeClickable(clickCloseBanner).click();
        sleep(2000);
        List<String> productNamesInCart = new ArrayList<>();
        for (WebElement element: listProduct) {
            productNamesInCart.add(element.getText());
        }
        Assertions.assertTrue(productNamesInCart.containsAll(getNames()));

        return pageManager.getBasketPage();
    }

    public BasketPage checkValueProductInBasket() {
        waitUntilVisibilityOf(productInBasket);
        Assertions.assertTrue(productInBasket.getText().contains(Product.list.size() + " товаров"), "Не все товары в корзине");
        return pageManager.getBasketPage();
    }

    public BasketPage deleteAllProducts() {
        waitUntilElementToBeClickable(clickDelete).click();
        waitUntilElementToBeClickable(yesClickDelete).click();
        return pageManager.getBasketPage();
    }

    public BasketPage checkAfterDelete() {
        waitUntilInvisibilityOf(yesClickDelete);
        Assertions.assertEquals("Корзина пуста", checkBasketAfterDelete.getText(), "Корзина не пуста после удаления");
        return pageManager.getBasketPage();
    }

}
