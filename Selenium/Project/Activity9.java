package Activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Activity9 {
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



        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moduleTab_9_Leads")));*/

        WebElement m = driver.findElement(By.id("moduleTab_9_Leads"));
        m.click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='moduleTitle']"))));

        WebElement table = driver.findElement(By.xpath("//form[2]/div[3]/table/tbody"));

        List<WebElement> rows = table.findElements(By.xpath("//tr"));
        System.out.println(rows.size());

        List<WebElement> name = table.findElements(By.xpath("//td[3][contains(@class,'inlineEdit')]"));
        List<WebElement> roles = table.findElements(By.xpath("//td[8]"));

        Iterator<WebElement> data1 = name.iterator();
        Iterator<WebElement> data2 = roles.iterator();

        for(int i =0; i<11; i++) {

            if(data1.hasNext()) {
                WebElement name1 = data1.next();
                System.out.println(name1.getText());
            }
            if(data2.hasNext()) {
                WebElement role1 = data2.next();
                System.out.println(role1.getText());
            }

        }


        driver.close();


    }
}
