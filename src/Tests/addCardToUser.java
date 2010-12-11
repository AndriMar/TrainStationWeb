package Tests;

import core.businessLogic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import core.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andri
 * Date: 12/11/10
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class addCardToUser {
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
        sqlResult = BL_.addCardToUser(user,0);
    }
    @After
    public void _then(){
        org.junit.Assert.assertNotSame(-1,sqlResult);
        if(user != -1){
            BL_.removeUser(user);
        }
        if(sqlResult != -1){
            BL_.removeCard(sqlResult);
        }

    }
}
