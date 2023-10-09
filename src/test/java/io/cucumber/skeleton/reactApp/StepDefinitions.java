package io.cucumber.skeleton.reactApp;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.reactApp.pageObjectsReactApp.HomeScreen;
import io.cucumber.skeleton.reactApp.pageObjectsReactApp.LoginScreen;
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
//        capabilities.setCapability("appActivity", "com.example.app.MainActivity");
//        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("udid", getDeviceId());
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
        capabilities.setCapability("permissions", "android.permission.READ_EXTERNAL_STORAGE");
        capabilities.setCapability("automationName", "UiAutomator2");

//        for some reason this next line doesn't work as expected, but before I used it
        capabilities.setCapability("autoGrantPermissions", "true");

        capabilities.setCapability(MobileCapabilityType.APP, new File("src/test/resources/appiumChallenge.apk").getAbsolutePath());
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
        capabilities.setCapability("appium:platformVersion", "7.1.1");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
//        sauceOptions.setCapability("username", "oauth-v.rudenko106-d1b39");
        sauceOptions.setCapability("username", getSauceLabsUserName());
//        sauceOptions.setCapability("accessKey", "7ccdc9d5-8cda-4a68-9959-89059265db0c");
        sauceOptions.setCapability("accessKey", getSauceLabsKey());
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


    public void initPages(){
        loginScreen = new LoginScreen(driver);
        homeScreen = new HomeScreen(driver);
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
    }

    @When("I click login")
    public void i_click_login() {
        loginScreen.getSubmitLoginBtn().click();
    }
    @Then("User is on home screen")
    public void userOnHomeScreen() {
        Assert.assertTrue(homeScreen.getNativeView().isDisplayed());
    }

    @Then("I see <{int}> elements")
    public void iSeeElements(int numberOfExpectedElements) {
        Assert.assertEquals(homeScreen.getListOfElemetTypes().size(), numberOfExpectedElements);
    }
}
