package SeleniumTest;
import de.pdbm.starter.business.messages.entity.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ProductTest {

    public WebDriver webDriver;
    public Product product = new Product(new BigDecimal(999.99), 2024, "autobike", null, null );


    @BeforeClass
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("fabiola.jackson@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("555-5554");
        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }

    @Test(priority = 1)
    public void testCreateProduct() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:j_idt11\"]")).click();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:productesPanel\"]/input[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:name\"]")).sendKeys(product.getName());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:price\"]")).sendKeys(String.valueOf(product.getPrice()));
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:description\"]")).sendKeys(String.valueOf(product.getModelYear()));
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[4]")).click();
    }

    @Test(priority = 2)
    public void testSearchProduct() {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt8:productesPanel\"]/input[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:name\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:name\"]")).sendKeys(product.getName());
    }

    @Test(priority = 3)
    public void testEditProduct() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:productTable:0:detail\"]")).click();
        Thread.sleep(600);
        product.setName("BIG Bike");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[5]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[5]")).sendKeys(product.getName());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[6]")).click();
    }

    @Test(priority = 4)
    public void testDeleteProduct() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:name\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:name\"]")).sendKeys(product.getName());
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:productTable:0:j_idt49\"]")).click();
    }

    @AfterTest
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }


}
