package meroshare.apply;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShareListPage {
    WebDriver driver;
    ApplicationPage applicationPage;
    private final List<WebElement> OfferingsList;
    private final int clientNo;

    public ShareListPage(WebDriver driver, int clientNo) {
        this.driver = driver;
        this.clientNo = clientNo;
        applicationPage = new ApplicationPage(driver);
        OfferingsList = this.driver.findElements(By.xpath("//span[@tooltip='Share Group']"));
    }

    public void offers(){
        if(OfferingsList.size()>0){
            int count=0;
            System.out.println("Total "+OfferingsList.size()+" offers.");
            System.out.println("The offers are as follows:");
            // can modify here and show the offerings full details, name and type
            for(WebElement offer: OfferingsList){
                System.out.println("Offer "+count+": "+offer.getText());
            }
            // make user select which ipo or type of offering to apply
            chooseIPOOffering();
        }
        else{
           System.out.println("No Openings Currently");
           return;
        }
    }

    private void chooseIPOOffering(){
            for (WebElement offering : OfferingsList) {
                if (offering.getText().equals("Ordinary Shares")){
                    try {
                       if(driver.findElement(By.xpath("//i[text()='Apply']")).isDisplayed()){
                           driver.findElement(By.xpath("//i[text()='Apply']")).click();
                           System.out.println("Applying the IPO.");
                           Thread.sleep(2000);
                           ApplicationPage applicationPage = new ApplicationPage(driver);
                           applicationPage.apply(clientNo);
                       }
                       else if(driver.findElement(By.xpath("//i[text()='Edit']")).isDisplayed()){
                           System.out.println("Processing on Bank End !!!");
                       }
                       // if not button displayed
                       else{
                           System.out.println("IPO Applied Successfully.");
                       }
                    }catch (Exception e){
                        System.out.println("Problem in Apply Button Section.");
                    }
                }
            }
    }
}
