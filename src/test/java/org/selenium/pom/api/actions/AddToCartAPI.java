package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.api.actions.apiUtils.APIRequest;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class AddToCartAPI {
    //If the customer is logged in
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    public AddToCartAPI() {
    }

    public AddToCartAPI(Cookies cookies) {
        this.cookies = cookies;
    }

    public Response addProductToCart(int productId, int quantity) {
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> productInfo = new HashMap<>();
        productInfo.put("product_id", productId);
        productInfo.put("quantity", quantity);
        if (cookies == null)
            cookies = new Cookies();
        Response response = APIRequest.post(cookies, headers, productInfo, Endpoint.ADD_TO_CART.url);
        if (response.statusCode() != 200)
            throw new RuntimeException("Unable to add product to the cart " + productId + " : " + response.statusCode());
        this.cookies = response.getDetailedCookies();
        System.out.println("cokkies are " + cookies);
        return response;
    }
}
