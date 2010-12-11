/**
 * Created by IntelliJ IDEA.
 * User: Andri
 * Date: 12/11/10
 * Time: 12:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class businessLogic {
    private dbConnection db_;

    public  businessLogic(){
        db_ = new dbConnection("com.mysql.jdbc.Driver");
        db_.openConnection("jdbc:mysql://localhost:3306/train","train","train");
    }
    public void createUser(String fullName,String username,String password,String kt){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO User (fullname,username,password,kt)");
        sqlQuery.append("values (");
        sqlQuery.append("'");sqlQuery.append(fullName);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(username);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(password);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(kt);sqlQuery.append("'");
        sqlQuery.append(");");
        db_.executeUpdate(sqlQuery.toString());
    }

    public void addCardToUser(int userId,int balance){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO Card (balance)");
        sqlQuery.append("values (");
        sqlQuery.append(balance);
        sqlQuery.append(");");
        int cardId = db_.executeUpdate(sqlQuery.toString());
        sqlQuery.setLength(0);
        sqlQuery.append("INSERT INTO Card (User_id,Card_id)");
        sqlQuery.append("values (");
        sqlQuery.append(userId);
        sqlQuery.append(",");
        sqlQuery.append(cardId);
        sqlQuery.append(");");
        db_.executeUpdate(sqlQuery.toString());
    }

    public void addTrip(String depart,String arival,String cost,int Card_id){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO User (depart,arival,cost,Card_id)");
        sqlQuery.append("values (");
        sqlQuery.append("'");sqlQuery.append(depart);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(arival);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(cost);sqlQuery.append("',");
        sqlQuery.append(Card_id);
        sqlQuery.append(");");
        db_.executeUpdate(sqlQuery.toString());

    }
}
