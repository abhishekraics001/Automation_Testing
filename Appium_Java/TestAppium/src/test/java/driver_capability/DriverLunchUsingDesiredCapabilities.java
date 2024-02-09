package driver_capability;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverLunchUsingDesiredCapabilities {

    AppiumDriver driver;



    @Test
    void TestApp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", MobilePlatform.ANDROID);
        cap.setCapability("appium:automationName", "uiautomator2");
        cap.setCapability("appium:deviceName", "vivi 22");

        cap.setCapability("appPackage", "io.appium.android.apis");
        cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");


        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Text\"]"));
        System.out.println(text.getText());
        text.click();


    }



    @AfterTest
    public void TearDown(){
        driver.quit();
    }
}
