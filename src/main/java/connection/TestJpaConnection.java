package connection;

import jakarta.persistence.EntityManager;
import model.Category;
import util.JpaUtil;

public class TestJpaConnection {

    public static void main(String[] args) {

        EntityManager em =
                JpaUtil.getEntityManager();

        try {
            Long total =
                    em.createQuery(
                                    "SELECT COUNT(c) FROM Category c",
                                    Long.class)
                            .getSingleResult();

            Category firstCategory =
                    em.find(Category.class, 1);

            System.out.println("JPA connected successfully.");
            System.out.println("Total categories: " + total);

            if (firstCategory != null) {
                System.out.println("First category: "
                        + firstCategory.getName());
            }
        } finally {
            em.close();
            JpaUtil.close();
        }
    }
}
