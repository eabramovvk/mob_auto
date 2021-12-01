package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUi extends MainPageObject{

    protected static String
            MY_LISTS_BUTTON;

    public NavigationUi(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.WaitForElementAndClick(
                MY_LISTS_BUTTON,
                "Cannot find 'My lists' button",
                5
        );
    }

}