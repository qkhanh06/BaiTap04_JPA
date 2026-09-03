package dao.impl;

import java.util.List;

import dao.CategoryDao;
import model.Category;
import util.JpaUtil;

public class TestCategoryDao {

    public static void main(String[] args) {

        CategoryDao dao =
                new CategoryDaoImpl();

        try {
            List<Category> list =
                    dao.search("iPhone");

            for (Category category : list) {
                System.out.println(
                        category.getId()
                        + " - "
                        + category.getName()
                        + " - "
                        + category.getIcon());
            }
        } finally {
            JpaUtil.close();
        }
    }
}
