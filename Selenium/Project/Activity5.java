package Activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity5 {
    public static void main(String[] args) {


        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        driver.get("http://alchemy.hguy.co/crm");

        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");

        driver.findElement(By.id("bigbutton")).click();

        WebElement homepage = driver.findElement(By.id("tab0"));
        System.out.println("Verify Homepage: " + homepage.isDisplayed());

        WebElement color = driver.findElement(By.className("container-fluid"));
        String s = color.getCssValue("color");

        System.out.println("Color of the Header is: " + s);

        driver.close();
    }

}
