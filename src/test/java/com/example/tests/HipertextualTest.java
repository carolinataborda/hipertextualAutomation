package com.example.tests;

import com.example.pages.HipertextualPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HipertextualTest {

    private WebDriver driver;
    private HipertextualPage hipertextualPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://hipertextual.com");
        hipertextualPage = new HipertextualPage(driver);
    }

    @Test
    public void testSearchAndVerifyPage() {
        hipertextualPage.searchFor("Steve Jobs");
        hipertextualPage.clickFirstPost();
        Assert.assertTrue(hipertextualPage.getUrl("https://hipertextual.com"));
        Assert.assertTrue(hipertextualPage.getTextOnPage("hipertextual"));
    }

    @Test(dependsOnMethods = {"testSearchAndVerifyPage"})
    public void testSubscribeToNewsletter() {
        hipertextualPage.subscribeToNewsletter("testExample@mail.com");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
