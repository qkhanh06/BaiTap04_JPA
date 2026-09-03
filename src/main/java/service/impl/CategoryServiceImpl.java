package service.impl;

import java.util.List;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import model.Category;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao =
            new CategoryDaoImpl();

    @Override
    public void insert(Category category) {

        Category oldCategory =
                findByCategoryname(
                        category.getCategoryname());

        if (oldCategory == null) {
            categoryDao.insert(category);
        }
    }

    @Override
    public void update(Category category) {

        Category oldCategory =
                findById(
                        category.getCategoryid());

        if (oldCategory != null) {
            categoryDao.update(category);
        }
    }

    @Override
    public void edit(Category newCategory) {

        Category oldCategory =
                findById(
                        newCategory.getCategoryid());

        if (oldCategory == null) {
            return;
        }

        oldCategory.setCategoryname(
                newCategory.getCategoryname());

        oldCategory.setStatus(
                newCategory.getStatus());

        if (newCategory.getImages() != null
                && !newCategory.getImages().isBlank()) {

            oldCategory.setImages(
                    newCategory.getImages());
        }

        categoryDao.update(oldCategory);
    }

    @Override
    public void delete(int id) {

        try {
            categoryDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category get(int id) {
        return findById(id);
    }

    @Override
    public Category findByCategoryname(String name) {

        try {
            return categoryDao.findByCategoryname(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Category get(String name) {
        return findByCategoryname(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> getAll() {
        return findAll();
    }

    @Override
    public List<Category> searchByName(String keyword) {
        return categoryDao.searchByName(keyword);
    }

    @Override
    public List<Category> search(String keyword) {
        return searchByName(keyword);
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        return categoryDao.findAll(page, pagesize);
    }

    @Override
    public int count() {
        return categoryDao.count();
    }
}
