package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.api.actions.apiUtils.APIRequest;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.objects.Users;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JavaFakerUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpAPI {
    private Cookies cookies;
    //we will set the cookies using registration API

    public Cookies getCookies() {
        return cookies;
    }

    private String getRegisterNonceValueUsingGroovy() {
        Response response = getAccountAPI();
        return response.htmlPath().getString("**. findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }

    private String getRegisterNonceValueUsingJsoup() {
        Response response = getAccountAPI();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }

    private Response getAccountAPI() {
        Cookies cookies = new Cookies();
        Response response =
                APIRequest.get(cookies, Endpoint.ACCOUNT.url);
        if (response.getStatusCode() != 200)
            throw new RuntimeException("Failed to fetch the account, the HTTP status code: " + response.getStatusCode());
        return response;
    }

    public Response register(Users user) {
        Cookies cookies = new Cookies();
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> registerData = new HashMap();
        registerData.put("username", user.getUsername());
        registerData.put("password", user.getPassword());
        registerData.put("email", user.getEmailAddress());
        registerData.put("woocommerce-register-nonce", getRegisterNonceValueUsingJsoup());
        registerData.put("register", "Register");
        Response response = APIRequest.post(cookies, headers, registerData, Endpoint.ACCOUNT.url);
        if (response.getStatusCode() != 302)
            throw new RuntimeException("Failed to register the account, the HTTP status code: " + response.getStatusCode());
        this.cookies = response.getDetailedCookies();
        System.out.println("The cookies are  " + getCookies());
        return response;
    }
}
