package meroshare;

import mainClasses.DataWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


import java.time.Duration;

public class LoginPage {
    private boolean loginStatus;
    private final String meroshareLogin;
    private final String meroshareDashboard;
    private final String[] depositoryParticipantArr;
    private final String[] clientUserNameArr;
    private final String[] clientPasswordArr;
    WebDriver driver;
    DataWarehouse dataWarehouse;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        dataWarehouse = new DataWarehouse();
        depositoryParticipantArr = dataWarehouse.getDepositoryParticipantArr();
        clientUserNameArr = dataWarehouse.getClientUserNameArr();
        clientPasswordArr = dataWarehouse.getClientPasswordArr();
        meroshareLogin = dataWarehouse.getLoginLink();
        meroshareDashboard = dataWarehouse.getDashboardLink();
        loginStatus = false;
    }

    public void login(int client) {
        int refreshCount = 0;
        // if login success exit out of loop and method
        while (! loginStatus){
            try {
                UIInput(client);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Not able to access login Link");
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            loginStatus = isLoginSuccess(client);
            refreshCount++;
            // refresh 5 times and still not able to login exit from method
            if(refreshCount >4){
                break;
            }
        }
    }

    private void UIInput(int client) throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Select your DP']")).click();
        driver.findElement(By.xpath("//span[1]/input[1]")).sendKeys(depositoryParticipantArr[client]);
        // mimic keyboard
        driver.findElement(By.xpath("//span[1]/input[1]")).sendKeys(Keys.ENTER);
        //username and password
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys(clientUserNameArr[client]);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(clientPasswordArr[client]);
        driver.findElement(By.cssSelector(".btn")).click();
        Thread.sleep(2000);
    }

    private boolean isLoginSuccess(int client){
        try {
            if(driver.getCurrentUrl().equals(meroshareLogin)){
                    driver.navigate().refresh();
                    System.out.println("Credentials Error...Refreshing Login Page.");
                    return false;
            }
            else if (driver.getCurrentUrl().equals(meroshareDashboard)) {
                System.out.println("Login Successful and in the dashboard.");
                return true;
            }

            else if(driver.getCurrentUrl().equals(meroshareLogin) && // change this link to password reset link
                    driver.findElement(By.cssSelector(".change-password-title")).isDisplayed() ){
                    System.out.println("In the password recovery page.");
                    PasswordChangePage passwordChangePage = new PasswordChangePage(driver);
                    passwordChangePage.changePassword(client);
                    boolean isPassChanged = passwordChangePage.passwordResetStatus();
                    if (isPassChanged) {
                        driver.navigate().to(meroshareLogin);
                        return false;
                    }
            }
        } catch (Exception e) {
            System.out.println("Login Error");
        }
        return false;
    }

    public boolean loginStatus(){
        return loginStatus;
    }

}
