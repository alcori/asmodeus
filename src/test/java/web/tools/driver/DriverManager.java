package web.tools.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import web.tools.driver.hardcode.DriverLoad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class DriverManager {
    ResourceBundle browserBundle = ResourceBundle.getBundle("testOptions");
    static ArrayList<WebDriver> drivers = new ArrayList<>();
    @Test
    public void driverSetup() throws IOException {
        String version = DriverLoad.getSeleniumVersion();

        if (browserBundle.getString("testdata.driverManager.pattern")
                .equalsIgnoreCase("io.github.bonigarcia")) {
            WebDriverManager.chromedriver().setup();
            WebDriverManager.firefoxdriver().setup();
            WebDriverManager.edgedriver().setup();
            WebDriverManager.safaridriver().setup();
        } else {
            System.setProperty("webdriver.chrome.driver", "target/driver/selenium-" + version + "/");
            System.setProperty("webdriver.firefox.driver", "target/driver/selenium-" + version + "/");
            System.setProperty("webdriver.edge.driver", "target/driver/selenium-" + version + "/");
            System.setProperty("webdriver.safari.driver", "target/driver/selenium-" + version + "/");
        }
        drivers.add(0, new ChromeDriver());
        drivers.add(1, new FirefoxDriver());
        drivers.add(2, new EdgeDriver());
        drivers.add(3, new SafariDriver());
    }
    public static WebDriver webDriver(String browser){
        switch(browser){
            case "chrome":
                return drivers.get(0);
            case "firefox":
                return drivers.get(1);
            case "edge":
                return drivers.get(2);
            case "safari":
                return drivers.get(3);
        }
        return drivers.get(0);
    }
    public static WebDriver webDriver(){
        return drivers.get(0);
    }
}
