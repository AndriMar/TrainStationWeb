package Tests;

import core.businessLogic;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import core.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andri
 * Date: 12/11/10
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */

public class createUserTest {
    private businessLogic BL_;
    private int sqlResult;

    @Before
    public void _if(){
        BL_ = new businessLogic();
    }

    @Test
    public void _when(){
        sqlResult = BL_.createUser("Andri Mar Björgvinsson","andri","andri","0408823919");
    }
    @After
    public void _then(){
        org.junit.Assert.assertNotSame(-1,sqlResult);
        if(sqlResult != -1){
            BL_.removeUser(sqlResult);
        }
    }
}
