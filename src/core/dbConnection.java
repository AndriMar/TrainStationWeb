package core;

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
    /*
     * Opens the conection and makes it ready to use.
     */
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
    /*
     * Executes insert,update and delete queries. Every thing thad dose not return rows.
     */
    public int executeUpdate(String queryString){
        try{
            Statement stm = con_.createStatement();
            int updateQuery = stm.executeUpdate(queryString);
            if(updateQuery == 0){
                return -1;
            }
            int autoIncKeyFromFunc = -1;
            ResultSet rs = stm.executeQuery("SELECT LAST_INSERT_ID();");

            if (rs.next()) {
                autoIncKeyFromFunc = rs.getInt(1);
            } else {
                throw new SQLException("Now ID got back from database");
            }

            rs.close();
            return autoIncKeyFromFunc;
        }
        catch (SQLException ex)
        {
            System.out.print("Error executing the SQL statement!!");
            System.out.print(ex.getMessage());
            return -1;
        }
    }
    /*
     * Executes queries that returns rows, for example select.
     */
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
