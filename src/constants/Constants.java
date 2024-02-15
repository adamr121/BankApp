package constants;

import java.awt.*;

public class Constants {
    public static final Color PRIMARY_COLOR = Color.decode("#353535");
    public static final Color SECONDARY_COLOR = Color.decode("#3c6e71");
    public static final Color TEXT_COLOR = Color.decode("#ffffff");

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/login_schema";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "dupa123";
    public static final String DB_USERS_TABLE = "USERS";
    public static final String DB_TRANSACTION_HISTORY_TABLE = "TRANSACTIONS";
    public static final String DB_TRANSACTION_HISTORY_USER_ID = "user_id";
    public static final String DB_TRANSACTION_HISTORY_AMOUNT = "kwota";
    public static final String DB_TRANSACTION_HISTORY_DATE = "data_tran";
}
