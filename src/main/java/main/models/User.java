package main.models;

/**
 * Created by brama on 1/8/17.
 */
public class User {

    private final String name;
    private final String pass_hash;
    private final String token;

    public User(String name, String pass_hash, String token) {
        this.name = name;
        this.pass_hash = pass_hash;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getPass_hash() {
        return pass_hash;
    }

    public String getToken() {
        return token;
    }
}
