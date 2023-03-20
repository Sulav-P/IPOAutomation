package meroshare;

import mainClasses.DataWarehouse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChangePage {
    private String meroshareLink;
    private boolean pwdResetStatus;
    DataWarehouse dataWarehouse;
    Scanner sc;
    WebDriver driver;
    private String newPassword;

    public PasswordChangePage(WebDriver driver){
        this.driver = driver;
        dataWarehouse = new DataWarehouse();
        sc = new Scanner(System.in);
        meroshareLink = dataWarehouse.getLoginLink();
        pwdResetStatus = false;
    }


    public void changePassword(int client){
        boolean validPassword = false;
        // ask for new password from client and check if the password is of valid format
        while (! validPassword){
            passwordInput();
            validPassword = passwordValidation(newPassword);
        }
        try{
            String verificationText = driver.findElement(By.cssSelector(".change-password-title")).getText().trim();
            Assert.assertEquals("Change Password",verificationText);
            driver.findElement(By.id("oldPassword")).click();
            driver.findElement(By.id("oldPassword")).sendKeys(dataWarehouse.getClientPasswordArr()[client]);
            driver.findElement(By.id("newPassword")).click();
            driver.findElement(By.id("newPassword")).sendKeys(newPassword);
            driver.findElement(By.id("confirmPassword")).click();
            driver.findElement(By.id("confirmPassword")).sendKeys(newPassword);
            System.out.println("New Password: "+ newPassword);
            driver.findElement(By.cssSelector("div.form-group.form-actions > button")).click();
            Thread.sleep(5000);
            Assert.assertEquals(meroshareLink,driver.getCurrentUrl());
            pwdResetStatus = true;
            dataWarehouse.setClientPasswordArr(client,newPassword);

        }catch (Exception e){
            System.out.println("Error in asserting password change element");
        }
    }

    private void passwordInput(){
        System.out.print("Type new Password:\t");
        newPassword = sc.nextLine();
    }

    private boolean passwordValidation(String pwd){
        int lowerCaseNo = 0;
        boolean isPasswordOk = true;
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(pwd);
        boolean found = matcher.find();
        if(found){
            System.out.println("Enter password without spaces in it!!!");
            isPasswordOk = false;
        }
        else if(! (pwd.length()>=4 && pwd.length()<=15)){
            System.out.println("Password should be of at least 4 characters and at max of 15 characters!!!");
            isPasswordOk = false;
        }
        else{
            for(int i=0; i< pwd.length(); i++){
                if (Character.isLowerCase(pwd.charAt(i))) lowerCaseNo++;
            }
            if(lowerCaseNo < 3){
                System.out.println("Password should contain at least 3 lowercase letters!!!");
                isPasswordOk = false;
            }
        }
        return isPasswordOk;
    }

    public boolean passwordResetStatus(){
        return pwdResetStatus;
    }

}
