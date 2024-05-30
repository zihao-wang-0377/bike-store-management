package SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OrderTest {
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
    public void testCreateOrder() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:j_idt14\"]")).click();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:ordersPanel\"]/input[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:customerID\"]")).sendKeys("3");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:staffID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:storeID\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:Bestelldatum\"]")).sendKeys("28.05.2024");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:ErwartetesLieferdatum\"]")).sendKeys("29.05.2024");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[7]")).click();
    }

    @Test(priority = 2)
    public void testSearchOrder() {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt8:ordersPanel\"]/input[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderDate\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderDate\"]")).sendKeys("28.05.2024");
    }
    @Test(priority = 3)
    public void testEditOrder() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderTable:0:j_idt55\"]")).click();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[6]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[6]")).sendKeys("28.06.2024");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[10]")).click();
    }
    @Test(priority = 4)
    public void testDeleteOrder() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderDate\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderDate\"]")).sendKeys("28.05.2024");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:orderTable:0:j_idt56\"]")).click();
    }

    @AfterTest
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
