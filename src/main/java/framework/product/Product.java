package framework.product;

import java.util.ArrayList;
import java.util.List;

public class Product {
    String name;
    int price;

    public static List<Product> list = new ArrayList<>();


    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public int getPrice() {
        return price;
    }

    public static List<String> getNames() {
        List<String> listOfNames = new ArrayList<>();
        for (Product product: list) {
            listOfNames.add(product.name);
        }
        return listOfNames;
    }
}
