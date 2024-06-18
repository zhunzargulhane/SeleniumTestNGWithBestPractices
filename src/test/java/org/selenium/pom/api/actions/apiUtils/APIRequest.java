package org.selenium.pom.api.actions.apiUtils;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class APIRequest extends SpecBuilder {
    public static Response post(Cookies cookies, Headers headers, HashMap<String, Object> registerData, String endpoint) {
        return given(getRequestSpec()).
                cookies(cookies).headers(headers).formParams(registerData).
                when().
                post(endpoint).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response get(Cookies cookies, String endpoint) {
        return given(getRequestSpec()).cookies(cookies).
                when().
                get(endpoint).
                then().spec(getResponseSpec()).
                extract().response();
    }
}
