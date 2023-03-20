package meroshare;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class NavigationPage {
    WebDriver driver;

    private boolean asbaNavStatus = false;
    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }
    public void navigateToASBA(){
        try {
            driver.findElement(By.xpath("//span[text()='My ASBA']")).click();
            Thread.sleep(1000);
            Assert.assertEquals("Apply for Issue",driver.findElement(By.xpath("//div/ul/li[1]/a/span")).getText());
            System.out.println("'ASBA' module successfully selected.");
            asbaNavStatus = true;
        }catch (Exception e){
            System.out.println("Problem in ASBA \n Took too long to load");
        }
    }
    public boolean getAsbaNavStatus() {
        return asbaNavStatus;
    }

    // Similaryly add different navigations menus
}
