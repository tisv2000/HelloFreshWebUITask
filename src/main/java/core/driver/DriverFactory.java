package core.driver;

import core.exceptions.ConfigurationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Slf4j
public class DriverFactory {

    private static final String DRIVER_NAME_PROPERTY = "driver.name";
    private static final String DRIVERS_PATH = "src/main/resources/drivers/";

    public static WebDriver createNewWebDriverInstance() {
        String driverName = System.getProperty(DRIVER_NAME_PROPERTY).toUpperCase();
        log.debug("Create WebDriver Instance for {}", driverName);
        switch (driverName) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", getDriverPathForCurrentOS() + "chromedriver");
                return new ChromeDriver();
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver", getDriverPathForCurrentOS() + "geckodriver");
                return new FirefoxDriver();
            default:
                log.error("Browser '{}' is not supported", driverName);
                throw new ConfigurationException("Browser '" + driverName + "' is not supported");
        }
    }

    private static String getDriverPathForCurrentOS() {
        if (SystemUtils.IS_OS_MAC) return DRIVERS_PATH + "macos/";
        if (SystemUtils.IS_OS_LINUX) return DRIVERS_PATH + "linux/";
        log.error("Current OS '{}' is not supported", System.getProperty("os.name"));
        throw new ConfigurationException("Current OS '" + System.getProperty("os.name") + "' is not supported");
    }
}
