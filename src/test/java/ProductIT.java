import de.pdbm.starter.business.messages.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.math.BigDecimal;

public class ProductIT {

    public WebDriver webDriver;
    public Product product = new Product(new BigDecimal(999.99), 2024, "autobike", null, null );


    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
//        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("fabiola.jackson@bikes.shop");
//        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("555-5554");
//        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }

    @Test
    public void testCreateProduct() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[3]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"productsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:name\"]")).sendKeys(product.getName());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:price\"]")).sendKeys(String.valueOf(product.getPrice()));
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:description\"]")).sendKeys(String.valueOf(product.getModelYear()));
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[4]")).click();
    }

    @Test
    public void testSearchProduct() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[3]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"productsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:name\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:name\"]")).sendKeys(product.getName());
        Thread.sleep(600);
    }

   @Test
    public void testEditProduct() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:productTable:0:detail\"]")).click();
        product.setName("BIG Bike");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[5]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[5]")).sendKeys(product.getName());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[6]")).click();
    }

   @Test
    public void testDeleteProduct() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:name\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:name\"]")).sendKeys(product.getName());
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:productTable:0:j_idt66\"]")).click();
    }

    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }


}
