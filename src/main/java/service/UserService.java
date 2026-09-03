package service;

import model.User;

public interface UserService {

    User login(String username, String password);

    User findByUsername(String username);

    void updateProfile(User user);
}
