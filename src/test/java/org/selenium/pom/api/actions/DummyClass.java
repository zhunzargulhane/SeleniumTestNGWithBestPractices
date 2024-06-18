package org.selenium.pom.api.actions;

import io.restassured.response.Response;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.Users;
import org.selenium.pom.utils.JavaFakerUtils;

import java.io.IOException;

public class DummyClass {
    public static void main(String[] args) throws IOException {
        SignUpAPI signUpAPI = new SignUpAPI();
        // System.out.println(signUpAPI.getRegisterNonceValueUsingJsoup());
        //System.out.println(signUpAPI.getRegisterNonceValueUsingGroovy());
        Users user = new Users().setUsername(JavaFakerUtils.getUserName()).
                setPassword(JavaFakerUtils.getPassword()).
                setEmailAddress(JavaFakerUtils.getEmailAddress());
        signUpAPI.register(user);
        System.out.println("SIGNUP API COOKIES: "+signUpAPI.getCookies());
        AddToCartAPI addToCartAPI = new AddToCartAPI(signUpAPI.getCookies());
        Products products = new Products(1215);
        addToCartAPI.addProductToCart(products.getId(),1);
        System.out.println("CART API COOKIES: "+addToCartAPI.getCookies());
    }
}
