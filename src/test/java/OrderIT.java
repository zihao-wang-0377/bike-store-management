import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Tag("order")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderIT {
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
    public void testCreateOrder() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[4]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"ordersPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"orderForm:customerID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"orderForm:staffID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"orderForm:storeID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"orderForm:Bestelldatum\"]")).sendKeys("26.08.2020");
        webDriver.findElement(By.xpath("//*[@id=\"orderForm\"]/input[7]")).click();
        Thread.sleep(600);
        String expect = "Bestellung erfolgreich gespeichert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"orderForm:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }

   @Test
   @Order(2)
    public void testSearchOrder() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[4]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"ordersPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"order:orderDate\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"order:orderDate\"]")).sendKeys("26.08.2020");
        Thread.sleep(600);
    }

    @Test
    @Order(3)
    public void testEditOrder() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[4]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"ordersPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"order:orderDate\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"order:orderDate\"]")).sendKeys("26.08.2020");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"order:orderTable:0:detail\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"orderDetail\"]/input[3]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"orderDetail\"]/input[3]")).sendKeys("28.08.2020");
        webDriver.findElement(By.xpath("//*[@id=\"orderDetail\"]/input[10]")).click();
        Thread.sleep(600);
        String expect = "Bestellung erfolgreich aktualisiert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"orderDetail:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @Order(4)
    public void testDeleteOrder() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[4]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"ordersPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"order:orderDate\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"order:orderDate\"]")).sendKeys("28.08.2020");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"order:orderTable:0:delete\"]")).click();
        Thread.sleep(600);
        String expect = "Bestellung erfolgreich gelöscht";
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
