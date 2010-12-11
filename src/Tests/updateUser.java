package Tests;

import core.businessLogic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Andri
 * Date: 12/11/10
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class updateUser {
    private businessLogic BL_;
    private int user;
    private int sqlResult;

    @Before
    public void _if(){
        BL_ = new businessLogic();
        user = BL_.createUser("Andri Mar Björgvinsson","andri","andri","0408823919");
        org.junit.Assert.assertNotSame(-1,user);
    }

    @Test
    public void _when(){
        sqlResult = BL_.updateUser(user,"Andri Mar Björgvinsson","andri123","andri","0408823919");
    }
    @After
    public void _then(){
        org.junit.Assert.assertNotSame(-1,sqlResult);
        ResultSet rs = BL_.getUser("andri123");
        int rowCount = 0;
        try{
            rs.last();
            rowCount = rs.getRow();

        }catch (SQLException ex){
            System.out.println("Error getting user count!");
            System.out.println(ex.getMessage());

        }
        org.junit.Assert.assertNotSame(0,rowCount);
        if(user != -1){
            BL_.removeUser(user);
        }
        if(sqlResult != -1){
            BL_.removeCard(sqlResult);
        }

    }
}
