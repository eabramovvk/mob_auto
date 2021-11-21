package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabiliesByPlatformEnv();
        this.getDriver(capabilities);

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);


    }

    @Override
    protected void tearDown() throws Exception{
        driver.quit();

        super.tearDown();
    }

    private DesiredCapabilities getCapabiliesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "emulator-5554");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/e.abramov/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS))
        {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("platformVersion", "14.5");
            capabilities.setCapability("app", "/Users/e.abramov/JavaAppiumAutomation/apks/Wikipedia.app");
        } else
        {
            throw new Exception("Cannot get run platform from env variable. Platform value: " + platform);
        }

        return capabilities;

    }

    private void getDriver(DesiredCapabilities capabilities) throws Exception
    {
        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID))
        {
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS))
        {
            driver = new IOSDriver(new URL(AppiumURL), capabilities);
        } else
        {
            throw new Exception("Cannot get driver by env variable. Platform value: " + platform);
        }
    }
}
