package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormVerificationTest {

    WebDriver driver;
    CartTest cartTest;

    public FormVerificationTest(WebDriver driver) {
        this.driver = driver;
        cartTest = new CartTest(driver);
    }

    /*
     * Navigation to check out form
     */
    public void openForm() {
        cartTest.openCart();
        driver.findElement(By.id("checkout")).click();
    }

    /*
     * Entering firstname into form
     */
    public void enterName() {
        driver.findElement(By.id("first-name")).sendKeys("Jovan");
    }

    /*
     * Entering lastname into form
     */
    public void enterLast() {
        driver.findElement(By.id("last-name")).sendKeys("Bojovic");
    }

    /*
     * Entering postal into form
     */
    public void enterPostal() {
        driver.findElement(By.id("postal-code")).sendKeys("34000");
    }

    /*
     * Testing submit form when just firstname is entered
     */
    public void justNameForm() {
        openForm();
        enterName();
        submitWrongForm();
    }

    /*
     * Testing submit form when just lastname is entered
     */
    public void justLastnameForm() {
        openForm();
        enterLast();
        submitWrongForm();
    }

    /*
     * Testing submit form when just postal is entered
     */
    public void justPostalForm() {
        openForm();
        enterPostal();
        submitWrongForm();
    }

    /*
     * Testing submit form when firstname and lastname are entered
     */
    public void nameAndLastForm() {
        openForm();
        enterName();
        enterLast();
        submitWrongForm();
    }

    /*
     * Testing submit form when firstname and postal are entered
     */
    public void nameAndPostalForm() {
        openForm();
        enterName();
        enterPostal();
        submitWrongForm();
    }

    /*
     * Testing submit form when lastname and postal are entered
     */
    public void lastAndPostalForm() {
        openForm();
        enterLast();
        enterPostal();
        submitWrongForm();
    }

    /*
     * Submiting empty form
     */
    public void emptyFormTest() {
        openForm();
        submitWrongForm();
    }

    /*
     * Enter all data into form
     */
    public void enterAllData() {
        enterName();
        enterLast();
        enterPostal();
    }

    /*
     * Checking if error is shown when data is missing from form
     */
    public void submitWrongForm() {
        driver.findElement(By.id("continue")).click();
        assertTrue(true, String.valueOf(driver.findElement(By.xpath("//h3[contains(text(), 'Error')]")).getText().contains("Error")));
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    /*
     * Checking if inputs in form has right data
     */
    public void checkEnteredValues() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        assertEquals("Jovan", driver.findElement(By.id("first-name")).getAttribute("value"));
        assertEquals("Bojovic", driver.findElement(By.id("last-name")).getAttribute("value"));
        assertEquals("34000", driver.findElement(By.id("postal-code")).getAttribute("value"));
        driver.findElement(By.id("continue")).click();
    }

}
