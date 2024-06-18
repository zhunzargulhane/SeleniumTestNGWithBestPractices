package org.selenium.pom.tests;

import org.selenium.pom.api.actions.AddToCartAPI;
import org.selenium.pom.api.actions.BillingAddressAPI;
import org.selenium.pom.api.actions.SignUpAPI;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.Users;
import org.selenium.pom.pages.CheckOut;
import org.selenium.pom.utils.JacksonUtils;
import org.selenium.pom.utils.JavaFakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("mybillingAddress.json", BillingAddress.class);
        AddToCartAPI addToCartAPI = new AddToCartAPI();
        Products products = new Products(1215);
        addToCartAPI.addProductToCart(products.getId(), 1);
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(addToCartAPI.getCookies());
        checkOut.load().setBillingAddress(billingAddress).clickOnPlaceOrderBtn();
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");
    }


    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("mybillingAddress.json", BillingAddress.class);
        Products products = new Products(1215);
        Users user = new Users().setUsername(JavaFakerUtils.getUserName()).
                setPassword(JavaFakerUtils.getPassword()).
                setEmailAddress(JavaFakerUtils.getEmailAddress());
        SignUpAPI signUpAPI = new SignUpAPI();
        signUpAPI.register(user);
        AddToCartAPI addToCartAPI = new AddToCartAPI(signUpAPI.getCookies());
        addToCartAPI.addProductToCart(products.getId(), 1);
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(signUpAPI.getCookies());
        checkOut.load().setBillingAddress(billingAddress).clickOnPlaceOrderBtn();
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");
    }

    @Test
    public void guestCheckoutUsingCashOnDelivery() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        AddToCartAPI addToCartAPI = new AddToCartAPI();
        addToCartAPI.addProductToCart(1215, 1);
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(addToCartAPI.getCookies());
        checkOut.load().setBillingAddress(billingAddress).cashOnDelivery().clickOnPlaceOrderBtn();
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingCashOnDelivery() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Users user = new Users().setUsername(JavaFakerUtils.getUserName()).
                setPassword(JavaFakerUtils.getPassword()).
                setEmailAddress(JavaFakerUtils.getEmailAddress());
        SignUpAPI signUpAPI = new SignUpAPI();
        signUpAPI.register(user);
        AddToCartAPI addToCartAPI = new AddToCartAPI(signUpAPI.getCookies());
        addToCartAPI.addProductToCart(1215, 1);
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(signUpAPI.getCookies());
        checkOut.load().setBillingAddress(billingAddress).cashOnDelivery().clickOnPlaceOrderBtn();
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransferWithBillingAddress() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Users user = new Users().setUsername(JavaFakerUtils.getUserName()).
                setPassword(JavaFakerUtils.getPassword()).
                setEmailAddress(JavaFakerUtils.getEmailAddress());
        SignUpAPI signUpAPI = new SignUpAPI();
        signUpAPI.register(user);
        AddToCartAPI addToCartAPI = new AddToCartAPI(signUpAPI.getCookies());
        addToCartAPI.addProductToCart(1215, 1);
        Thread.sleep(5000);
        BillingAddressAPI billingAddressAPI = new BillingAddressAPI(signUpAPI.getCookies());
        billingAddressAPI.setBillingAddress(billingAddress);
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(signUpAPI.getCookies());
        checkOut.load().directBankTransfer().clickOnPlaceOrderBtn();
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");
    }

    @Test(dataProvider = "billingAddress", dataProviderClass = MyDataProvider.class)
    public void guestCheckoutUsingDirectBankTransferWithMultipleBillingAddress(BillingAddress billingAddress) {
        Users user = new Users().setUsername(JavaFakerUtils.getUserName()).
                setPassword(JavaFakerUtils.getPassword()).
                setEmailAddress(JavaFakerUtils.getEmailAddress());
        SignUpAPI signUpAPI = new SignUpAPI();
        signUpAPI.register(user);
        AddToCartAPI addToCartAPI = new AddToCartAPI(signUpAPI.getCookies());
        addToCartAPI.addProductToCart(1215, 1);
        BillingAddressAPI billingAddressAPI = new BillingAddressAPI(signUpAPI.getCookies());
        billingAddressAPI.setBillingAddress(billingAddress);
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(signUpAPI.getCookies());
        checkOut.load().directBankTransfer().clickOnPlaceOrderBtn();
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");
    }
}
