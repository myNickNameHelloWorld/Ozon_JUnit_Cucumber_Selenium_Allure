package framework.steps;

import framework.managers.PageManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;

import java.util.List;

public class Steps {
    private PageManager pageManager = PageManager.getPageManager();

    @Когда("^Загружена стартовая страница$")
    public void getStartPage() {
        pageManager.getStartPage();
    }

    @Когда("^Проверка страницы$")
    public void checkTittle() {
        pageManager.getStartPage().checkTittle();
    }

    @Когда("^Поиск '(.*)'$")
    public void clickSearchInput(String product) {
        pageManager.getStartPage().clickSearchInput(product);
    }

    @Когда("^Заполнение поля 'Цена до'$")
    public void priceTo(DataTable dataTable) {
        dataTable.asMap(String.class, Integer.class)
                .forEach((key, value) -> {
                    pageManager.getSearchPage().priceTo((String) key, (Integer) value);
                });
    }

    @Когда("^Просмотров всех брендов$")
    public void allBrandsClick() {
        pageManager.getSearchPage().allBrandsClick();
    }

    @Когда("^Выбор дополнительного параметра$")
    public void choiceCheckbox(List<String> arg) {
        for (String s : arg) {
            pageManager.getSearchPage().choiceCheckbox(s);
        }
    }

    @Когда("^Добавление товара в количестве '(\\d*)' штук$")
    public void addProduct(int count) {
        pageManager.getSearchPage().addProduct(count);
    }

    @Когда("^Добавление всех товаров$")
    public void addProduct() {
        pageManager.getSearchPage().addProduct();
    }

    @Когда("^Переход в корзину$")
    public void clickBasket() {
        pageManager.getSearchPage().clickBasket();
    }

    @Когда("^Проверка ранее добавленых товаров в корзине$")
    public void checkBucket() {
        pageManager.getBasketPage().checkBucket();
    }

    @Когда("^Проверка отображения текста$")
    public void checkValueProductInBasket() {
        pageManager.getBasketPage().checkValueProductInBasket();
    }

    @Когда("^Удаление всех товаров$")
    public void deleteAllProducts() {
        pageManager.getBasketPage().deleteAllProducts();
    }

    @Когда("^Проверка после удаления$")
    public void checkAfterDelete() {
        pageManager.getBasketPage().checkAfterDelete();
    }
}
