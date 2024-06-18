package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManagerAbstract extends DriverManagerAbstract{
    @Override
    public void startDriver() {
        WebDriverManager.firefoxdriver().cachePath("Driver").setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
