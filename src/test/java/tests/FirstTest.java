package tests;

import framework.product.Product;
import org.junit.jupiter.api.Test;
import tests.base.BaseTests;

public class FirstTest extends BaseTests {


    @Test
    public void test1() {
        pageManager.getStartPage()
                .checkTittle()
                .clickSearchInput("iphone")
                .priceTo("Цена", 100000)
                .clickCheckbox("NFC")
                .clickCheckbox("Высокий рейтинг")
                .addProduct(8)
                .clickBasket()
                .checkBucket()
                .checkValueProductInBasket()
                .deleteAllProducts()
                .checkAfterDelete();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
