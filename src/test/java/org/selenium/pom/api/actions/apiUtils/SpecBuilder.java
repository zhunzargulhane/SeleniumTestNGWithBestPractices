package org.selenium.pom.api.actions.apiUtils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import okhttp3.Request;
import org.selenium.pom.utils.ConfigLoader;

import static io.restassured.RestAssured.baseURI;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().addFilter(new AllureRestAssured()).
                setBaseUri(ConfigLoader.getInstance().getBaseUrl()).
                log(LogDetail.ALL).build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }
}
