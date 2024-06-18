package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {
    private MyHeader myHeader;


    private ProductThumbnail productThumbnail;
//    By storeMenuLink = By.cssSelector("#menu-item-1227 a");
    // private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public HomePage(WebDriver driver) {
        super(driver);
        this.myHeader = new MyHeader(driver);
        this.productThumbnail = new ProductThumbnail(driver);
    }

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

   /* public StorePage navigateToStoreUsingMenu() {
        waitUntilVisibilityOfElement(storeMenuLink).click();
        //driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }*/

    public HomePage load() {
        load("/");
        waitUntilTitleMatches("AskOmDch");
        return this;
    }

    private By productDetails(String productName) {
        return By.xpath("//h2[normalize-space()=" + "\'" + productName + "\']");
    }

    public ProductDetailsPage viewFeaturedProductDetails(String productName) {
        By productDetails = productDetails(productName);
        waitUntilElementToBeClickable(productDetails).click();
        return new ProductDetailsPage(driver);
    }

   /* private By addToCart(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }
    public HomePage clickOnAddToCartBtn(String productName) {
        By addToCartBtn = addToCart(productName);
        waitUntilElementToBeClickable(addToCartBtn).click();
        //driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickOnViewCartLink() {
        waitUntilElementToBeClickable(viewCartLink).click();
        //driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }*/
}
