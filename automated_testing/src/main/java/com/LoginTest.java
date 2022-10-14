package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest {

    WebDriver driver;

    public LoginTest(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Problem user login
     */
    public void loginProblemUser() {
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    /*
     * Standard user login
     */
    public void loginStandardUser() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

}
