package SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrandTest {
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
    public void createBrandTest() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:j_idt20\"]")).click();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:BrandPanel\"]/input[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:brandName\"]")).sendKeys("Hello World");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[3]")).click();
    }
    @Test(priority = 2)
    public void searchBrandTest(){
        webDriver.findElement(By.xpath("//*[@id=\"j_idt8:BrandPanel\"]/input[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:brandName\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:brandName\"]")).sendKeys("Hello World");
    }
    @Test(priority = 3)
    public void deleteBrandTest() throws InterruptedException {
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:brandTable:0:j_idt46\"]")).click();
    }
    @AfterTest
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
