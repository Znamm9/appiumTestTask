package io.cucumber.skeleton.corePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    WebDriver driver;

    final int TIME_TO_WAIT_SECONDS = 15;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getElementByXpath(String locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public List<WebElement> getElementsByXpath(String locator){
        return driver.findElements(By.xpath(locator));
    }

    public WebElement getElementById(String id){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    public void waitForElementToDisappear(By by, String locator){
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        for (int i = 0; i<TIME_TO_WAIT_SECONDS*2; i++){
            if (elements.size() > 0){
                sleep(500);
            }else {
                return;
            }
            System.out.println("was waiting for element to disappear but element is still displayed");
        }
    }

    public void waitForListOfElements(String locator, int timeToWaitSeconds, int elementsNumExpected){
        for (int i = 0; i<timeToWaitSeconds*2; i++){
            if (getElementsByXpath(locator).size() != elementsNumExpected){
                sleep(500);
            }else {
                return;
            }
            System.out.println("was waiting for " + elementsNumExpected +
                    " elements, but got only " + getElementsByXpath(locator).size());
        }
    }
}
