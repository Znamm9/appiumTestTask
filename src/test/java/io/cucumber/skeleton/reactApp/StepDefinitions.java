package io.cucumber.skeleton.reactApp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.reactApp.pageObjectsReactApp.HomeScreen;
import io.cucumber.skeleton.reactApp.pageObjectsReactApp.LoginScreen;
import io.cucumber.skeleton.reactApp.pageObjectsReactApp.NativeViewScreen;
import io.cucumber.skeleton.reactApp.pageObjectsReactApp.SliderScreen;
import org.junit.Assert;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class StepDefinitions{

    private AndroidDriver driver;
    String APPIUM_LOCAL_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    String APPIUM_REMOTE_SERVER_URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    public DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("udid", getDeviceId());
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP, new File("src/test/resources/appiumChallenge.apk").getAbsolutePath());
        capabilities.setCapability("fullReset","true");
        return capabilities;
    }

    private String getDeviceId() {
        String defaultId = "emulator-5554";
        if (System.getProperty("deviceid") == null){
            System.out.println("device id is not set using default on with id = " + defaultId);
            return defaultId;
        }else {
            return System.getProperty("saucekey");
        }
    }

    public MutableCapabilities getRemoteCapabilities() {

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:app", "storage:filename=appiumChallenge.apk");  // The filename of the mobile app
        capabilities.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
//        sauce labs have only 8.1 Android for x86 arch type
        capabilities.setCapability("appium:platformVersion", "8.1");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-v.rudenko106-d1b39");
//        sauceOptions.setCapability("username", getSauceLabsUserName());
        sauceOptions.setCapability("accessKey", "7ccdc9d5-8cda-4a68-9959-89059265db0c");
//        sauceOptions.setCapability("accessKey", getSauceLabsKey());
        sauceOptions.setCapability("build", "appium-build-QC4A4");
        sauceOptions.setCapability("name", "<your test name>");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("sauce:options", sauceOptions);
        return capabilities;
    }

    private String getSauceLabsKey() {
        if (System.getProperty("saucekey") == null){
            System.out.println("sauce labs key is not set, returning null instead");
            return null;
        }else {
            return System.getProperty("saucekey");
        }
    }

    private String getSauceLabsUserName() {
        if (System.getProperty("saucename") == null){
            System.out.println("sauce labs name is not set, returning null instead");
            return null;
        }else {
            return System.getProperty("saucename");
        }
    }

    public boolean isRemoteRun() {
        return System.getProperty("remote") != null && System.getProperty("remote").equals("true");
    }

    public void initRemoteAndroidDriver(MutableCapabilities capabilities) {
        try {
            driver = new AndroidDriver(
                    new URL(APPIUM_REMOTE_SERVER_URL), capabilities
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initLocalAndroidDriver(DesiredCapabilities capabilities) {
        try {
            driver = new AndroidDriver(
                    new URL(APPIUM_LOCAL_SERVER_URL), capabilities
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    LoginScreen loginScreen;
    HomeScreen homeScreen;
    NativeViewScreen nativeViewScreen;
    SliderScreen sliderScreen;


    public void initPages(){
        loginScreen = new LoginScreen(driver);
        homeScreen = new HomeScreen(driver);
        nativeViewScreen = new NativeViewScreen(driver);
        sliderScreen = new SliderScreen(driver);
    }

    @Given("app started")
    public void app_started() {
        if (isRemoteRun()){
            MutableCapabilities capabilities = getRemoteCapabilities();
            initRemoteAndroidDriver(capabilities);
        } else {
            DesiredCapabilities capabilities = getLocalCapabilities();
            initLocalAndroidDriver(capabilities);
        }
        initPages();
        homeScreen.closeReactNativeWarningIfExists();
    }

    @When("I click login")
    public void i_click_login() {
        loginScreen.getSubmitLoginBtn().click();
    }
    @Then("User is on home screen")
    public void userOnHomeScreen() {
        Assert.assertTrue(homeScreen.getNativeView().isDisplayed());
    }

    @Then("I see more than <{int}> elements")
    public void iSeeElements(int minimumNumberOfElements) {
        Assert.assertTrue(homeScreen.getListOfElemetTypes().size() > minimumNumberOfElements);
    }

    @When("I click native view")
    public void iClickNativeView() {
        homeScreen.getNativeView().click();
    }

    @Then("I see native elements")
    public void iSeeNativeElements() {
        Assert.assertEquals(nativeViewScreen.getListOfViews().size(), 3);
    }

    @And("I click slider")
    public void iClickSlider() {
        homeScreen.getSliderView().click();
    }

    @Then("I see slider")
    public void iSeeSlider() {
        Assert.assertTrue(sliderScreen.getSlider().isDisplayed());
    }
}
