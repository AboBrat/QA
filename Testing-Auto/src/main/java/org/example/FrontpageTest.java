package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontpageTest {
    WebDriver driver;

    public FrontpageTest(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Verification of all item titles on frontpage
     */
    public void verifyTitles() {
        List<WebElement> titles = driver.findElements(By.className("inventory_item_name"));
        int listSize = titles.size();
        for(int i = 0; i < listSize; i++) {
            String currentTitle = titles.get(i).getText();
            switch (i) {
                case 0:
                    assertEquals("Sauce Labs Backpack", currentTitle);
                    break;
                case 1:
                    assertEquals("Sauce Labs Bike Light", currentTitle);
                    break;
                case 2:
                    assertEquals("Sauce Labs Bolt T-Shirt", currentTitle);
                    break;
                case 3:
                    assertEquals("Sauce Labs Fleece Jacket", currentTitle);
                    break;
                case 4:
                    assertEquals("Sauce Labs Onesie", currentTitle);
                    break;
                case 5:
                    assertEquals("Test.allTheThings() T-Shirt (Red)", currentTitle);
                    break;
            }
        }
    }

    /*
     * Verification of all item prices on frontpage
     */
    public void verifyPrices() {
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        int listSize = prices.size();
        for(int i = 0; i < listSize; i++) {
            String currentTitle = prices.get(i).getText();
            switch (i) {
                case 0:
                    assertEquals("$29.99", currentTitle);
                    break;
                case 1:
                    assertEquals("$9.99", currentTitle);
                    break;
                case 2:
                case 5: {
                    assertEquals("$15.99", currentTitle);
                    break;
                }
                case 3:
                    assertEquals("$49.99", currentTitle);
                    break;
                case 4:
                    assertEquals("$7.99", currentTitle);
                    break;
            }
        }
    }

    /*
     * Verification of all item images on frontpage
     */
    public void verifyImages() {
        List<WebElement> images = driver.findElements(By.className("inventory_item_img"));
        int listSize = images.size();
        for(int i = 0; i < listSize; i++) {
            String currentImage = images.get(i).getAttribute("src");
            switch (i) {
                case 1:
                    assertEquals("https://www.saucedemo.com/static/media/sauce-backpack-1200x1500.34e7aa42.jpg", currentImage);
                    break;
                case 3:
                    assertEquals("https://www.saucedemo.com/static/media/bike-light-1200x1500.a0c9caae.jpg", currentImage);
                    break;
                case 5:
                    assertEquals("https://www.saucedemo.com/static/media/bolt-shirt-1200x1500.c0dae290.jpg", currentImage);
                    break;
                case 7:
                    assertEquals("https://www.saucedemo.com/static/media/sauce-pullover-1200x1500.439fc934.jpg", currentImage);
                    break;
                case 9:
                    assertEquals("https://www.saucedemo.com/static/media/red-onesie-1200x1500.1b15e1fa.jpg", currentImage);
                    break;
                case 11:
                    assertEquals("https://www.saucedemo.com/static/media/red-tatt-1200x1500.e32b4ef9.jpg", currentImage);
                    break;
            }
        }
    }

    /*
     * Testing Z to A sort on frontpage
     */
    public void checkSortZtoA() {
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.xpath("//option[@value='za']")).click();
        List<WebElement> titles = driver.findElements(By.className("inventory_item_name"));
        int listSize = titles.size();
        for(int i = 1; i < listSize; i++) {
            String s1 = titles.get(i - 1).getText();
            String s2 = titles.get(i).getText();
            if(s1.compareTo(s2) < 0) {
                throw new AssertionError("Items not sorted well");
            }
        }
    }

    /*
     * Testing A to Z sort on frontpage
     */
    public void checkSortAtoZ() {
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.xpath("//option[@value='az']")).click();
        List<WebElement> titles = driver.findElements(By.className("inventory_item_name"));
        int listSize = titles.size();
        for(int i = 1; i < listSize; i++) {
            String s1 = titles.get(i - 1).getText();
            String s2 = titles.get(i).getText();
            if(s1.compareTo(s2) > 0) {
                throw new AssertionError("Items not sorted well");
            }
        }
    }

    /*
     * Testing high to low sort on frontpage
     */
    public void checkSortHighToLow() {
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.xpath("//option[@value='hilo']")).click();
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        int listSize = prices.size();
        for(int i = 1; i < listSize; i++) {
            double currentPrice = Double.parseDouble(prices.get(i - 1).getText().substring(1));
            double nextPrice = Double.parseDouble(prices.get(i).getText().substring(1));
            if(currentPrice < nextPrice) {
                throw new AssertionError("Items not sorted well");
            }
        }
    }

    /*
     * Testing low to high sort on frontpage
     */
    public void checkSortLowToHigh() {
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.xpath("//option[@value='lohi']")).click();
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        int listSize = prices.size();
        for(int i = 1; i < listSize; i++) {
            double currentPrice = Double.parseDouble(prices.get(i - 1).getText().substring(1));
            double nextPrice = Double.parseDouble(prices.get(i).getText().substring(1));
            if(currentPrice > nextPrice) {
                throw new AssertionError("Items not sorted well");
            }
        }
    }

    /*
     * Adding all items to cart from homepage
     */
    public void addAllItemsToCart() {
        List<WebElement> buttons = driver.findElements(By.xpath("//button[contains(@class, 'btn_inventory') and contains(text(), 'Add to cart')]"));
        for(WebElement button : buttons) {
            button.click();
        }
        assertEquals("6", driver.findElement(By.className("shopping_cart_badge")).getText());
    }

    /*
     * Removing all items from cart from frontpage
     */
    public void removeAllItemsFromCart() {
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[contains(@class, 'btn_inventory') and contains(text(), 'Remove')]"));
        for(WebElement button : removeButtons) {
            button.click();
        }
        assertEquals("", driver.findElement(By.className("shopping_cart_link")).getText());
    }
}
