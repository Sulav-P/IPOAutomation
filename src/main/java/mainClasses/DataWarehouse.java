package mainClasses;

public class DataWarehouse {

    private String[] depositoryParticipantArr = {
           // save depository participant details
    };
    private String[] clientUserNameArr = {
            // save client username
    };
    private String[] clientPasswordArr = {
            // save client password
    };
    private String[] clientBankArr = {
           // save client bank detail
    };

    private String[] clientCrnArr = {
            // save client crn
    };

    private String pin = "";// save pin
    private String loginLink = "https://meroshare.cdsc.com.np/#/login";
    private String dashboardLink = "https://meroshare.cdsc.com.np/#/dashboard";



    public String[] getDepositoryParticipantArr() {
        return depositoryParticipantArr;
    }

    public String[] getClientUserNameArr() {
        return clientUserNameArr;
    }

    public String[] getClientPasswordArr() {
        return clientPasswordArr;
    }

    public void setClientPasswordArr(int client,String newPassword){
        clientPasswordArr[client] = newPassword;
    }

    public String[] getClientCrnArr() {
        return clientCrnArr;
    }

    public String[] getClientBankArr() {
        return clientBankArr;
    }

    public String getPin() {
        return pin;
    }

    public String getLoginLink(){
        return loginLink;
    }

    public String getDashboardLink(){
        return dashboardLink;
    }

}