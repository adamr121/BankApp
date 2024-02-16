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
            this.tran_id = tran.getInt("tran_id");
            this.user_id = tran.getInt("user_id");
            this.amount = tran.getFloat("kwota");
            this.date = tran.getString("data_tran");
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
}
