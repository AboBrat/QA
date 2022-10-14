package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {
    WebDriver driver;

    public CartTest(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Open cart by clicking on link
     */
    public void openCart() {
        driver.findElement(By.xpath("//a[contains(@class, 'shopping_cart_link')]")).click();
    }

    /*
     * Checking if cart is empty
     */
    public void isCartEmpty() {
        if(driver.findElement(By.className("summary_total_label")).getText().equals("Total: $0.00")) {
            assertEquals("false", String.valueOf(driver.findElement(By.id("finish")).isEnabled()));
        }
    }

    /*
     * Check if shopping cart is present on page
     */
    public void isPresentCart() {
        WebElement cartContainer = driver.findElement(By.className("shopping_cart_container"));
        cartContainer.findElement(By.className("shopping_cart_link"));
    }
}
