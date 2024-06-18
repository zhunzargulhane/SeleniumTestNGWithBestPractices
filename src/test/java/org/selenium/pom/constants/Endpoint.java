package org.selenium.pom.constants;

public enum Endpoint {
    STORE("/store"),
    ACCOUNT("/account"),
    CHECKOUT("/checkout"),
    ADD_TO_CART("/?wc-ajax=add_to_cart"),
    ACCOUNT_EDIT_BILLING_ADDRESS("/account/edit-address/billing/");
    public String url;

    Endpoint(String s) {
        url = s;
    }
}
