package org.testmu.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmazonGalaxyTest {

    @Test
    public void testGalaxy() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        driver.get("https://www.amazon.in");

        // SEARCH
        WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox"))
        );
        searchBox.sendKeys("Samsung Galaxy", Keys.ENTER);

        // WAIT FOR PRODUCTS
        wait.until(d ->
                d.findElements(By.xpath("//a[contains(@href,'/dp/')]")).size() > 0
        );

        List<WebElement> products =
                driver.findElements(By.xpath("//a[contains(@href,'/dp/')]"));

        System.out.println("Products found: " + products.size());

        if (products.size() == 0) {
            driver.quit();
            return;
        }

        WebElement firstProduct = products.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

        // HANDLE TAB OR SAME PAGE
        String original = driver.getWindowHandle();
        Thread.sleep(3000);

        if (driver.getWindowHandles().size() > 1) {
            for (String tab : driver.getWindowHandles()) {
                if (!tab.equals(original)) {
                    driver.switchTo().window(tab);
                    break;
                }
            }
        }

        // GET PRICE (FINAL FIX)
        String price = "";
        try {
            WebElement priceElement = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//span[@class='a-price']//span[@class='a-offscreen']")
                    )
            );
            price = priceElement.getAttribute("innerText");

        } catch (Exception e1) {
            try {
                price = driver.findElement(By.id("priceblock_ourprice")).getText();
            } catch (Exception e2) {
                try {
                    price = driver.findElement(By.id("priceblock_dealprice")).getText();
                } catch (Exception e3) {
                    price = "Price not found";
                }
            }
        }

        System.out.println("Galaxy Price: " + price);

        // ADD TO CART
        try {
            WebElement addToCart = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@id='add-to-cart-button'] | //button[@id='add-to-cart-button']")
                    )
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

            System.out.println("Added to cart");

        } catch (Exception e) {
            System.out.println("Add to cart not available");
        }

        driver.quit();
    }
}