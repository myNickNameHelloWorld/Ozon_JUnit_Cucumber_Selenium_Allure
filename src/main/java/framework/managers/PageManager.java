package framework.managers;

import framework.pages.BasketPage;
import framework.pages.SearchPage;
import framework.pages.StartPage;

public class PageManager {
    private static PageManager pageManager;
    private StartPage startPage;
    private SearchPage searchPage;
    private BasketPage basketPage;


    private PageManager() {

    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }


}
