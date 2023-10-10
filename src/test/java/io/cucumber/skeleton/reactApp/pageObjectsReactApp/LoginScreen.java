package io.cucumber.skeleton.reactApp.pageObjectsReactApp;

import io.appium.java_client.AppiumDriver;
import io.cucumber.skeleton.corePageObjects.BasePage;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BasePage {

    public LoginScreen(AppiumDriver driver){
        super(driver);
    }

    public WebElement getSubmitLoginBtn(){
        return getClickableElementByXpath("//android.view.ViewGroup[@content-desc='login']/android.widget.Button/android.widget.TextView");
    }
}
