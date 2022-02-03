package framework.steps;

import framework.managers.DriverManager;
import framework.managers.InitManager;
import framework.product.Product;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {
    protected DriverManager driverManager = DriverManager.getInstance();

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            Allure.addAttachment("failureScreenshot", "image/png", addScreenshot(), "png");
        }
        Allure.addAttachment("Список товаров", "plain/text", new ByteArrayInputStream(addTxt()), ".txt");
        InitManager.quitFramework();
    }

    private InputStream addScreenshot() {
        byte[] screenshot = ((TakesScreenshot) driverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }

    private byte[] addTxt() {
        Product maxProduct = Product.list.stream()
                .max(Product::compareTo)
                .get();
        File report = new File("src/main/resources/report.txt");
        try {
            FileWriter writer = new FileWriter(report);
            writer.write("Товар с наибольшей ценой '" + maxProduct.getName() + "' цена: " + maxProduct.getPrice() + " ₽\n\n");
            writer.write("Весь список товаров: \n");
            for (Product product : Product.list) {
                writer.write(product.toString());
            }
            writer.close();
            return Files.readAllBytes(Paths.get("src/main/resources/report.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
