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
            /*this.connect = DriverManager
                    .getConnection("jdbc:mysql://" + System.getenv("MYSQL_URL") + "/" + System.getenv("MYSQL_DATABASE")
                            + "user=" + System.getenv("MYSQL_USER") + "&password=" + System.getenv("MYSQL_PASSWORD"));*/
            //this.connect = DriverManager.getConnection("jdbc:mysql://" + System.getenv("MYSQL_URL") + "/" + System.getenv("MYSQL_DATABASE"), System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASSWORD"));
            //this.connect = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7152891", "sql7152891", System.getenv("MYSQL_PASSWORD"));
            this.connect = DriverManager.getConnection("jdbc:mysql://eu-cdbr-west-01.cleardb.com/heroku_26ad5be0859c7ad", "b5f5f7a46d64d6", System.getenv("MYSQL_PASSWORD"));
        } catch (Exception e) {
            System.out.println(e.toString());
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
            System.out.println(e.toString());
        }
    }

}
