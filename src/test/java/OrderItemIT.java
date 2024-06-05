import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderItemIT {

    public WebDriver webDriver;

    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("admin.staff@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("444-3049");
        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }

    @Test
    @Order(1)
    public void testCreateOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:orderItemID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:orderID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:Rabatte\"]")).sendKeys("0.1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:ListenPreis\"]")).sendKeys("999");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:quantity\"]")).sendKeys("10");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:ProduktID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[8]")).click();
    }

   @Test
   @Order(2)
    public void testSearchOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderId\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderId\"]")).sendKeys("1");
    }
    @Test
    @Order(3)
    public void testEditOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderItemTable:0:j_idt72\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[5]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[5]")).sendKeys("100");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[8]")).click();
    }
    @Test
    @Order(4)
    public void testDeleteOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderId\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderId\"]")).sendKeys("1");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderItemTable:0:j_idt73\"]")).click();
    }
    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

