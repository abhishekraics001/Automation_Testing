package driver_capability;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriveAndroidCommand {

    AndroidDriver driver;

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

    String appAPkBundledID = "io.appium.android.apis";
    String appAPKPath = "\\APK\\ApiDemos-debug.apk";


    @BeforeTest
    public void StartAppiumServer(){
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(NodeJSExePath))
                .withAppiumJS(new File(NodeJSMainPath))
                .withIPAddress(ServerIPAddress)
                .usingPort(ServerPORT)
                .withTimeout(Duration.ofSeconds(20))
                .withLogFile(new File(AppiumLogPath))
        );

        service.start();
    }


    @Test
    void TestApp() throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", MobilePlatform.ANDROID);
        cap.setCapability("appium:automationName", "UIAutomator2");
        //cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        cap.setCapability("newCommandTimeout", 80000);
        cap.setCapability("appium:deviceName", "vivi 22");


        cap.setCapability("appium:fastReset", true);
        //cap.setCapability("appium:fullReset", true);
        cap.setCapability("appium:noReset", false);

         cap.setCapability("app", System.getProperty("user.dir")+appAPKPath);
        cap.setCapability("appPackage", "io.appium.android.apis");
        cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");


        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);



     /*   if(!driver.isAppInstalled(appAPkBundledID)){
            driver.installApp(System.getProperty("user.dir")+appAPKPath);
        }*/


        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //Thread.sleep(8000);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        driver.activateApp(appAPkBundledID);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        Thread.sleep(8000);
        driver.runAppInBackground(Duration.ofSeconds(3));
        Thread.sleep(5000);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        boolean isTerminated = driver.terminateApp(appAPkBundledID);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if(isTerminated){
            driver.activateApp(appAPkBundledID);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        //Check the listview visibility
        boolean isContentListIsDisplayOrNot = driver.findElement(AppiumBy.id("android:id/list")).isDisplayed();
        System.out.println("isContentListIsDisplayOrNot: "+isContentListIsDisplayOrNot);


        //Find all elements from listview
        List<WebElement> elementList = driver.findElements(AppiumBy.id("android:id/text1"));
        for (WebElement items : elementList ){
            System.out.println("list items: "+items.getText());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



        //Find elements by accessibilityId and get the text and then Click
        WebElement app = driver.findElement(AppiumBy.accessibilityId("App"));
        String textString = app.getText();
        app.click();


        //Find elements by Xpath & contains and get the text and then Click
        WebElement activity = driver.findElement(AppiumBy.xpath("//*[contains(@text, \"Activity\")]"));
        textString = activity.getText();
        activity.click();



        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"+".scrollIntoView(new UiSelector()" + ".textMatches(\"Screen Orientation"+ "\").instance(0))")).click();


        driver.runAppInBackground(Duration.ofSeconds(3));

        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();



        //Find elements by Xpath & content-desc and get the text and then Click
        WebElement text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Text\"]"));
         textString = text.getText();
        text.click();






        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }





    @AfterTest
    public void TearDown(){
        //driver.removeApp(appAPkBundledID);
        driver.quit();
        if(service.isRunning()){
            service.stop();
        }
    }
}
