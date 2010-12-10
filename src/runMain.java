/**
 * Created by IntelliJ IDEA.
 * User: Andri
 * Date: 12/9/10
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */

public class runMain {

    public static void main(String[] args) {
        dbConnection db = new dbConnection("com.mysql.jdbc.Driver");
        db.openConnection("jdbc:mysql://localhost:3306/train","train","train");
        db.executeQuery("select * from users;");

    }
}
