package org.selenium.pom.dataprovider;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Products;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDataProvider {
    @DataProvider(name = "featuredProducts", parallel = true)
    public Object[] getAllProducts() throws IOException {
        List<Products> list = new ArrayList<>();
        Products[] products = JacksonUtils.deserializeJson("products.json", Products[].class);
        for (Products featuredProducts : products) {
            if (featuredProducts.isFeatured())
                list.add(featuredProducts);
        }
        Products[] featuredProducts = new Products[list.size()];
        for (int getEachProduct = 0; getEachProduct < list.size(); getEachProduct++) {
            featuredProducts[getEachProduct] = list.get(getEachProduct);
        }
        return featuredProducts;
    }

    @DataProvider(name = "billingAddress", parallel = true)
    public Object[] getAllBillingAddress() throws IOException {
        List<Products> list = new ArrayList<>();
        BillingAddress[] billingAddresses = JacksonUtils.deserializeJson("billingAddresses.json", BillingAddress[].class);
        return billingAddresses;
    }
}
