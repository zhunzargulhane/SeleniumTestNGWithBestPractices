package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.Users;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckOut;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestMyFirstCase extends BaseTest {
    //@Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserializeJson("mybillingAddress.json", BillingAddress.class);
        Products products = new Products(1215);
        StorePage storePage = new HomePage(getDriver()).load().getMyHeader().
                navigateToStoreUsingMenu().search("blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “blue”");
        storePage.getProductThumbnail().clickOnAddToCartBtn(products.getName());
        //Thread.sleep(5000);
        CartPage cartPage = storePage.getProductThumbnail().clickOnViewCartLink();
        Assert.assertEquals(cartPage.getTextOfProductDetailsAddedToCartPage(), products.getName());
        CheckOut checkOut = cartPage.checkout();
        checkOut.setBillingAddress(billingAddress).clickOnPlaceOrderBtn();
        // Thread.sleep(5000);
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");

    }

    //@Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Products products = new Products(1215);
        //Users users = JacksonUtils.deserializeJson("users.json", Users.class);
        Users users = new Users(ConfigLoader.getInstance().getUserName(),ConfigLoader.getInstance().getUserName());
        StorePage storePage = new HomePage(getDriver()).load().getMyHeader()
                .navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.search("blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        storePage.getProductThumbnail().clickOnAddToCartBtn(products.getName());
        CartPage cartPage = storePage.getProductThumbnail().clickOnViewCartLink();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getTextOfProductDetailsAddedToCartPage(), products.getName());
        CheckOut checkOut = cartPage.checkout().clickHereToLogin();
        checkOut = checkOut.login(users.getUsername(), users.getPassword()).
                setBillingAddress(billingAddress);
        checkOut.directBankTransfer().clickOnPlaceOrderBtn();
        Assert.assertEquals(checkOut.getSuccessMessage(), "Thank you. Your order has been received.");
    }


}
