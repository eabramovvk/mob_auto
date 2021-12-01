package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUi;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.iOSNavigationUI;

public class NavigationUIFactory {
    public static NavigationUi get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidNavigationUI(driver);
        } else {
            return new iOSNavigationUI(driver);
        }
    }
}