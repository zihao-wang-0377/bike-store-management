import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Tag("login")
public class LoginIT {
    public WebDriver webDriver;

    @BeforeEach
    void setup() {
        webDriver = new ChromeDriver();
        // 打开应用首页，通常未登录会被重定向到 loginPage，或者首页就是登录入口
        webDriver.get("http://localhost:8080/team-10");
    }

    @Test
    void login() {
        try {
            webDriver.findElement(By.xpath("//*[@id=\"loginform:username\"]")).sendKeys("admin.staff@bikes.shop");
            webDriver.findElement(By.xpath("//*[@id=\"loginform:password\"]")).sendKeys("444-3049");
            webDriver.findElement(By.xpath("//*[@id=\"loginform\"]/input[4]")).click();
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navForm")));

            // Verify that the URL is correct after login
            String currentUrl = webDriver.getCurrentUrl().split(";")[0];
            assertEquals("http://localhost:8080/team-10/homePage.xhtml", currentUrl);

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
