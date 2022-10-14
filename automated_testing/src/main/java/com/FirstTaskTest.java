package com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTaskTest {

    public WebDriver driver;

    private LoginTest loginTest;

    private SocialsTest socialsTest;

    private CartTest cartTest;

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
        socialsTest = new SocialsTest(driver);
        cartTest = new CartTest(driver);
        navTest = new NavTest(driver);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void isPresentProductsHeader() {
        loginTest.loginStandardUser();
        WebElement header = driver.findElement(By.className("header_secondary_container"));
        WebElement productsText = header.findElement(By.className("title"));
        assertEquals("PRODUCTS", productsText.getText());
    }

    @Test
    public void isPresentShoppingCart() {
        loginTest.loginStandardUser();
        cartTest.isPresentCart();
    }

    @Test
    public void isPresentBurgerMenu() {
        loginTest.loginStandardUser();
        navTest.isPresentBurgerMenu();
    }


    @Test
    public void isPresentSocials() {
        loginTest.loginStandardUser();
        socialsTest.isPresentTwitter();
        socialsTest.isPresentFacebook();
        socialsTest.isPresentLinkedin();
    }

    @Test
    public void isPresentLogout() {
        loginTest.loginStandardUser();
        navTest.isPresentLogout();
    }

}
