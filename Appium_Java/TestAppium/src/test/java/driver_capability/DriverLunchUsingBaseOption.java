package driver_capability;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverLunchUsingBaseOption {

    AppiumDriver driver;

    @BeforeClass
    public void Setup(){

    }


    @Test
    void TestApp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("appium:automationName", "uiautomator2")
                .amend("platformName", "Android")
                .amend("appium:deviceName", "vivi 22")
                //.amend("appium:ensureWebviewsHavePages", true)
                //.amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600);
                //.amend("appium:connectHardwareKeyboard", true);


        URL getUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(getUrl, options);

        WebElement text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Text\"]"));
        System.out.println(text.getText());


    }



    @AfterTest
    public void TearDown(){
        driver.quit();
    }
}
