package org.selenium.pom.utils;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {
    public List<org.openqa.selenium.Cookie> convertRestAssuredToSeleniumCookies(Cookies cookies) {
        List<Cookie> restAssuredCookies = cookies.asList();
        List<org.openqa.selenium.Cookie> seleniumCookies = new ArrayList<>();
        for (Cookie cookie : restAssuredCookies) {
            seleniumCookies.add(new
                    org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue(),
                    cookie.getDomain(), cookie.getPath(), cookie.getExpiryDate(),
                    cookie.isSecured(), cookie.isHttpOnly()));
        }
        return seleniumCookies;
    }

}
