package SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:orderItemID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:orderID\"]")).sendKeys("999");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:Rabatte\"]")).sendKeys("0.1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:ListenPreis\"]")).sendKeys("999");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:quantity\"]")).sendKeys("10");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:ProduktID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[8]")).click();
//        gibt es Problem bei der speichern
    }

    @Test(priority = 2)
    public void testSearchOrder() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[5]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"orderPositionsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderId\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:orderId\"]")).sendKeys("999");
    }
}

