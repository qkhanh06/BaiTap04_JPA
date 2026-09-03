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
}
