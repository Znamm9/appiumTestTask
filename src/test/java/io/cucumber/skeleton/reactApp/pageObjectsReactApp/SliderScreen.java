package io.cucumber.skeleton.reactApp.pageObjectsReactApp;

import io.cucumber.skeleton.corePageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SliderScreen extends BasePage {

    public SliderScreen(WebDriver driver) {
        super(driver);
    }

    public WebElement getSlider(){
        return getClickableElementByXpath("//android.widget.SeekBar[@content-desc='slider']");
    }
}
