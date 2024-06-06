import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
@Tag("orderitem")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderItemIT {

    public WebDriver webDriver;

    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:username\"]")).sendKeys("admin.staff@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:password\"]")).sendKeys("444-3049");
        webDriver.findElement(By.xpath("//*[@id=\"loginform\"]/input[4]")).click();
    }

    @Test
    @Order(1)
    public void testCreateOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"orderItemForm:orderItemID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"orderItemForm:orderID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"orderItemForm:Rabatte\"]")).sendKeys("0.1");
        webDriver.findElement(By.xpath("//*[@id=\"orderItemForm:ListenPreis\"]")).sendKeys("999");
        webDriver.findElement(By.xpath("//*[@id=\"orderItemForm:quantity\"]")).sendKeys("10");
        webDriver.findElement(By.xpath("//*[@id=\"orderItemForm:ProduktID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"orderItemForm\"]/input[8]")).click();
        Thread.sleep(600);
        String expect = "orderItem saved successfully";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"jakarta_faces_developmentstage_messages\"]/li/span")).getText();
        Assertions.assertEquals(expect, actual);
    }

   @Test
   @Order(2)
    public void testSearchOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"orderItem:orderId\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"orderItem:orderId\"]")).sendKeys("1");
    }
    @Test
    @Order(3)
    public void testEditOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"orderItem:orderItemTable:0:detail\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"orderItemDetail\"]/input[5]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"orderItemDetail\"]/input[5]")).sendKeys("100");
        webDriver.findElement(By.xpath("//*[@id=\"orderItemDetail\"]/input[8]")).click();
        Thread.sleep(600);
        String expect = "orderItem updated successfully";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"jakarta_faces_developmentstage_messages\"]/li/span")).getText();
        Assertions.assertEquals(expect, actual);
    }
    @Test
    @Order(4)
    public void testDeleteOrderItem() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"orderItem:orderId\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"orderItem:orderId\"]")).sendKeys("1");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"orderItem:orderItemTable:0:delete\"]")).click();
//        Thread.sleep(600);
//        String expect = "orderItem deleted successfully";
//        String actual = webDriver.findElement(By.xpath("//*[@id=\"jakarta_faces_developmentstage_messages\"]/li/span")).getText();
//        Assertions.assertEquals(expect, actual);
    }
    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

