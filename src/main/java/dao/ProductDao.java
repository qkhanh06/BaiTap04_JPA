package dao;

import java.util.List;

import model.Product;

public interface ProductDao {

    void insert(Product product);

    void update(Product product);

    void delete(int id) throws Exception;

    Product findById(int id);

    List<Product> findAll();

    List<Product> findAll(int page, int pagesize);

    List<Product> findLatest(int limit);

    int count();
}
