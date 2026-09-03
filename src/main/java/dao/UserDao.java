package dao;

import model.User;

public interface UserDao {

    void insert(User user);

    void update(User user);

    User findByUsername(String username);
}
