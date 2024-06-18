package org.selenium.pom.base;

import io.qameta.allure.Allure;
import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.DriverManagerOriginal;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstractFactory;
import org.selenium.pom.utils.CookieUtils;
import org.selenium.pom.utils.ScreenshotUtility;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal();
    private ThreadLocal<DriverManagerAbstract> driverManagerAbstract = new ThreadLocal<>();

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    public DriverManagerAbstract getDriverManagerAbstract() {
        return this.driverManagerAbstract.get();
    }

    public void setDriverManagerAbstract(DriverManagerAbstract driverManagerAbstract) {
        this.driverManagerAbstract.set(driverManagerAbstract);
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void gearUp(@Optional String browser) {
        browser = System.getProperty("browser", browser);
        if (browser == null)
            browser = "CHROME";
        //setDriver(new DriverManagerOriginal().initializeDriver(browser));
        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        //If using factory design pattern using abstract class then use below line of code
        //setDriver(getDriverManagerAbstract().getDriver());
        System.out.println("CURRENT THREAD:" + Thread.currentThread().getId() + " , " + "DRIVER: " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void tearDown(@Optional String browser, ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = "scr" + File.separator + browser + File.separator + result.getTestClass().getRealClass().getSimpleName() + "_" +
                    result.getMethod().getMethodName() + ".png";
            File destFile = new File(path);
            takeScreenshot(destFile);
            //Allure.addAttachment("Page Screenshot",FileUtils.openInputStream(destFile));
            ScreenshotUtility.placeScreenShotInAllureReport(getDriver());
            //takeScreenshotUsingAshot(destFile);
        }
        System.out.println("CURRENT THREAD:" + Thread.currentThread().getId() + " , " + "DRIVER: " + getDriver());
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredToSeleniumCookies(cookies);
        for (Cookie cookie : seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

    private void takeScreenshotUsingAshot(File destFile) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());
        try {
            ImageIO.write(screenshot.getImage(), "png", destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
