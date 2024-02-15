package db;

import constants.Constants;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyJDBC {
    public static Connection connection = null;
    public static void setConnection(Connection connection) {
        MyJDBC.connection = connection;
    }
    // true - register success
    // false - register failure
    public static boolean register(String username, String password){
        try{
            if(!checkUser(username)){
                PreparedStatement insertUser = connection.prepareStatement("INSERT INTO " + Constants.DB_USERS_TABLE +" (username, password, stan_konta) VALUES (?, ?, ?)");
                insertUser.setString(1, username);
                insertUser.setString(2, password);
                insertUser.setString(3, "0");
                insertUser.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Checks if user is already in the database
    public static boolean checkUser(String username){
        try{
            PreparedStatement checkUserExists = connection.prepareStatement("SELECT * FROM " + Constants.DB_USERS_TABLE
            + " WHERE username = ?");
            // Replacing ? with the username
            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getErrorCode());
        }
        return true;
    }
    public static BoUser validateLogin(String username, String password){
        try{
            if(checkUser(username)){
                PreparedStatement checkUserPassword = connection.prepareStatement("SELECT * FROM " + Constants.DB_USERS_TABLE + " WHERE username = ? AND password = ?");
                checkUserPassword.setString(1, username);
                checkUserPassword.setString(2, password);
                ResultSet result =  checkUserPassword.executeQuery();
                if(result.isBeforeFirst()){
                    result.next();
                    return new BoUser(result.getInt("idusers"));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getUser(int user_id){
        try{
            PreparedStatement getUserStmt = connection.prepareStatement("SELECT * FROM " + Constants.DB_USERS_TABLE + " WHERE idusers =?");
            getUserStmt.setString(1, Integer.toString(user_id));
            return getUserStmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateUserAmount(int user_id, float amount){
        try{
            PreparedStatement updateBalance = connection.prepareStatement("UPDATE " + Constants.DB_USERS_TABLE + " SET stan_konta = " + amount + " WHERE idusers =?");
            updateBalance.setString(1, Integer.toString(user_id));
            updateBalance.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
    public static void recordTransaction(int user_id, float amount){
        try{
            PreparedStatement recordTransaction = connection.prepareStatement("INSERT INTO " + Constants.DB_TRANSACTION_HISTORY_TABLE + "(user_id, kwota, data_tran)" + " VALUES (?, ?, ?)");
            recordTransaction.setString(1, Integer.toString(user_id));
            recordTransaction.setString(2, Float.toString(amount));
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            recordTransaction.setString(3, timeStamp);

            recordTransaction.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
}
