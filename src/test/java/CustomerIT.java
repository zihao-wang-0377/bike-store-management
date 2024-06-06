import de.pdbm.starter.business.messages.entity.Customer;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
@Tag("customer")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerIT {
    public WebDriver webDriver;
    public Customer customer = new Customer("Wolfenbuttel", "123@gmail.com", "mask", "Elon", "(559) 628-2239", "HH", "12 exer", "38302");

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
    public void testcreateCustomer() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[2]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"customersPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:vorname\"]")).sendKeys(customer.getFirstname());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:nachname\"]")).sendKeys(customer.getLastname());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:strasse\"]")).sendKeys(customer.getStreet());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:phone\"]")).sendKeys(customer.getPhone());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:email\"]")).sendKeys(customer.getEmail());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:staat\"]")).sendKeys(customer.getState());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:stadt\"]")).sendKeys(customer.getCity());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm:plz\"]")).sendKeys(customer.getZipcode());
        webDriver.findElement(By.xpath("//*[@id=\"customerForm\"]/input[10]")).click();
        Thread.sleep(600);
        String expect = "Kunde erfolgreich gespeichert";
        String actual = webDriver.findElement(By.xpath("//*[@id=\"customerForm:messages\"]")).getText();
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @Order(2)
    public void testSearchCustomer() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[2]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"customersPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"customer:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"customer:lastname\"]")).sendKeys(customer.getLastname());
        Thread.sleep(600);
    }
   @Test
   @Order(3)
    public void testEditCustomer() throws InterruptedException {
       WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[2]/span"));
       Actions actions = new Actions(webDriver);
       actions.moveToElement(hoverElement).perform();
       Thread.sleep(600);
       webDriver.findElement(By.xpath("//*[@id=\"customersPanel\"]/a[1]")).click();
       webDriver.findElement(By.xpath("//*[@id=\"customer:lastname\"]")).clear();
       webDriver.findElement(By.xpath("//*[@id=\"customer:lastname\"]")).sendKeys(customer.getLastname());
       Thread.sleep(600);
       webDriver.findElement(By.xpath("//*[@id=\"customer:customerTable:0:detail\"]")).click();
       customer.setLastname("Koorper");
       webDriver.findElement(By.xpath("//*[@id=\"customerDetail\"]/input[4]")).clear();
       webDriver.findElement(By.xpath("//*[@id=\"customerDetail\"]/input[4]")).sendKeys(customer.getLastname());
       webDriver.findElement(By.xpath("//*[@id=\"customerDetail\"]/input[11]")).click();
       Thread.sleep(600);
       String expect = "Kunde erfolgreich aktualisiert";
       String actual = webDriver.findElement(By.xpath("//*[@id=\"customerDetail:messages\"]")).getText();
       Assertions.assertEquals(expect, actual);
    }

   @Test
   @Order(4)
    public void testDeleteCustomer() throws InterruptedException {
       WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[2]/span"));
       Actions actions = new Actions(webDriver);
       actions.moveToElement(hoverElement).perform();
       Thread.sleep(600);
       webDriver.findElement(By.xpath("//*[@id=\"customersPanel\"]/a[1]")).click();
       webDriver.findElement(By.xpath("//*[@id=\"customer:lastname\"]")).clear();
       webDriver.findElement(By.xpath("//*[@id=\"customer:lastname\"]")).sendKeys(customer.getLastname());
       Thread.sleep(600);
       webDriver.findElement(By.xpath("//*[@id=\"customer:customerTable:0:delete\"]")).click();
       Thread.sleep(600);
       String expect = "Kunde erfolgreich gelöscht";
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
