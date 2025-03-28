package io.docuport_g1.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    /*
    Creating the private constructor so this class's object is not reachable from outside
     */

    private Driver(){}

    /*
    making driver instance private
    static - run before everything else and use in static method
     */

    // private static WebDriver driver;
    // implement threadLocal ro achieve multi thread locally
    private static InheritableThreadLocal <WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    reusable method that will return the same driver instance everytime called
     */

    /**
     * singleton pattern
     *@return
     */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserType = ConfigurationReader.getProperties("browser");
            switch (browserType.toLowerCase()){
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();

                    chromeOptions.addArguments("--headless=new");

                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");

                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;

                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
                    break;

                case "safari":
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
                    break;

                case "headless":
                    ChromeOptions headlessOptions = new ChromeOptions();

                    headlessOptions.addArguments("--headless=new");

                    headlessOptions.addArguments("--no-sandbox");
                    headlessOptions.addArguments("--disable-dev-shm-usage");
                    headlessOptions.addArguments("--disable-gpu");
                    headlessOptions.addArguments("--window-size=1920,1080");

                    driverPool.set(new ChromeDriver(headlessOptions));
                    break;
            }
        }
        return driverPool.get();
    }

    /**
     * closing driver
     * @author zck
     */
    public static void closeDriver(){
        if(driverPool.get()!=null){
            driverPool.get().quit();
            //driver=null;
            driverPool.remove();
        }
    }
}
