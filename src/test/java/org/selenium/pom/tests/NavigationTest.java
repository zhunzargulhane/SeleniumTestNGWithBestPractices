package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Products;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductDetailsPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateFromHomePageToStorePageUsingMainMenu() {
        StorePage storePage = new HomePage(getDriver()).load().getMyHeader().
                navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(), "Store");
    }

    @Test
    public void navigateFromStorePageToProductPage() throws IOException {
        Products products = new Products(1215);
        ProductDetailsPage productDetailsPage = new StorePage(getDriver()).load().viewProductDetails(products.getName());
        Assert.assertEquals(productDetailsPage.getProductTitle(),products.getName());
    }

    @Test
    public void navigateFromHomePageToFeaturedProductPage() throws IOException {
        Products products = new Products(1215);
        ProductDetailsPage productDetailsPage = new HomePage(getDriver()).load().viewFeaturedProductDetails(products.getName());
        Assert.assertEquals(productDetailsPage.getProductTitle(),products.getName());
    }
}
