package main.db;

import main.models.User;

/**
 * Created by brama on 1/10/17.
 */
public interface UserDAO {
    public void insert(User user);

    public User findByUserName(String name);

}
