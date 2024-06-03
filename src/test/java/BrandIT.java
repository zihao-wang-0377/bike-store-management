import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class BrandIT {
    public WebDriver webDriver;

    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
//        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("fabiola.jackson@bikes.shop");
//        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("555-5554");
//        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }
   @Test
    public void createBrandTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[6]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"brandsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:brandName\"]")).sendKeys("Hello World");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[3]")).click();
    }
    @Test
    public void searchBrandTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[6]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"brandsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:brandName\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:brandName\"]")).sendKeys("Hello World");
        Thread.sleep(600);
    }
   @Test
    public void deleteBrandTest() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:brandTable:0:j_idt63\"]")).click();
    }
    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
