package Activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity6 {
    public static void main(String[] args) {

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        driver.get("http://alchemy.hguy.co/crm");

        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");

        driver.findElement(By.id("bigbutton")).click();

        WebElement navmenu = driver.findElement(By.id("toolbar"));
        System.out.println("Verify Homepage: " + navmenu.isDisplayed());

         driver.findElement(By.className("desktop-toolbar")).isDisplayed();

         WebElement actopt = driver.findElement(By.xpath("//*[contains(text(),'Activities')]"));
        System.out.println("Activities is present: " + actopt.isDisplayed());
        System.out.println("Activities is present: " + actopt.getText());
         actopt.click();

         driver.close();


    }
}
