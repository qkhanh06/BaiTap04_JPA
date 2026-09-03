package dao;

import java.util.List;

import model.Category;

public interface CategoryDao {

    void insert(Category category);

    void update(Category category);

    void edit(Category category);

    void delete(int cateid) throws Exception;

    Category findById(int cateid);

    Category get(int id);

    Category findByCategoryname(String name) throws Exception;

    Category get(String name);

    List<Category> findAll();

    List<Category> getAll();

    List<Category> searchByName(String catname);

    List<Category> search(String keyword);

    List<Category> findAll(int page, int pagesize);

    int count();
}
