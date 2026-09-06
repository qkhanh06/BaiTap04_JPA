package service.impl;

import java.time.LocalDateTime;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;
import util.EmailUtil;
import util.OtpUtil;

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

            user.setEmail("admin@example.com");
            user.setActive(true);

            userDao.insert(user);
        }

        if (user != null
                && user.isActive()
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
    public User register(
            String username,
            String password,
            String fullname,
            String phone,
            String email) {

        if (userDao.findByUsername(username) != null
                || userDao.findByEmail(email) != null) {
            return null;
        }

        User user =
                new User(username, password, fullname, phone, "");

        String otp =
                OtpUtil.generate();

        user.setEmail(email);
        user.setActive(false);
        user.setOtpCode(otp);
        user.setOtpExpireTime(
                LocalDateTime.now().plusMinutes(5));

        userDao.insert(user);

        EmailUtil.send(
                email,
                "Ma OTP kich hoat tai khoan",
                "Ma OTP cua ban la: " + otp
                        + ". Ma co hieu luc trong 5 phut.");

        return user;
    }

    @Override
    public boolean activate(String username, String otp) {

        User user =
                userDao.findByUsername(username);

        if (user == null
                || user.getOtpCode() == null
                || user.getOtpExpireTime() == null
                || LocalDateTime.now().isAfter(user.getOtpExpireTime())
                || !user.getOtpCode().equals(otp)) {
            return false;
        }

        user.setActive(true);
        user.setOtpCode(null);
        user.setOtpExpireTime(null);
        userDao.update(user);

        return true;
    }

    @Override
    public String createResetOtp(String email) {

        User user =
                userDao.findByEmail(email);

        if (user == null) {
            return null;
        }

        String otp =
                OtpUtil.generate();

        user.setResetOtpCode(otp);
        user.setResetOtpExpireTime(
                LocalDateTime.now().plusMinutes(5));
        userDao.update(user);

        EmailUtil.send(
                email,
                "Ma OTP dat lai mat khau",
                "Ma OTP dat lai mat khau cua ban la: " + otp
                        + ". Ma co hieu luc trong 5 phut.");

        return otp;
    }

    @Override
    public boolean resetPassword(
            String email,
            String otp,
            String newPassword) {

        User user =
                userDao.findByEmail(email);

        if (user == null
                || user.getResetOtpCode() == null
                || user.getResetOtpExpireTime() == null
                || LocalDateTime.now().isAfter(user.getResetOtpExpireTime())
                || !user.getResetOtpCode().equals(otp)) {
            return false;
        }

        user.setPassword(newPassword);
        user.setResetOtpCode(null);
        user.setResetOtpExpireTime(null);
        userDao.update(user);

        return true;
    }

    @Override
    public void updateProfile(User user) {
        userDao.update(user);
    }
}
