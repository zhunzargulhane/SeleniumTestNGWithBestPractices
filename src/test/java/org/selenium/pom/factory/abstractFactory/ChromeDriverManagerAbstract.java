package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {
    @Override
    public void startDriver() {
        WebDriverManager.chromedriver().cachePath("Driver").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
}
