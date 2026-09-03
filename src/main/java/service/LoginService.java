package service;

import model.User;
import service.impl.UserServiceImpl;

public class LoginService {

    private final UserService userService =
            new UserServiceImpl();

    public boolean checkLogin(User user) {

        return login(user) != null;
    }

    public User login(User user) {

        return userService.login(
                user.getUsername(),
                user.getPassword());
    }
}
