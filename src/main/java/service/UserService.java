package service;

import model.User;

public interface UserService {

    User login(String username, String password);

    User findByUsername(String username);

    User register(String username, String password, String fullname, String phone, String email);

    boolean activate(String username, String otp);

    String createResetOtp(String email);

    boolean resetPassword(String email, String otp, String newPassword);

    void updateProfile(User user);
}
