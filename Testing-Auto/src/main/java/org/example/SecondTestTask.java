package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondTestTask{

        public WebDriver driver;

        private LoginTest loginTest;

        private FormTestVerification formTest;

        private NavTest navTest;

        @BeforeAll
        public static void setDriver() {
            System.setProperty("webdriver.chrome.driver", Utils.DRIVER_PATH);
        }

        @BeforeEach
        public void setup() {
            driver = new ChromeDriver();
            driver.get("https://www.saucedemo.com");
            loginTest = new LoginTest(driver);
            formTest = new FormTestVerification(driver);
            navTest = new NavTest(driver);
        }

        @AfterEach
        public void quit() {
            driver.quit();
        }


        private void checkTitleAndOpenPage() {
            driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Backpack')]")).click();
        }

        private void addJacketToCart() {
            driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Fleece Jacket')]"));
            driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        }

        private void verifyItem() {
            WebElement title = driver.findElement(By.className("inventory_details_name"));
            assertEquals(title.getText(), "Sauce Labs Backpack");
            WebElement description = driver.findElement(By.className("inventory_details_desc"));
            assertEquals(description.getText(), "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
            WebElement price = driver.findElement(By.className("inventory_details_price"));
            assertEquals(price.getText(), "$29.99");
        }

        private void addBackpackToCart() {
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        }

        private void backToProducts() {
            driver.findElement(By.id("back-to-products")).click();
        }

        private void openCart() {
            driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        }

        private void checkout() {
            driver.findElement(By.id("checkout")).click();
        }

        private void doCheckout() {
            formTest.enterAllData();
            formTest.checkEnteredValues();
            driver.findElement(By.id("finish")).click();
        }

        private void verifyCheckout() {
            driver.findElement(By.xpath("//h2[contains(text(), 'THANK YOU FOR YOUR ORDER')]"));
        }


        @Test
        public void addToCart() {
            loginTest.loginStandardUser();
            checkTitleAndOpenPage();
            verifyItem();
            addBackpackToCart();
            backToProducts();
            addJacketToCart();
            openCart();
            checkout();
            doCheckout();
            verifyCheckout();
            navTest.logout();
        }
}
