package SeleniumTest;

import de.pdbm.starter.business.messages.entity.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:j_idt8\"]")).click();
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt5:customersPanel\"]/input[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:vorname\"]")).sendKeys(customer.getFirstname());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:nachname\"]")).sendKeys(customer.getLastname());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:strasse\"]")).sendKeys(customer.getStreet());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:phone\"]")).sendKeys(customer.getPhone());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:email\"]")).sendKeys(customer.getEmail());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:staat\"]")).sendKeys(customer.getState());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:stadt\"]")).sendKeys(customer.getCity());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36:plz\"]")).sendKeys(customer.getZipcode());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[10]")).click();
    }

    @Test(priority = 2)
    public void testSearchCustomer() {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt8:customersPanel\"]/input[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:lastname\"]")).sendKeys(customer.getLastname());
    }
    @Test(priority = 3)
    public void testEditCustomer() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:customerTable:0:detail\"]")).click();
        Thread.sleep(600);
        customer.setLastname("Koorper");
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[4]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[4]")).sendKeys(customer.getLastname());
        webDriver.findElement(By.xpath("//*[@id=\"j_idt36\"]/input[11]")).click();
    }

    @Test(priority = 4)
    public void testDeleteCustomer() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:lastname\"]")).clear();
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:lastname\"]")).sendKeys(customer.getLastname());
        Thread.sleep(600);
        webDriver.findElement(By.xpath("//*[@id=\"j_idt35:customerTable:0:j_idt61\"]")).click();
    }

    @AfterTest
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
