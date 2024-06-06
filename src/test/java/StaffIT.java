import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
@Tag("staff")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StaffIT {

    public WebDriver webDriver;

    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:username\"]")).sendKeys("admin.staff@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:password\"]")).sendKeys("444-3049");
        webDriver.findElement(By.xpath("//*[@id=\"loginform\"]/input[4]")).click();
    }
    @Test
    @Order(1)
    public void createStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"staffForm:vorname\"]")).sendKeys("mask");
        webDriver.findElement(By.xpath("//*[@id=\"staffForm:nachname\"]")).sendKeys("Elon");
        webDriver.findElement(By.xpath("//*[@id=\"staffForm:email\"]")).sendKeys("123@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"staffForm:activ\"]")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"staffForm:phone\"]")).sendKeys("48-1234");
        webDriver.findElement(By.xpath("//*[@id=\"staffForm\"]/input[7]")).click();
        Thread.sleep(600);
        String expect = "Mitarbeiter erfolgreich gespeichert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"staffForm:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }
    @Test
    @Order(2)
    public void SearchStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"staff:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"staff:lastname\"]")).sendKeys("Elon");
    }
    @Test
    @Order(3)
    public void EditStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"staff:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"staff:lastname\"]")).sendKeys("Elon");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staff:staffTable:0:detail\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"staffdetail:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"staffdetail:lastname\"]")).sendKeys("ma");
        webDriver.findElement(By.xpath("//*[@id=\"staffdetail\"]/input[7]")).click();
        Thread.sleep(600);
        String expect = "Mitarbeiter erfolgreich aktualisiert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"staffdetail:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }
    @Test
    @Order(4)
    public void DeleteStaffTest() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[8]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staffsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"staff:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"staff:lastname\"]")).sendKeys("ma");
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"staff:staffTable:0:delete\"]")).click();
        Thread.sleep(600);
        String expect = "Mitarbeiter erfolgreich gelöscht";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"messageForm:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }
    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
