import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class OrderIT {
    public WebDriver webDriver;


    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("fabiola.jackson@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("555-5554");
        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }

    @Test
    public void testCreateOrder() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[4]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"ordersPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:customerID\"]")).sendKeys("55");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:staffID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:storeID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:Bestelldatum\"]")).sendKeys("26.08.2020");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[7]")).click();
    }

   @Test
    public void testSearchOrder() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[4]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"ordersPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderDate\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderDate\"]")).sendKeys("26.08.2020");
        Thread.sleep(600);
    }
   @Test
    public void testEditOrder() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderTable:0:j_idt72\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[3]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[3]")).sendKeys("28.08.2020");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[10]")).click();
    }
 @Test
    public void testDeleteOrder() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderDate\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderDate\"]")).sendKeys("28.08.2020");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderTable:0:j_idt73\"]")).click();
    }

    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
