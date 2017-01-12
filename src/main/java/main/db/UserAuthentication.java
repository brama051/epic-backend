package main.db;

import main.services.HashService;

import java.sql.ResultSet;

/**
 * Created by brama on 1/11/17.
 */
public class UserAuthentication extends Database {

    public int validateUser(String username, String password) {
        String hashedPassword = new HashService().stringToMD5(password);
        try {
            String sql = "SELECT * FROM Users WHERE Users.name = ? AND Users.pass_hash = ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, username);
            this.preparedStatement.setString(2, hashedPassword);
            this.resultSet = this.preparedStatement.executeQuery();

            int userCount = 0;
            while (this.resultSet.next()) {
                userCount++;
                System.out.println("Username: " + this.resultSet.getString("name"));
                System.out.println("Password: " + this.resultSet.getString("pass_hash"));
            }
            return userCount;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int logoutUser(String token) {
        try {
            String sql = "UPDATE Users SET token = '' WHERE Users.token = ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, token);
            return this.preparedStatement.executeUpdate(); // 0 for SQL statements that returns nothing

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return -1;

    }

    public String validateUserToken(String token) {

        return "";
    }

    public String createToken(String username) {
        String token = new HashService().generateToken();
        try {
            String sql = "UPDATE Users SET token = ? WHERE Users.name = ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, token);
            this.preparedStatement.setString(2, username);
            this.preparedStatement.executeUpdate();

            return token;

        } catch (Exception e) {
            System.out.println(e.toString());
        }


        return "";
    }




}
