package core;

import java.sql.ResultSet;


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
    public int createUser(String fullName,String username,String password,String kt){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO User (fullname,username,password,kt)");
        sqlQuery.append("values (");
        sqlQuery.append("'");sqlQuery.append(fullName);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(username);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(password);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(kt);sqlQuery.append("'");
        sqlQuery.append(");");
        return db_.executeUpdate(sqlQuery.toString());
    }

    public int updateUser(int userID,String fullName,String username,String password,String kt){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("Update User SET");
        sqlQuery.append(" fullname='");sqlQuery.append(fullName);sqlQuery.append("'");
        sqlQuery.append(" username='");sqlQuery.append(username);sqlQuery.append("'");
        sqlQuery.append(" password='");sqlQuery.append(password);sqlQuery.append("'");
        sqlQuery.append(" kt='");sqlQuery.append(kt);sqlQuery.append("'");
        sqlQuery.append(" where id=");sqlQuery.append(userID);
        sqlQuery.append(";");

        return db_.executeUpdate(sqlQuery.toString());
    }

    public int removeUser(int userID){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("DELETE FROM User where id=");
        sqlQuery.append(userID);
        sqlQuery.append(";");

        return db_.executeUpdate(sqlQuery.toString());
    }

    public ResultSet getUser(String username){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT * FROM User where username='");
        sqlQuery.append(username);
        sqlQuery.append("';");
        return  db_.executeQuery(sqlQuery.toString());
    }

    public int addCardToUser(int userId,int balance){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO Card (balance)");
        sqlQuery.append("values (");
        sqlQuery.append(balance);
        sqlQuery.append(");");
        int cardId = db_.executeUpdate(sqlQuery.toString());
        sqlQuery.setLength(0);
        sqlQuery.append("INSERT INTO User_has_cards (User_id,Card_id)");
        sqlQuery.append("values (");
        sqlQuery.append(userId);
        sqlQuery.append(",");
        sqlQuery.append(cardId);
        sqlQuery.append(");");
        return cardId;
    }

    public ResultSet getUserCards(int userID){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("select distinct c.* from Card as c,User as u, User_has_cards con  where u.id=");
        sqlQuery.append(userID);
        sqlQuery.append(" and u.id=con.User_id and con.Card_id=c.id;");
        return  db_.executeQuery(sqlQuery.toString());
    }

    public  ResultSet getCard(int cardID){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT * FROM Card where id='");
        sqlQuery.append(cardID);
        sqlQuery.append("';");
        return  db_.executeQuery(sqlQuery.toString());
    }

    public int removeCard(int cardID){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("DELETE FROM Card where id=");
        sqlQuery.append(cardID);
        sqlQuery.append(";");
        return db_.executeUpdate(sqlQuery.toString());
    }

    public int addTrip(String depart,String arival,String cost,int Card_id){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO trips (depart,arival,cost,Card_id)");
        sqlQuery.append("values (");
        sqlQuery.append("'");sqlQuery.append(depart);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(arival);sqlQuery.append("',");
        sqlQuery.append("'");sqlQuery.append(cost);sqlQuery.append("',");
        sqlQuery.append(Card_id);
        sqlQuery.append(");");
        return db_.executeUpdate(sqlQuery.toString());
    }

    public int removeTrip(int tripID){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("DELETE FROM Trip where id=");
        sqlQuery.append(tripID);
        sqlQuery.append(";");
        return db_.executeUpdate(sqlQuery.toString());
    }

    public  ResultSet getTripForCard(int cardID){
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT * FROM Trip where Card_id='");
        sqlQuery.append(cardID);
        sqlQuery.append("';");
        return  db_.executeQuery(sqlQuery.toString());
    }


}
