package main.db;

import main.models.User;

import javax.activation.DataSource;

/**
 * Created by brama on 1/10/17.
 */
public class JdbcUserDAO implements UserDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(User user) {
        /**
         * TODO
         */
    }

    public User findByUserName(String name) {
        /**
         * TODO
         */
        return new User("found name", "pass_hash", "");
    }
}
