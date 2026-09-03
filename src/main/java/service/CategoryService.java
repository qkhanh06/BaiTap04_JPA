package service;

import java.util.List;

import model.Category;

public interface CategoryService {

    void insert(Category category);

    void update(Category category);

    void edit(Category category);

    void delete(int id);

    Category findById(int id);

    Category get(int id);

    Category findByCategoryname(String name);

    Category get(String name);

    List<Category> findAll();

    List<Category> getAll();

    List<Category> searchByName(String keyword);

    List<Category> search(String keyword);

    List<Category> findAll(int page, int pagesize);

    int count();
}
