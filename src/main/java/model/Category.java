package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryid;

    @Column(name = "CategoryName", columnDefinition = "nvarchar(50) not null")
    @NotEmpty(message = "Khong duoc phep rong")
    private String categoryname;

    @Column(name = "Images", columnDefinition = "nvarchar(500) null")
    private String images;

    @Column(name = "status")
    private int status = 1;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public Category() {
    }

    public Category(int categoryid, String categoryname, String images) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
        this.images = images;
        this.status = 1;
    }

    public Category(String categoryname, String images) {
        this.categoryname = categoryname;
        this.images = images;
        this.status = 1;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return categoryid;
    }

    public void setId(int id) {
        this.categoryid = id;
    }

    public String getName() {
        return categoryname;
    }

    public void setName(String name) {
        this.categoryname = name;
    }

    public String getIcon() {
        return images;
    }

    public void setIcon(String icon) {
        this.images = icon;
    }

    @Override
    public String toString() {
        return categoryid + " | " + categoryname + " | " + images + " | " + status;
    }
}
