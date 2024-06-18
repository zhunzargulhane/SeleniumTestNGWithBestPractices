package org.selenium.pom.tests;

import org.selenium.pom.api.actions.AddToCartAPI;
import org.selenium.pom.api.actions.SignUpAPI;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.Users;
import org.selenium.pom.pages.CheckOut;
import org.selenium.pom.utils.JavaFakerUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestDuringCheckout extends BaseTest {
    SignUpAPI signUpAPI;
    AddToCartAPI addToCartAPI;
    Users user;
    Products products;

    @BeforeMethod
    public void registerUserAndAddProductToCart() throws IOException {
        signUpAPI = new SignUpAPI();
        user = new Users().setUsername(JavaFakerUtils.getUserName()).
                setPassword(JavaFakerUtils.getPassword()).
                setEmailAddress(JavaFakerUtils.getEmailAddress());
        signUpAPI.register(user);
        System.out.println("SIGNUP API COOKIES: " + signUpAPI.getCookies());
        addToCartAPI = new AddToCartAPI();
        products = new Products(1215);
        addToCartAPI.addProductToCart(products.getId(), 1);
    }

    @Test
    public void loginDuringCheckout() throws InterruptedException {
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(addToCartAPI.getCookies());
        checkOut.load();
        checkOut.clickHereToLogin().
                login(user);
        Assert.assertTrue(checkOut.getProductName().contains(products.getName()));
    }

    @Test
    public void loginFailsDuringCheckout() {
        CheckOut checkOut = new CheckOut(getDriver()).load();
        injectCookiesToBrowser(addToCartAPI.getCookies());
        checkOut.load();
        String username = JavaFakerUtils.getUserName();
        String password = JavaFakerUtils.getPassword();
        checkOut = checkOut.clickHereToLogin().
                login(username, password);
        Assert.assertEquals(checkOut.getErrorMsg(), "Error: The username " + username + " is not registered on this site. If you are unsure of your username, try your email address instead.");
    }
}
