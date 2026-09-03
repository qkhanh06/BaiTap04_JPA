package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao =
            new UserDaoImpl();

    @Override
    public User login(String username, String password) {

        User user =
                userDao.findByUsername(username);

        if (user == null
                && "admin".equals(username)
                && "123456".equals(password)) {

            user =
                    new User(
                            "admin",
                            "123456",
                            "Lê Phạm Quang Khánh",
                            "",
                            "");

            userDao.insert(user);
        }

        if (user != null
                && user.getPassword().equals(password)) {

            return user;
        }

        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void updateProfile(User user) {
        userDao.update(user);
    }
}
