import core.DbConnection;
import core.Helper;
import view.LoginView;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {


        Helper.setTheme();
        LoginView loginView = new LoginView();
    }
}