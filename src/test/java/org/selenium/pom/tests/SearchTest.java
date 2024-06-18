package org.selenium.pom.tests;

import com.google.errorprone.annotations.concurrent.LazyInit;
import io.qameta.allure.*;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Products;
import org.selenium.pom.pages.ProductDetailsPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JavaFakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTest extends BaseTest {
    @Test
    public void searchWithPartialMatch() {
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver()).load().search(searchFor);
        storePage.isLoadedWithText(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
    }

    @Story("Business wants to search")
    @Epic("Backlog Items")
    @Feature("Search Engine")
    @Description("searching with exact match")
    @TmsLink("TC-123")
    @Issue("DEFECT-123")
    @Link("https://designDocumentJira.com")
    @Test(description = "It should be able to search the text with exact match")
    public void searchWithExactMatch() throws IOException {
        Products products = new Products(1215);
        ProductDetailsPage productDetailsPage = new StorePage(getDriver()).load().searchWithExactTxt(products.getName());
        Assert.assertEquals(productDetailsPage.getProductTitle(), products.getName()+"a");
    }

    @Test
    public void searchWithNonExistingProduct(){
        StorePage storePage = new StorePage(getDriver()).load().search(JavaFakerUtils.generateRandomName());
        Assert.assertEquals(storePage.displayMsgNonExistingProduct(), "No products were found matching your selection.");
    }


}
