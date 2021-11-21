package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject{

    private static final String
            MY_LISTS_BUTTON = "xpath://*[@content-desc='My lists']";

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