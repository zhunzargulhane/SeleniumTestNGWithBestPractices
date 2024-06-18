package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class ProductDetailsPage extends BasePage {
    private final By productTitle = By.cssSelector(".product_title");
    private final By addToCartBtnInProductPage = By.cssSelector(".single_add_to_cart_button");
    private final By productAddedToCartConfirmationMsg = By.cssSelector("div[role='alert']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return waitUntilVisibilityOfElement(productTitle).getText();
    }

    public ProductDetailsPage loadProductPage(String productName) {
        load("/product/" + productName);
        return this;
    }

    public ProductDetailsPage clickOnAddToCartBtn(){
        waitUntilVisibilityOfElement(addToCartBtnInProductPage).click();
        return this;
    }

    public String productAddedToCartAlertMsg(){
        return waitUntilVisibilityOfElement(productAddedToCartConfirmationMsg).getText().trim();
    }

}
