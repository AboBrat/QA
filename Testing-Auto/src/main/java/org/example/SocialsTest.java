package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocialsTest {
    WebDriver driver;

    public SocialsTest(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getSocialsList() {
        return driver.findElement(By.className("social"));
    }

    public void isPresentTwitter() {
        WebElement twitterElement = getSocialsList().findElement(By.className("social_twitter"));
        assertEquals("Twitter", twitterElement.findElement(By.tagName("a")).getText());
    }

    public void isPresentFacebook() {
        WebElement facebookElement = getSocialsList().findElement(By.className("social_facebook"));
        assertEquals("Facebook", facebookElement.findElement(By.tagName("a")).getText());
    }

    public void isPresentLinkedin() {
        WebElement linkedinElement = getSocialsList().findElement(By.className("social_linkedin"));
        assertEquals("LinkedIn", linkedinElement.findElement(By.tagName("a")).getText());
    }

}
