import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Tag("brand")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BrandIT {
    public WebDriver webDriver;

    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:username\"]")).sendKeys("admin.staff@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:password\"]")).sendKeys("444-3049");
        webDriver.findElement(By.xpath("//*[@id=\"loginform\"]/input[4]")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navForm")));
    }

    @Test
    @Order(1)
    public void createBrandTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[6]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"brandsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"brandform:brandName\"]")).sendKeys("Hello World");
        webDriver.findElement(By.xpath("//*[@id=\"brandform\"]/input[3]")).click();
        Thread.sleep(600);
        String expect = "Marke erfolgreich gespeichert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"brandform:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @Order(2)
    public void searchBrandTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[6]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"brandsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"brand:brandName\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"brand:brandName\"]")).sendKeys("Hello World");
        Thread.sleep(600);
    }

    @Test
    @Order(3)
    public void deleteBrandTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[6]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"brandsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"brand:brandName\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"brand:brandName\"]")).sendKeys("Hello World");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"brand:brandTable:0:delete\"]")).click();
        Thread.sleep(600);
        String expect = "Marke erfolgreich gelöscht";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"messageForm:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }

    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
