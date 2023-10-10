package io.cucumber.skeleton.reactApp.pageObjectsReactApp;

import io.cucumber.skeleton.corePageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeScreen extends BasePage {

    String listOfReactElementsLocator = "//android.view.ViewGroup[@content-desc]/android.view.ViewGroup";

    public HomeScreen(WebDriver driver) {
        super(driver);
    }

    public void waitForListOfReactNativeElements(){
        waitForListOfElements(listOfReactElementsLocator, 5, 10);
    }

    public List<WebElement> getListOfElemetTypes() {
        waitForListOfReactNativeElements();
        return getElementsByXpath(listOfReactElementsLocator);
    }

    public void closeReactNativeWarningIfExists() {
        String okButton = "android:id/button1";
        if (getElementsById(okButton).size() > 0){
            getElementById(okButton).click();
        }
    }

    public WebElement getNativeView(){
        return getClickableElementByXpath("//android.widget.TextView[@content-desc='chainedView']");
    }

    public WebElement getSliderView() {
        return getClickableElementByXpath("//android.view.ViewGroup[@content-desc=\"slider1\"]/android.view.ViewGroup");
    }
}
