package io.cucumber.skeleton.reactApp.pageObjectsReactApp;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.skeleton.corePageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomeScreen extends BasePage {

    String listOfReactElementsLocator = "//android.view.ViewGroup[@content-desc]/android.view.ViewGroup";

    public HomeScreen(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='chainedView']/android.view.ViewGroup")
    public WebElement nativeView;

    public WebElement getNativeView(){
        return getElementByXpath("//android.view.ViewGroup[@content-desc=\"chainedView\"]/android.view.ViewGroup");
    }

    public void waitForListOfReactNativeElements(){
        waitForListOfElements(listOfReactElementsLocator, 5, 10);
    }

    public List<WebElement> getListOfElemetTypes() {
        waitForListOfReactNativeElements();
        return getElementsByXpath(listOfReactElementsLocator);
    }
}
