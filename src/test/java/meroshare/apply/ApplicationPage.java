package meroshare.apply;

import mainClasses.DataWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ApplicationPage {
    WebDriver driver;
    DataWarehouse dataWarehouse;
    VerificationPage verificationPage;
    private final String[] clientBankArr;
    private final String[] clientCrnArr;

    public ApplicationPage(WebDriver driver){
        this.driver = driver;
        dataWarehouse =  new DataWarehouse();
        clientBankArr = dataWarehouse.getClientBankArr();
        clientCrnArr = dataWarehouse.getClientCrnArr();
        verificationPage = new VerificationPage(driver);
    }

    public void apply(int client){
        try{
            Select myBank = new Select(driver.findElement(By.id("selectBank")));
            myBank.selectByVisibleText(clientBankArr[client]);
            driver.findElement(By.id("appliedKitta")).click();
            driver.findElement(By.id("appliedKitta")).sendKeys("10");
            driver.findElement(By.id("crnNumber")).click();
            driver.findElement(By.id("crnNumber")).sendKeys(clientCrnArr[client]);
            driver.findElement(By.id("disclaimer")).click();
            driver.findElement(By.cssSelector(".card-footer > .btn-primary > span")).click();
            Thread.sleep(2000);
            verificationPage.verify();

        }catch (Exception e){
            System.out.println("Problem in Application Page.");
        }
    }


}
