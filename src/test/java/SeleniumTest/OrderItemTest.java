package SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OrderItemTest {

    public WebDriver webDriver;

    @BeforeClass
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("fabiola.jackson@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("555-5554");
        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }

    @Test(priority = 1)
    public void testCreateOrderItem() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:j_idt17\"]")).click();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:orderItemsPanel\"]/input[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:orderItemID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:orderID\"]")).sendKeys("999");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:Rabatte\"]")).sendKeys("0.1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:ListenPreis\"]")).sendKeys("999");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:quantity\"]")).sendKeys("10");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:ProduktID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[8]")).click();
//        gibt es Problem bei der speichern
    }

    @Test(priority = 2)
    public void testSearchOrder() {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt8:orderItemsPanel\"]/input[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderId\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderId\"]")).sendKeys("999");
    }
}

