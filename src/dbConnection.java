import java.lang.String;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andri
 * Date: 12/9/10
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class dbConnection {
    private static Connection con_;


    public dbConnection(String driver){
        try{
            Class.forName(driver);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.print("Class not found!");
            System.out.print(ex.getMessage());
        }
    }

    public void openConnection(String url,String user, String password){
        try{
            con_ = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex)
        {
            System.out.print("SQL error!!");
            System.out.print(ex.getMessage());
        }
    }

    public boolean executeUpdate(String queryString){
        try{
            Statement stm = con_.createStatement();
            int updateQuery = stm.executeUpdate(queryString);
            if(updateQuery == 0){
                return false;
            }
        }
        catch (SQLException ex)
        {
            System.out.print("Error executing the SQL statement!!");
            System.out.print(ex.getMessage());
            return false;
        }
        return true;
    }

    public ResultSet executeQuery(String queryString){
        ResultSet resultSet = null;
        try{
            Statement stm = con_.createStatement();
            resultSet = stm.executeQuery(queryString);
            return resultSet;
        }
        catch (SQLException ex)
        {
            System.out.print("Error executing the SQL statement!!");
            System.out.print(ex.getMessage());
        }
        return resultSet;
    }
}
