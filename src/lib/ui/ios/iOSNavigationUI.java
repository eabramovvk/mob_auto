package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class iOSNavigationUI extends NavigationUi {
    static {
        MY_LISTS_BUTTON = "id:Saved";
    }
    public iOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}