package db;

import constants.CommonConstants;
import java.sql.*;


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
                PreparedStatement insertUser = connection.prepareStatement("INSERT INTO " + CommonConstants.DB_USERS_TABLE_NAME +" (username, password) VALUES (?, ?)");
                insertUser.setString(1, username);
                insertUser.setString(2, password);
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
            PreparedStatement checkUserExists = connection.prepareStatement("SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME
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
    public static boolean validateLogin(String username, String password){
        try{
            if(checkUser(username)){
                PreparedStatement checkUserPassword = connection.prepareStatement("SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME + " WHERE username = ? AND password = ?");
                checkUserPassword.setString(1, username);
                checkUserPassword.setString(2, password);
                ResultSet result =  checkUserPassword.executeQuery();

                return result.isBeforeFirst();
            }
           return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getUser(int user_id){
        try{
            PreparedStatement getUserStmt = connection.prepareStatement("SELECT * FROM " +CommonConstants.DB_USERS_TABLE_NAME + " WHERE idusers =?");
            getUserStmt.setString(1, Integer.toString(user_id));
            return getUserStmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateUserAmount(int user_id, float amount){
        try{
            PreparedStatement updateBalance = connection.prepareStatement("UPDATE " + CommonConstants.DB_USERS_TABLE_NAME + " SET stan_konta = " + amount + " WHERE idusers =?");
            updateBalance.setString(1, Integer.toString(user_id));
            updateBalance.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
}
