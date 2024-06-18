package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void load(String endpoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endpoint);
    }

    public void waitForOverlaysToDisappear(By webElement) {
        List<WebElement> overlays = driver.findElements(webElement);
        System.out.println("OVERLAY SIZE: " + overlays.size());
        if (overlays.size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAY SIZE: After Invisible " + overlays.size());
        } else System.out.println("OVERLAY NOT FOUND");
    }

    public WebElement waitUntilVisibilityOfElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitUntilTitleMatches(String title) {

        return wait.until(ExpectedConditions.titleContains(title));
    }

    public boolean waitUntilURLMatches(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }

    public boolean waitUntilTextToBe(By locator, String txt) {
        return wait.until(ExpectedConditions.textToBe(locator, txt));
    }

    public void waitUntilPollingForSeconds() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).
                pollingEvery(Duration.ofSeconds(4)).ignoring(NoSuchElementException.class);
        wait.until(new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        });
    }

    public WebElement waitUntilVisibilityOfElement(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitUntilElementToBeClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public Boolean waitUntilTextToBe(WebElement locator, String txt) {
        return wait.until(ExpectedConditions.textToBePresentInElement(locator, txt));
    }

    public void waitUntilInvisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
