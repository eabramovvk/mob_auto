package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class AndroidNavigationUI extends NavigationUi {
    static {
        MY_LISTS_BUTTON = "xpath://*[@content-desc='My lists']";
    }
    public AndroidNavigationUI(AppiumDriver driver){
        super(driver);
    }
}