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
            String sql = "SELECT * FROM users WHERE users.name = ? AND users.pass_hash = ?";
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
            String sql = "UPDATE users SET token = '' WHERE users.token = ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, token);
            int result = this.preparedStatement.executeUpdate(); // 0 for SQL statements that returns nothing
            //System.out.println("Return status: " + result); //This should return 1 but returns 0 - confusing
            return result;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return -1;

    }

    public String getUserByToken(String token) {
        String username = "";
        try {
            String sql = "SELECT * FROM users WHERE users.token = ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, token);

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                username = this.resultSet.getString("name");
                System.out.println("Validated user by token - Username: " + username);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return username;
    }

    public String createToken(String username) {
        String token = new HashService().generateToken();
        try {
            String sql = "UPDATE users SET token = ? WHERE users.name = ?";
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
