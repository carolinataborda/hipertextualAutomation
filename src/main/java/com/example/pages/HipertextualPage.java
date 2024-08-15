package com.example.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HipertextualPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By searchButton = By.xpath("//button[@id='search-toggle']");

    private By searchBox = By.id("search-form-2");

    private By firstPostLink = By.xpath("(//*[@class='entry-title'])[1]");

    private By newsletterButton = By.xpath("//*[@id='menu-header-1']/li[5]");

    private By subscribeEmailField = By.xpath("//*[@type='email']");

    private By subscribeButton = By.xpath("//button[@class='button rightButton primary subscribe-btn _button_11q5m_76']");

    private By confirmSubscription = By.xpath("//*[contains(text(), 'By subscribing')]");



    public HipertextualPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void searchFor(String textToSearch) {
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='newspack_global_ad global_below_header fixed-height']")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(searchBox)));
        driver.findElement(searchBox).sendKeys(textToSearch);
        driver.findElement(searchBox).sendKeys(Keys.RETURN);
    }

    public void clickFirstPost() {
        WebElement postLink = driver.findElement(firstPostLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", postLink);
        postLink.click();
    }

    public boolean getUrl(String expectedURLPart) {
        return driver.getCurrentUrl().contains(expectedURLPart);
    }

    public boolean getTextOnPage(String expectedText) {
        return driver.getPageSource().contains(expectedText);
    }

    public void subscribeToNewsletter(String email) throws InterruptedException {
        driver.findElement(newsletterButton).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='post-1426049']/div/p[1]")));
        WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='https://hipertextual.substack.com/embed']")));
        driver.switchTo().frame(iframeElement);
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeEmailField));
        emailField.sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button rightButton primary subscribe-btn _button_11q5m_76']")));
        WebElement subscribeBtn = driver.findElement(subscribeButton);
        Thread.sleep(4000);
        subscribeBtn.click();
    }

    public void manageWindows(){
        String mainWindowHandle = driver.getWindowHandle();
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(mainWindowHandle);
                //driver.close();
            }
        }

    }

    public void manageAlert(){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='https://hipertextual.substack.com/embed']")));
        driver.switchTo().frame(iframeElement);
    }

    public String getSuccessfulSubscriptionMessage() {
        String textSuccessSubsciption = driver.findElement(confirmSubscription).getText();
        return textSuccessSubsciption;
    }
}
