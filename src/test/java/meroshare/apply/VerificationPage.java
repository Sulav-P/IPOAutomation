package meroshare.apply;

import mainClasses.DataWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerificationPage {
    WebDriver driver;
    DataWarehouse dataWarehouse;
    public VerificationPage(WebDriver driver) {
        this.driver = driver;
        dataWarehouse = new DataWarehouse();
    }
    public void verify(){
        try{
        driver.findElement(By.cssSelector("#transactionPIN")).click();
        driver.findElement(By.cssSelector("#transactionPIN")).sendKeys(dataWarehouse.getPin());
        driver.findElement(By.xpath("//div[2]/div[1]/div[1]/div[1]/button[1]")).click();
        Thread.sleep(2000);
        }catch (Exception e) {
            System.out.println("Problem in Verification Page.");
        }
    }
}
