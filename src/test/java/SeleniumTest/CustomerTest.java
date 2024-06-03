package SeleniumTest;

import de.pdbm.starter.business.messages.entity.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerTest {
    public WebDriver webDriver;
    public Customer customer = new Customer("Wolfenbuttel", "123@gmail.com", "mask", "Elon", "(559) 628-2239", "HH", "12 exer", "38302");

    @BeforeClass
    void Setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/team-10");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:username']")).sendKeys("fabiola.jackson@bikes.shop");
        webDriver.findElement(By.xpath("//*[@id='j_idt6:password']")).sendKeys("555-5554");
        webDriver.findElement(By.xpath("//*[@id='j_idt6']/input[4]")).click();
    }

    @Test(priority = 1)
    public void testcreateCustomer() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[2]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"customersPanel\"]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:vorname\"]")).sendKeys(customer.getFirstname());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:nachname\"]")).sendKeys(customer.getLastname());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:strasse\"]")).sendKeys(customer.getStreet());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:phone\"]")).sendKeys(customer.getPhone());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:email\"]")).sendKeys(customer.getEmail());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:staat\"]")).sendKeys(customer.getState());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:stadt\"]")).sendKeys(customer.getCity());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53:plz\"]")).sendKeys(customer.getZipcode());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[10]")).click();
    }

    @Test(priority = 2)
    public void testSearchCustomer() throws InterruptedException {
        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"navForm\"]/div/div[2]/span"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"customersPanel\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).sendKeys(customer.getLastname());
        Thread.sleep(600);
    }
    @Test(priority = 3)
    public void testEditCustomer() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:customerTable:0:detail\"]")).click();
        customer.setLastname("Koorper");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[4]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[4]")).sendKeys(customer.getLastname());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt53\"]/input[11]")).click();
    }

    @Test(priority = 4)
    public void testDeleteCustomer() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:lastname\"]")).sendKeys(customer.getLastname());
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt52:customerTable:0:j_idt78\"]")).click();
    }

    @AfterTest
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
