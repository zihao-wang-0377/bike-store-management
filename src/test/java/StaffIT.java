import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StaffIT {

    public WebDriver webDriver;

    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("admin.staff@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("444-3049");
        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }
    @Test
    @Order(1)
    public void createStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:vorname\"]")).sendKeys("mask");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:nachname\"]")).sendKeys("Elon");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:email\"]")).sendKeys("123@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:activ\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:phone\"]")).sendKeys("48-1234");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[7]")).click();
    }
    @Test
    @Order(2)
    public void SearchStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).sendKeys("Elon");
    }
    @Test
    @Order(3)
    public void EditStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).sendKeys("Elon");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:staffTable:0:j_idt63\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[3]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[3]")).sendKeys("ma");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[7]")).click();
    }
    @Test
    @Order(4)
    public void DeleteStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).sendKeys("ma");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:staffTable:0:j_idt64\"]")).click();
    }
    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
