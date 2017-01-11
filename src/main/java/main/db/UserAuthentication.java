package main.db;

import main.models.AuthenticationResponse;

import java.sql.ResultSet;

/**
 * Created by brama on 1/11/17.
 */
public class UserAuthentication extends Database {
    public ResultSet validateUser(String username, String password) {

        return null;
    }

    public ResultSet logoutUser(String token) {

        return null;
    }

    public boolean validateToken(String token) {

        return false;
    }

    public void createToken(String username) {

    }

    public void destroyToken(String token) {

    }

}
