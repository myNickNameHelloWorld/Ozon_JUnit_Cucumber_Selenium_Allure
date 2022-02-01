package tests.base;

import org.junit.jupiter.api.Test;

public class SecondTest extends BaseTests {
    @Test
    public void test2() {
        pageManager.getStartPage()
                .checkTittle()
                .clickSearchInput("беспроводные наушники")
                .priceTo("Цена", 10000)
                .allBrandsClick()
                .clickCheckbox("Beats")
                .clickCheckbox("Samsung")
                .clickCheckbox("Xiaomi")
                .clickCheckbox("Высокий рейтинг")
                .addProduct()
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
