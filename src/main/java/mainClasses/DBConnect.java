package mainClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnect {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String url,name,pass = null;

    public static void main(String[] args) {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            url="jdbc:mysql://localhost:3306";
            name="root";
            pass="root";
            conn = (Connection) DriverManager.getConnection(url,name,pass);
            System.out.println("Connected :: "+ conn);
        } catch (Exception ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
        }
        DataWarehouse dataWarehouse = new DataWarehouse();
        int i =1;
        int id ,pin = 0;
        String dp,username,password,bank,crn = null;
        try {
            stmt = (Statement) conn.createStatement();
            while ( i <= (dataWarehouse.getClientUserNameArr().length)){
                id = i;
                dp = dataWarehouse.getDepositoryParticipantArr()[i-1];
                username = dataWarehouse.getClientUserNameArr()[i-1];
                password = dataWarehouse.getClientPasswordArr()[i-1];
                bank = dataWarehouse.getClientBankArr()[i-1];
                crn = dataWarehouse.getClientCrnArr()[i-1];
                pin = Integer.parseInt(dataWarehouse.getPin());
                String query = "INSERT INTO sys.clientsinfo " +
                        "VALUES (" +id+ ",'" +dp+ "','" +username+ "','" +password+ "','" +bank+ "','" +crn+ "'," +pin+ ")";
                System.out.println(query);
                stmt.executeUpdate(query);
                i++;
            }
        }catch (Exception e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
