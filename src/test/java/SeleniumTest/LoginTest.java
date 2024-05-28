package SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    public WebDriver webDriver;
    @BeforeClass
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
    }

    @Test
    public void login(){
        try {
            webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("fabiola.jackson@bikes.shop");
            webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("555-5554");
            webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();

            // Verify that the URL is correct after login
            String currentUrl = webDriver.getCurrentUrl().split(";")[0];
            Assert.assertEquals(currentUrl,"http://localhost:8080/team-10/loginPage.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Login test failed due to an exception: " + e.getMessage());
        }
    }
    @AfterTest
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
