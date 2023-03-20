package meroshare;

import mainClasses.DataWarehouse;
import meroshare.apply.ShareListPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class MainIPOApplyTest {
    WebDriver driver;
    LoginPage loginPage;
    NavigationPage navigationPage;
    ShareListPage shareListPage;
    DataWarehouse dataWarehouse;
    String meroshareLink;
    private static int clientNo = 0;


    @BeforeEach
    public void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        dataWarehouse = new DataWarehouse();
        meroshareLink = dataWarehouse.getLoginLink();
        driver.get(meroshareLink);
        driver.manage().window().maximize();
    }

    // for n number of user
    //@RepeatedTest(6)
    @Test
    public void fillCredentials(){
        System.out.println("Applying for client : "+ dataWarehouse.getClientUserNameArr()[clientNo]);
        // Logging in
        loginPage = new LoginPage(driver);
        loginPage.login(clientNo);
        if(loginPage.loginStatus()){
            // Login successful
            // Navigating to ASBA
            navigationPage = new NavigationPage(driver);
            navigationPage.navigateToASBA();
            if(navigationPage.getAsbaNavStatus()){
                // Navigation Success
                // Check if ipo is open
                shareListPage = new ShareListPage(driver,clientNo);
                shareListPage.offers();
            }
        }
    }

    @AfterEach
    public void closeBrowser(){
        // for different clients
        clientNo++;
        driver.close();
    }
}
