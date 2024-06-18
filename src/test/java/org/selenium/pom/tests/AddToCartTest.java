package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.Products;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductDetailsPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddToCartTest extends BaseTest {
    @Test
    public void addToCartFromStorePage() throws IOException {
        Products products = new Products(1215);
        CartPage cartPage = new StorePage(getDriver()).load().getProductThumbnail().
                clickOnAddToCartBtn(products.getName())
                .clickOnViewCartLink();
        Assert.assertEquals(cartPage.getTextOfProductDetailsAddedToCartPage(), products.getName());
    }

    @Test
    public void addFeaturedProductToCart() throws IOException {
        Products products = new Products(1215);
        CartPage cartPage = new HomePage(getDriver()).load().getProductThumbnail().
                clickOnAddToCartBtn(products.getName()).
                clickOnViewCartLink();
        Assert.assertEquals(cartPage.getTextOfProductDetailsAddedToCartPage(), products.getName());
    }

    @Test
    public void addToCartFromProductsPage() throws IOException {
        Products products = new Products(1215);
        String productName = products.getName().replaceAll(" ", "-");
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(getDriver()).loadProductPage(productName).clickOnAddToCartBtn();
        Assert.assertTrue(productDetailsPage.productAddedToCartAlertMsg().contains(products.getName()));
    }


    @Test(dataProvider = "featuredProducts", dataProviderClass = MyDataProvider.class)
    public void addAllFeaturedProductToCart(Products products) {
        CartPage cartPage = new HomePage(getDriver()).load().getProductThumbnail().
                clickOnAddToCartBtn(products.getName()).
                clickOnViewCartLink();
        Assert.assertEquals(cartPage.getTextOfProductDetailsAddedToCartPage(), products.getName());
    }

    @Test(dataProvider = "featuredProducts", dataProviderClass = MyDataProvider.class)
    public void addAllProductToCartFromStorePage(Products products) {
        CartPage cartPage = new StorePage(getDriver()).load().getProductThumbnail().
                clickOnAddToCartBtn(products.getName()).
                clickOnViewCartLink();
        Assert.assertEquals(cartPage.getTextOfProductDetailsAddedToCartPage(), products.getName());
    }


}
