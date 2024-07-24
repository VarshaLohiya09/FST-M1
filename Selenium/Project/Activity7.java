package Activities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Activity7 {
    public static void main(String[] args) {

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("http://alchemy.hguy.co/crm");

        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");

        driver.findElement(By.id("bigbutton")).click();

        WebElement navmenu = driver.findElement(By.id("toolbar"));
        System.out.println("Verify Homepage: " + navmenu.isDisplayed());

        driver.findElement(By.className("desktop-toolbar")).isDisplayed();

        WebElement sales = driver.findElement(By.xpath("//*[contains(text(),'Sales')]"));
        System.out.println("Activities is present: " + sales.isDisplayed());
        System.out.println("Activities is present: " + sales.getText());
        sales.click();

        Actions a = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moduleTab_9_Leads")));

        WebElement m = driver.findElement(By.id("moduleTab_9_Leads"));
        a.moveToElement(m).click().perform();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(40));

        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@id,'adspan_1bf01e91')]")));
        WebElement icon = driver.findElement(By.xpath("//span[contains(@id,'adspan_1bf01e91')]"));

        icon.click();

        System.out.println();


        WebElement f = driver.findElement(By.xpath("//div[@class='open ui-dialog-content ui-widget-content']/span"));

        System.out.println(f.getText());

        driver.close();



    }
}
