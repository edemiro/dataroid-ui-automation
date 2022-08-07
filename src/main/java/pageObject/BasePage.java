package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected static WebDriver driver;
    static HttpURLConnection huc = null;
    static By agreeButton = By.name("agree");

    public static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static boolean checkAgree() {
        return isDisplayed(agreeButton);
    }

    public static void clickAgree() {
        if (checkAgree() == true) {
            driver.findElement(By.name("agree")).click();
        }
    }

    public static boolean isDisplayed(By element) {
        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void focusAndClick(WebElement element) {
        new Actions(driver).moveToElement(element).click().perform();
    }

    public static boolean isLinkValid(String linkURL) throws IOException {
        huc = (HttpURLConnection) (new URL(linkURL).openConnection());
        huc.setRequestMethod("GET");
        huc.connect();
        int respCode = huc.getResponseCode();
        if (respCode >= 400) {
            System.out.println(linkURL + " is a broken link");
            return false;
        } else {
            System.out.println(linkURL + " is a valid link");
        }
        return true;
    }

    public static void waitUntilPageLoaded() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Thread.sleep(5000);
    }

    public static void waitUntilToBeVisibleElement(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }
}
