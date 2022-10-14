package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavTest {
    WebDriver driver;
    CartTest cartTest;

    public NavTest(WebDriver driver) {
        this.driver = driver;
        cartTest = new CartTest(driver);
    }

    public WebElement getBurgerMenu() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement getLogout() { return driver.findElement(By.id("logout_sidebar_link"));}

    /*
     * Opening navbar
     */
    public void openNav() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    /*
     * Checking if about hyperlink is correct
     */
    public void testAboutButton() {
        assertEquals("https://saucelabs.com/", driver.findElement(By.xpath("//a[contains(@class, 'bm-item menu-item') and contains(text(), 'About')]")).getAttribute("href"));
    }

    /*
     * Checking if reset button resets cart state
     */
    public void testResetAppButton() {
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.findElement(By.className("btn_inventory")).click();
        assertEquals("1", driver.findElement(By.className("shopping_cart_badge")).getText());
        openNav();
        driver.findElement(By.xpath("//a[contains(@class, 'bm-item menu-item') and contains(text(), 'Reset App State')]")).click();
        assertEquals("", driver.findElement(By.className("shopping_cart_link")).getText());
    }

    /*
     * Checking if logout button does logout
     */
    public void logout() {
        getLogout().click();
    }

    /*
     * Checking if all items hyperlink is correct
     */
    public void testAllItemsButton() {
        cartTest.openCart();
        openNav();
        driver.findElement(By.xpath("//a[contains(@class, 'bm-item menu-item') and contains(text(), 'All Items')]")).click();
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    public void isPresentBurgerMenu() { getBurgerMenu(); }

    public void isPresentLogout() {
        getBurgerMenu().click();
        getLogout();
    }
}
