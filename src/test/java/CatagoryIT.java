import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
@Tag("category")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CatagoryIT {

    public WebDriver webDriver;

    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id='loginform:username']")).sendKeys("admin.staff@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id='loginform:password']")).sendKeys("444-3049");
        webDriver.findElement(By.xpath("//*[@id='loginform']/input[4]")).click();    }
    @Test
    @Order(1)
    public void CreateCatagoryTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[7]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"categoriesPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:categoryName\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[3]")).click();
    }
    @Test
    @Order(2)
    public void SearchCatagoryTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[7]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"categoriesPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:categoryName\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:categoryName\"]")).sendKeys("1");
    }
    @Test
    @Order(3)
    public void DeleteCatagoryTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[7]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"categoriesPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:categoryName\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:categoryName\"]")).sendKeys("1");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:categoryTable:0:j_idt63\"]")).click();
    }
    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
