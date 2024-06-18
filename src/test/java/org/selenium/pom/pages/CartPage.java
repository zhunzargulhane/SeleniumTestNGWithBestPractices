package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    //private final By productAddedCheckInCartPage = By.cssSelector("td[class='product-name'] a");
    // private final By checkoutBtn = By.cssSelector(".checkout-button");
    //private final By cartText = By.cssSelector(".has-text-align-center");

    @FindBy(how = How.CSS, using = "td[class='product-name'] a")
    @CacheLookup private WebElement productAddedCheckInCartPage;

    @FindBy(how = How.CSS, using = ".checkout-button")
    private WebElement checkoutBtn;

    @FindBy(how = How.CSS, using = ".has-text-align-center")
    private WebElement cartText;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTextOfProductDetailsAddedToCartPage() {
        return waitUntilVisibilityOfElement(productAddedCheckInCartPage).getText();
        //return driver.findElement(productAddedCheckInCartPage).getText();
    }

    public CheckOut checkout() {
        waitUntilElementToBeClickable(checkoutBtn).click();
//        driver.findElement(checkoutBtn).click();
        return new CheckOut(driver);
    }

    public boolean isLoaded() {
        return waitUntilTextToBe(cartText, "Cart");
    }

}
