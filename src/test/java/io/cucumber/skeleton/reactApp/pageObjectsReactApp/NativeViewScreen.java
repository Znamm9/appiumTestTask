package io.cucumber.skeleton.reactApp.pageObjectsReactApp;

import io.cucumber.skeleton.corePageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NativeViewScreen extends BasePage {

    public NativeViewScreen(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getListOfViews() {
        String elementsLocator = "(//android.widget.TextView[@content-desc='textView'])";
        waitForListOfElements(elementsLocator, 5, 3);
        return getElementsByXpath(elementsLocator);
    }

    public WebElement getNativeElementNum(int num) {
        return getClickableElementByXpath("(//android.widget.TextView[@content-desc='textView']["+ num + "])");
    }
}
