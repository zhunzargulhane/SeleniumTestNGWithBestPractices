package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.DriverType;

public class DriverManagerAbstractFactory {
    public static DriverManagerAbstract getManager(DriverType driverType){
        switch (driverType){
            case CHROME -> {
                return new ChromeDriverManagerAbstract();
            }
            case FIREFOX -> {
                return new FirefoxDriverManagerAbstract();
            }
            default -> throw new IllegalArgumentException("Not found "+driverType);
        }
    }
}
