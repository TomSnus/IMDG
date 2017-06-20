package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by stt44293 on 20.06.2017.
 */
public class DbHelper {
    public Connection connection = null;
    public DbHelper() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+DbContract.DB_Host+"/"+DbContract.DB, DbContract.DB_USERNAME, DbContract.DB_PW);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
