package main.models;

/**
 * Created by brama on 1/13/17.
 */
public class Token {
    public String token;

    public Token() {
        this.token = "";
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
