package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoUser {
    private final int id;
    private final String username;
    private float amount;

    public BoUser (int p_id) {
        ResultSet user = MyJDBC.getUser(p_id);
        try {
            user.next();
            id = user.getInt("idusers");
            username = user.getString("username");
            amount = user.getFloat("stan_konta");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float new_amount){
        amount = new_amount;
        MyJDBC.updateUserAmount(id, amount);
    }
}
