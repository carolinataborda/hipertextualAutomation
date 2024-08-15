package com.example.steps;

import com.example.pages.HipertextualPage;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;


public class HipertextualSteps {

    private WebDriver driver;
    private HipertextualPage hipertextualPage;

    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
    public void heClicksAndEnterTheEmail(String email) throws InterruptedException {
        hipertextualPage.subscribeToNewsletter(email);
    }

    @Then("he should see a confirmation message")
    public void heShouldSeeAConfirmationMessage() {
        hipertextualPage.manageWindows();
        hipertextualPage.manageAlert();
        hipertextualPage.getSuccessfulSubscriptionMessage();
        Assert.assertTrue(hipertextualPage.getSuccessfulSubscriptionMessage().contains("By subscribing you agree to Substack's " +
                "Terms of Use, our Privacy Policy and our Information collection notice"));
    }
}

