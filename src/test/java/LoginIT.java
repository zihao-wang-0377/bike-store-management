import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginIT {
    public WebDriver webDriver;

    @BeforeEach
    void setup() {
        // 设置 ChromeDriver 的路径，例如：
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
    }

    @Test
    void login() {
        try {
            webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("admin.staff@bikes.shop");
            webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("444-3049");
            webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();

            // Verify that the URL is correct after login
            String currentUrl = webDriver.getCurrentUrl().split(";")[0];
            assertEquals("http://localhost:8080/team-10/loginPage.xhtml", currentUrl);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Login test failed due to an exception: " + e.getMessage());
        }
    }

    @AfterEach
    void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
