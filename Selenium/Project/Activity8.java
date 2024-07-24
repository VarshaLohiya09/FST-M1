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
import java.util.Iterator;
import java.util.List;

public class Activity8 {
    public static void main(String[] args) {

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        driver.get("http://alchemy.hguy.co/crm");

        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");

        driver.findElement(By.id("bigbutton")).click();
        WebElement sales = driver.findElement(By.xpath("//*[contains(text(),'Sales')]"));
        System.out.println("Activities is present: " + sales.isDisplayed());
        System.out.println("Activities is present: " + sales.getText());
        sales.click();

        Actions a = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moduleTab_9_Accounts")));

        WebElement m = driver.findElement(By.id("moduleTab_9_Accounts"));
        a.moveToElement(m).click().perform();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='list view table-responsive']/tbody/tr")));

        WebElement table = driver.findElement(By.xpath("//table[@class='list view table-responsive']/tbody"));
        List<WebElement> rows = table.findElements(By.xpath("//tr[@class='oddListRowS1']"));
        System.out.println("No.of rows: " + rows.size());

        Iterator<WebElement> odd = rows.iterator();
        for ( int i =0; i<5; i++)
        {
            WebElement name = odd.next();
            System.out.println(name.getText());
        }


        driver.close();




    }
}
