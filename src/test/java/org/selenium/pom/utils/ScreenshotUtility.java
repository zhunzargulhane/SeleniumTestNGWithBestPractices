package org.selenium.pom.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BaseTest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ScreenshotUtility extends BaseTest {
    @Attachment(value = "Page screenshot", type = "image/png")
    public static void placeScreenShotInAllureReport(WebDriver driver) {
        byte[] expected = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Page screenshot", new ByteArrayInputStream(expected));
    }
}
