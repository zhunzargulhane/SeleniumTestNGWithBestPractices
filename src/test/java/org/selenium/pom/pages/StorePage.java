package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {
    private ProductThumbnail productThumbnail;

    private final By searchFld = By.cssSelector("#woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    //  private final By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");

    // private final By viewCartLink = By.cssSelector("a[title='View cart']");
    private final By displayMsgNonExistingProduct = By.cssSelector(".woocommerce-no-products-found");


    public StorePage(WebDriver driver) {

        super(driver);
        this.productThumbnail = new ProductThumbnail(driver);
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public String getTitle() {
        return waitUntilVisibilityOfElement(title).getText();
        //return driver.findElement(title).getText();
    }

    public StorePage sendInputInSearchField(String txt) {
        waitUntilVisibilityOfElement(searchFld).sendKeys(txt);
        //driver.findElement(searchFld).sendKeys(txt);
        return this;
    }

    public StorePage search(String txt) {
        sendInputInSearchField(txt).clickOnSearchBtn();
        return this;
    }

    public StorePage clickOnSearchBtn() {
        waitUntilVisibilityOfElement(searchBtn).click();
        //driver.findElement(searchBtn).click();
        return this;
    }

   /* private By getAddToCarBtnElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }*/

    private By productDetails(String productName) {
        return By.xpath("//h2[normalize-space()=" + "\'" + productName + "\']");
    }

    /*public StorePage clickOnAddToCartBtn(String productName) {
        By addToCartBtn = getAddToCarBtnElement(productName);
        waitUntilElementToBeClickable(addToCartBtn).click();
        //driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickOnViewCartLink() {
        waitUntilElementToBeClickable(viewCartLink).click();
        //driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }
*/
    public boolean isLoaded() {
        return waitUntilURLMatches("/store");
    }

    public boolean isLoadedWithText(String text) {
        return waitUntilURLMatches(text);
    }


    public StorePage load() {
        load(Endpoint.STORE.url);
        return this;
    }

    public ProductDetailsPage viewProductDetails(String productName) {
        By productDetails = productDetails(productName);
        waitUntilElementToBeClickable(productDetails).click();
        return new ProductDetailsPage(driver);
    }

    @Step
    public ProductDetailsPage searchWithExactTxt(String txt) {
        sendInputInSearchField(txt).clickOnSearchBtn();
        return new ProductDetailsPage(driver);
    }

    public String displayMsgNonExistingProduct() {
        return waitUntilVisibilityOfElement(displayMsgNonExistingProduct).getText();
    }

}
