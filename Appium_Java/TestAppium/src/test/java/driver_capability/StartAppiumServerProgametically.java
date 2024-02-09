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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class StartAppiumServerProgametically {


    AppiumDriver driver;

    public static AppiumDriverLocalService service;

    //Options:  Node JS file path is Options and required only in case of your node and appium is on different server
    public static String NodeJSExePath = "C:\\Program Files\\nodejs\\node.exe";

    //Options:  Appium JS file path is Options and required only in case of your node and appium is on different server
    //public static String NodeJSMainPath = "C:\\Users\\T0185CK\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\appium.js";
    public static String NodeJSMainPath = "C:\\Users\\T0185CK\\AppData\\Roaming\\npm\\node_modules\\appium";

    //Server IP address where Appium Server is Running
    public static String ServerIPAddress = "127.0.0.1"; // Default Port

    // Server PORT  where Appium Server is Running
    public static Integer ServerPORT = 4723;

    // Log file path where you want to store Appium Server log
    public static String AppiumLogPath = "D:\\appiumlog.txt";


    @BeforeTest
    public void StartAppiumServer(){
         service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File(NodeJSExePath))
                        .withAppiumJS(new File(NodeJSMainPath))
                        .withIPAddress(ServerIPAddress)
                        .usingPort(ServerPORT)
                        .withTimeout(Duration.ofSeconds(15))
                        .withLogFile(new File(AppiumLogPath))
        );

        service.start();
    }



    @Test
    void TestApp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("platformName", MobilePlatform.ANDROID);
        cap.setCapability("appium:automationName", "uiautomator2");
        cap.setCapability("appium:deviceName", "vivi 22");
        cap.setCapability("appPackage", "io.appium.android.apis");
        cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), cap);

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Text\"]"));
        System.out.println(text.getText());
    }

    @AfterTest
    public void TearDown(){
        driver.quit();
        if(service.isRunning()){
            service.stop();
        }
    }
}