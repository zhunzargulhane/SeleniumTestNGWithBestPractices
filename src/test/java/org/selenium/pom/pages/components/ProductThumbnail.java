package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;

public class ProductThumbnail extends BasePage {
    private final By viewCartLink = By.cssSelector("a[title='View cart']");
    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By addToCart(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }
    public ProductThumbnail clickOnAddToCartBtn(String productName) {
        By addToCartBtn = addToCart(productName);
        waitUntilElementToBeClickable(addToCartBtn).click();
        //driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickOnViewCartLink() {
        waitUntilElementToBeClickable(viewCartLink).click();
        //driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }
}
