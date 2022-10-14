package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SoloTest {

    private WebDriver driver;
    private LoginTest loginTest;

    private FrontpageTest frontpageTest;

    private FormTestVerification formTest;

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
        frontpageTest = new FrontpageTest(driver);
        formTest = new FormTestVerification(driver);
        navTest = new NavTest(driver);
        cartTest = new CartTest(driver);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    private void formTest() {
        formTest.emptyFormTest();
        formTest.justNameForm();
        formTest.justLastnameForm();
        formTest.justPostalForm();
        formTest.nameAndLastForm();
        formTest.nameAndPostalForm();
        formTest.lastAndPostalForm();
    }

    private void testSorting() {
        frontpageTest.checkSortZtoA();
        frontpageTest.checkSortAtoZ();
        frontpageTest.checkSortHighToLow();
        frontpageTest.checkSortLowToHigh();
    }

    private void verifyFrontPage() {
        frontpageTest.verifyTitles();
        frontpageTest.verifyPrices();
        frontpageTest.verifyImages();
    }

    private void frontpageAddItemsToCartTest() {
        frontpageTest.addAllItemsToCart();
        frontpageTest.removeAllItemsFromCart();
    }

    private void testNavItems() {
        navTest.testAboutButton();
        navTest.testAllItemsButton();
        navTest.testResetAppButton();
        navTest.logout();
    }

    private void testEmptyCart() {
        formTest.openForm();
        formTest.enterName();
        formTest.enterLast();
        formTest.enterPostal();
        formTest.checkEnteredValues();
        cartTest.isCartEmpty();
    }


    @Test
    public void standardUserFrontpageTest() {
        loginTest.loginStandardUser();
        verifyFrontPage();
    }

    @Test
    public void standardUserCartTest() {
        loginTest.loginStandardUser();
        frontpageAddItemsToCartTest();
    }

    @Test
    public void standardUserSortTest() {
        loginTest.loginStandardUser();
        testSorting();
    }

    @Test
    public void standardUserNavTest() {
        loginTest.loginStandardUser();
        testNavItems();
    }

    @Test
    public void standardUserFormTest() {
        loginTest.loginStandardUser();
        formTest();
    }

    @Test
    public void standardUserEmptyCartTest() {
        loginTest.loginStandardUser();
        testEmptyCart();
    }

    /*
     * Problem user tests
     */

    @Test
    public void problemUserSortTest() {
        loginTest.loginProblemUser();
        testSorting();
    }

    @Test
    public void problemUserCartTest() {
        loginTest.loginProblemUser();
        frontpageAddItemsToCartTest();
    }

    @Test
    public void problemUserFrontpageTest() {
        loginTest.loginProblemUser();
        verifyFrontPage();
    }

    @Test
    public void problemUserNavTest() {
        loginTest.loginProblemUser();
        testNavItems();
    }

    @Test
    public void problemUserFormTest() {
        loginTest.loginProblemUser();
        formTest();
    }

    @Test
    public void problemUserEmptyCartTest() {
        loginTest.loginProblemUser();
        testEmptyCart();
    }

}
