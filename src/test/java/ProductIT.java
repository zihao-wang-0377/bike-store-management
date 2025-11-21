import de.pdbm.starter.business.messages.entity.Product;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.math.BigDecimal;
import java.time.Duration;

@Tag("product")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIT {

    public WebDriver webDriver;
    public Product product = new Product(new BigDecimal(999.99), 2024, "autobike", null, null );


    @BeforeEach
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:username\"]")).sendKeys("admin.staff@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id=\"loginform:password\"]")).sendKeys("444-3049");
        webDriver.findElement(By.xpath("//*[@id=\"loginform\"]/input[4]")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navForm")));
    }

    @Test
    @Order(1)
    public void testCreateProduct() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[3]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"productsPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"productForm:name\"]")).sendKeys(product.getName());
        webDriver.findElement(By.xpath("//*[@id=\"productForm:price\"]")).sendKeys(String.valueOf(product.getPrice()));
        webDriver.findElement(By.xpath("//*[@id=\"productForm:description\"]")).sendKeys(String.valueOf(product.getModelYear()));
        webDriver.findElement(By.xpath("//*[@id=\"productForm\"]/input[4]")).click();
        Thread.sleep(600);
        String expect = "Produkt erfolgreich gespeichert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"productForm:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @Order(2)
    public void testSearchProduct() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[3]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"productsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"product:name\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"product:name\"]")).sendKeys(product.getName());
        Thread.sleep(600);
    }

    @Test
    @Order(3)
    public void testEditProduct() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[3]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"productsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"product:name\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"product:name\"]")).sendKeys(product.getName());
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"product:productTable:0:detail\"]")).click();
        product.setName("BIG Bike");
        webDriver.findElement(By.xpath("//*[@id=\"productDetail\"]/input[5]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"productDetail\"]/input[5]")).sendKeys(product.getName());
        webDriver.findElement(By.xpath("//*[@id=\"productDetail\"]/input[6]")).click();
        Thread.sleep(600);
        String expect = "Produkt erfolgreich aktualisiert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"productDetail:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @Order(4)
    public void testDeleteProduct() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[3]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"productsPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"product:name\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"product:name\"]")).sendKeys(product.getName());
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"product:productTable:0:delete\"]")).click();
        Thread.sleep(600);
        String expect = "Produkt erfolgreich gelöscht";
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
