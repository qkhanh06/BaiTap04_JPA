package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Username", columnDefinition = "varchar(50)")
    private String username;

    @Column(name = "Password", columnDefinition = "varchar(100) not null")
    private String password;

    @Column(name = "Fullname", columnDefinition = "nvarchar(100)")
    private String fullname;

    @Column(name = "Phone", columnDefinition = "varchar(20)")
    private String phone;

    @Column(name = "Images", columnDefinition = "nvarchar(500)")
    private String images;

    @Column(name = "Email", columnDefinition = "varchar(120)")
    private String email;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "OtpCode", columnDefinition = "varchar(10)")
    private String otpCode;

    @Column(name = "OtpExpireTime")
    private java.time.LocalDateTime otpExpireTime;

    @Column(name = "ResetOtpCode", columnDefinition = "varchar(10)")
    private String resetOtpCode;

    @Column(name = "ResetOtpExpireTime")
    private java.time.LocalDateTime resetOtpExpireTime;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(
            String username,
            String password,
            String fullname,
            String phone,
            String images) {

        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.images = images;
        this.active = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public java.time.LocalDateTime getOtpExpireTime() {
        return otpExpireTime;
    }

    public void setOtpExpireTime(java.time.LocalDateTime otpExpireTime) {
        this.otpExpireTime = otpExpireTime;
    }

    public String getResetOtpCode() {
        return resetOtpCode;
    }

    public void setResetOtpCode(String resetOtpCode) {
        this.resetOtpCode = resetOtpCode;
    }

    public java.time.LocalDateTime getResetOtpExpireTime() {
        return resetOtpExpireTime;
    }

    public void setResetOtpExpireTime(java.time.LocalDateTime resetOtpExpireTime) {
        this.resetOtpExpireTime = resetOtpExpireTime;
    }
}
