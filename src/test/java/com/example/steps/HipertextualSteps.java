package com.example.steps;

import com.example.pages.HipertextualPage;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class HipertextualSteps {

    private WebDriver driver;
    private HipertextualPage hipertextualPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        hipertextualPage = new HipertextualPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user wants to navigate to {string}")
    public void NavigateTo(String url) {
        driver.get(url);
    }

    @When("he search for {string}")
    public void iSearchFor(String query) {
        hipertextualPage.searchFor(query);
    }

    @Then("he validate the URL contains {string}")
    public void iValidateTheUrlContains(String urlPart) {
        Assert.assertTrue(driver.getCurrentUrl().contains(urlPart));
    }

    @Then("he validate the page contains the text {string}")
    public void iValidateThePageContainsTheText(String text) {
        Assert.assertTrue(hipertextualPage.getTextOnPage(text));
    }

    @Given("he wants to subscribe to the newsletter")
    public void heWantsToSubscribeToTheNewsletter() {
    }

    @When("he clicks and enter the email {string}")
    public void heClicksAndEnterTheEmail(String email) {
        hipertextualPage.subscribeToNewsletter(email);
    }

}

