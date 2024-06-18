package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Users;

public class CheckOut extends BasePage {
    private final By firstNameFld = By.cssSelector("#billing_first_name");
    private final By lastNameFld = By.cssSelector("#billing_last_name");
    private final By addressLineOneFld = By.cssSelector("#billing_address_1");
    private final By cityFld = By.cssSelector("#billing_city");
    private final By zipCodeFld = By.cssSelector("#billing_postcode");
    private final By emailAddressFld = By.cssSelector("#billing_email");
    private final By placeOrderBtn = By.cssSelector("#place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLink = By.cssSelector(".showlogin");

    private final By loginBtn = By.cssSelector(".woocommerce-form-login__submit");
    private final By usernameFld = By.cssSelector("#username");
    private final By passwordFld = By.cssSelector("#password");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDropDown = By.xpath("//select[@id='billing_country']");
    private final By stateFld = By.xpath("//input[@id='billing_state']");
    private final By directBankTransferRadioBtn = By.xpath("//label[normalize-space(text())='Direct bank transfer']/preceding-sibling::input");
    private final By countryLocator = By.cssSelector("#select2-billing_country-container");
    private final By productName = By.cssSelector("td[class='product-name']");

    private final By errorMsg = By.cssSelector(".woocommerce-error li");
    private final By cashOnDeliveryRadioBtn = By.cssSelector(".payment_method_cod input");

    public CheckOut(WebDriver driver) {
        super(driver);
    }

    public CheckOut enterFirstName(String firstName) {
        WebElement elementFirstName = waitUntilVisibilityOfElement(firstNameFld);
        elementFirstName.clear();
        elementFirstName.sendKeys(firstName);
       /* driver.findElement(firstNameFld).clear();
        driver.findElement(firstNameFld).sendKeys(firstName);*/
        return this;
    }

    public CheckOut enterLastName(String lastName) {
        WebElement elementLastName = waitUntilVisibilityOfElement(lastNameFld);
        elementLastName.clear();
        elementLastName.sendKeys(lastName);
       /* driver.findElement(lastNameFld).click();
        driver.findElement(lastNameFld).sendKeys(lastName);*/
        return this;
    }

    public CheckOut enterAddressLineOne(String addressLineOne) {
        WebElement elementAddressLineOne = waitUntilVisibilityOfElement(addressLineOneFld);
        elementAddressLineOne.clear();
        elementAddressLineOne.sendKeys(addressLineOne);
       /* driver.findElement(addressLineOneFld).clear();
        driver.findElement(addressLineOneFld).sendKeys(addressLine1);*/
        return this;
    }

    public CheckOut enterCity(String city) {
        WebElement elementCity = waitUntilVisibilityOfElement(cityFld);
        elementCity.clear();
        elementCity.sendKeys(city);
        /*driver.findElement(cityFld).clear();
        driver.findElement(cityFld).sendKeys(city);*/
        return this;
    }

    public CheckOut enterZipCode(String zipCode) {
        WebElement elementZipCode = waitUntilVisibilityOfElement(zipCodeFld);
        elementZipCode.clear();
        elementZipCode.sendKeys(zipCode);

       /* driver.findElement(zipCodeFld).clear();
        driver.findElement(zipCodeFld).sendKeys(zipCode);*/
        return this;
    }

    public CheckOut enterEmailAddress(String emailAddress) {
        WebElement elementEmailAddress = waitUntilVisibilityOfElement(emailAddressFld);
        elementEmailAddress.clear();
        elementEmailAddress.sendKeys(emailAddress);

       /* driver.findElement(emailAddressFld).clear();
        driver.findElement(emailAddressFld).sendKeys(emailAddress);*/
        return this;
    }

    public CheckOut clickOnPlaceOrderBtn() {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getSuccessMessage() {
        return waitUntilVisibilityOfElement(successNotice).getText();
        //return driver.findElement(successNotice).getText();
    }

    private CheckOut waitForLoginBtnToDisappear() {
        waitUntilInvisibilityOfElement(loginBtn);
        return this;
    }

    public CheckOut login(String username, String password) {
        return enterUsername(username).enterPassword(password).clickOnLoginBtn().waitForLoginBtnToDisappear();
    }

    public CheckOut login(Users user) {
        return enterUsername(user.getUsername()).enterPassword(user.getPassword()).clickOnLoginBtn().waitForLoginBtnToDisappear();
    }

    public CheckOut enterUsername(String username) {
        waitUntilVisibilityOfElement(usernameFld).sendKeys(username);
        //  driver.findElement(usernameFld).sendKeys(username);
        return this;
    }

    public CheckOut enterPassword(String password) {
        waitUntilVisibilityOfElement(passwordFld).sendKeys(password);
        // driver.findElement(passwordFld).sendKeys(password);
        return this;
    }

    public CheckOut clickHereToLogin() {
        waitUntilElementToBeClickable(clickHereToLoginLink).click();
        //driver.findElement(clickHereToLoginLink).click();
        return this;
    }

    public CheckOut clickOnLoginBtn() {
        waitUntilElementToBeClickable(loginBtn).click();
        //driver.findElement(loginBtn).click();
        return this;
    }

    public CheckOut setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName()).enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).enterState(billingAddress.getState()).
                enterZipCode(billingAddress.getZipCode())
                .enterEmailAddress(billingAddress.getEmailAddress());
    }

    public CheckOut selectCountry(String country) {
        WebElement country_ = waitUntilElementToBeClickable(countryLocator);
        country_.click();
        By myCountry = By.xpath("//li[text()='" + country + "']");
        WebElement myCountry_ = waitUntilElementToBeClickable(myCountry);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myCountry_);
        myCountry_.click();

       /* WebElement countryOne = driver.findElement(countryDropDown);
        Select selectCountry = new Select(countryOne);
        selectCountry.selectByVisibleText(country);*/
        return this;
    }

    public CheckOut enterState(String state) {
        WebElement stateField = waitUntilVisibilityOfElement(stateFld);
        stateField.clear();
        stateField.sendKeys(state);
        return this;
    }

    public CheckOut directBankTransfer() {
        waitUntilInvisibilityOfElement(loginBtn);
        WebElement radioBtn = waitUntilElementToBeClickable(directBankTransferRadioBtn);
        if (!radioBtn.isSelected())
            radioBtn.click();
        return this;
    }

    public CheckOut cashOnDelivery() {
        waitUntilInvisibilityOfElement(loginBtn);
        waitForOverlaysToDisappear(overlay);
        WebElement radioBtn = waitUntilElementToBeClickable(cashOnDeliveryRadioBtn);
        if (!radioBtn.isSelected())
            radioBtn.click();
        return this;
    }

    public CheckOut load() {
        load("/checkout");
        return this;
    }

    public String getProductName() throws InterruptedException {
        int retry = 5;
        while (retry > 0) {
            try {
                return waitUntilVisibilityOfElement(productName).getText();
            } catch (StaleElementReferenceException staleElementReferenceException) {
                System.out.println("Element Not found : TRYING AGAIN " + staleElementReferenceException);
            }
            Thread.sleep(5000);
            retry--;
        }
        throw new RuntimeException("Element Not found");
    }


    public String getErrorMsg() {
        return waitUntilVisibilityOfElement(errorMsg).getText();
    }
}
