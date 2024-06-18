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
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.utils.JavaFakerUtils;

import java.util.HashMap;

public class BillingAddressAPI {
    private Cookies cookies;
    //woocommerce-edit-address-nonce   //85c0f33bd4

    public Cookies getCookies() {
        return cookies;
    }

    public BillingAddressAPI() {
        this.cookies = new Cookies();
    }

    public BillingAddressAPI(Cookies cookies) {
        this.cookies = cookies;
    }

    private Response getAccountEditAddressBilling(Cookies cookies) {
        return APIRequest.get(cookies, Endpoint.ACCOUNT_EDIT_BILLING_ADDRESS.url);
    }

    private String getNonceUniqueId(Cookies cookies) {
        Response response = getAccountEditAddressBilling(cookies);
        Document document = Jsoup.parse(response.getBody().prettyPrint());
        Element element = document.selectFirst("#woocommerce-edit-address-nonce");
        return element.attr("value");
    }

    public Response setBillingAddress(BillingAddress billingAddress) {
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("billing_first_name", billingAddress.getFirstName());
        formParams.put("billing_last_name", billingAddress.getLastName());
        formParams.put("billing_country", billingAddress.getCountry());
        formParams.put("billing_address_1", billingAddress.getAddressLineOne());
        formParams.put("billing_city", billingAddress.getCity());
        formParams.put("billing_state", billingAddress.getState());
        formParams.put("billing_postcode", billingAddress.getZipCode());
        formParams.put("_wp_http_referer", "/account/edit-address/billing/");
        formParams.put("billing_email", billingAddress.getEmailAddress());
        formParams.put("save_address", "Save address");
        formParams.put("action", "edit_address");
        formParams.put("woocommerce-edit-address-nonce", getNonceUniqueId(getCookies()));
        Response response = APIRequest.post(cookies, headers, formParams, Endpoint.ACCOUNT_EDIT_BILLING_ADDRESS.url);
        if (response.getStatusCode() != 302)
            throw new RuntimeException("Unable to set the billing address :" + response.getStatusCode());
        this.cookies = response.getDetailedCookies();
        return response;
    }

}


