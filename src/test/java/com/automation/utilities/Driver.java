package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class Driver {

    private Driver() {}

    private static WebDriver driver;

    public static WebDriver get() {
        // Test
        if (driver == null) {
//        	 String browser = "firefox";
            String browser = ConfigurationReader.get("browser");
//            String browser = System.getProperty("os.name").toLowerCase().contains("windows")?"edge":"chrome-local";
            switch (browser) {
                case "chrome-local":
                    System.setProperty("webdriver.chrome.driver", "browserdriver/chromedriver");
                    driver = new ChromeDriver((ChromeOptions) new ChromeOptions().setAcceptInsecureCerts(true));
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver((ChromeOptions) new ChromeOptions().setAcceptInsecureCerts(true));
                    break;
                case "chrome-headless":
                    System.setProperty("webdriver.chrome.driver", "browserdriver/chrome-headless-shell");
                    driver = new ChromeDriver(); // optional parameters: new ChromeOptions().setHeadless(true).addArguments("start-maximized").setAcceptInsecureCerts(true)
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setAcceptInsecureCerts(true);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options = new FirefoxOptions();
//                    options.setHeadless(true).setAcceptInsecureCerts(true);
                    driver = new FirefoxDriver(options);
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    // add browser options
                    driver = new EdgeDriver(edgeOptions);
                    break;

                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    SafariOptions safariOptions = new SafariOptions();
                    // add browser options
                    driver = new SafariDriver(safariOptions);
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
