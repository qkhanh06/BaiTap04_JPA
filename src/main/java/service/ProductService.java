package service;

import java.util.List;

import model.Product;

public interface ProductService {

    void insert(Product product);

    void edit(Product product);

    void delete(int id);

    Product findById(int id);

    List<Product> findAll();

    List<Product> findAll(int page, int pagesize);

    List<Product> findLatest(int limit);

    int count();
}
