package Activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Activity1 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();

        driver.get("http://alchemy.hguy.co/crm");
    }

    @Test
    public void testCase1(){
        System.out.println("Title of the page: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "SuiteCRM" );


    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
