package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Category;

public class TestJpa {

    public static void main(String[] args) {

        EntityManager em =
                JpaConfig.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        Category category =
                new Category();

        category.setCategoryname("Iphone JPA Test");
        category.setImages("abc.jpg");
        category.setStatus(1);

        try {
            transaction.begin();
            em.persist(category);
            transaction.commit();

            System.out.println("JPA test insert thanh cong.");
            System.out.println("CategoryId: " + category.getCategoryid());
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
            JpaConfig.close();
        }
    }
}
