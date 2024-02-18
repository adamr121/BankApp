package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoTransaction {
    private int tran_id;
    private int user_id;
    private float amount;
    private String date;

    public BoTransaction(int tran_id){
        ResultSet tran = MyJDBC.getTransaction(tran_id);
        try{
            tran.next();
            this.tran_id = tran.getInt(T_transactionId);
            this.user_id = tran.getInt(T_userId);
            this.amount = tran.getFloat(T_amount);
            this.date = tran.getString(T_date);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public float getAmount(){
        return amount;
    }
    public String getDate(){
        return date;
    }
    public int getUserId(){
        return user_id;
    }
    public int getTran_id() {
        return tran_id;
    }
    public static final String T_userId = "user_id";
    public static final String T_transactionId = "tran_id";
    public static final String T_amount = "kwota";
    public static final String T_date = "data_tran";
}
