package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    public Connection connect = null;
    public Statement statement = null;
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;

    public Database() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            this.connect = DriverManager
                    .getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7152891?"
                            + "user=sql7152891&password=2lfGA3yCas");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                this.resultSet.close();
            }

            if (statement != null) {
                this.statement.close();
            }

            if (connect != null) {
                this.connect.close();
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

}