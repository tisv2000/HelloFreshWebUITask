package core.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

@Slf4j
public class DriverHolder {

    //region ===== Working with WebDriver instance =====
    static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        if (!isDriverCreated()) {
            log.error("Web driver instance wasn't created");
            throw new WebDriverException("Web driver instance wasn't created");
        }
        return driverThreadLocal.get();
    }

    public static boolean isDriverCreated() {
        return driverThreadLocal.get() != null;
    }

    public static void removeDriver() {
        driverThreadLocal.remove();
    }
    //endregion
}
