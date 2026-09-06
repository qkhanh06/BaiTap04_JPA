package dao.impl;

import java.util.List;

import dao.ProductDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Product;
import util.JpaUtil;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void insert(Product product) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(product);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(product);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Product product = em.find(Product.class, id);

            if (product == null) {
                throw new Exception("Khong tim thay product");
            }

            em.remove(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Product findById(int id) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            TypedQuery<Product> query =
                    em.createQuery(
                            "SELECT p FROM Product p JOIN FETCH p.category WHERE p.productId = :id",
                            Product.class);

            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Product p JOIN FETCH p.category ORDER BY p.createdAt DESC",
                    Product.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findAll(int page, int pagesize) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            TypedQuery<Product> query =
                    em.createQuery(
                            "SELECT p FROM Product p JOIN FETCH p.category ORDER BY p.createdAt DESC",
                            Product.class);

            query.setFirstResult(page * pagesize);
            query.setMaxResults(pagesize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findLatest(int limit) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Product p JOIN FETCH p.category "
                            + "WHERE p.status = 1 ORDER BY p.createdAt DESC",
                    Product.class)
                    .setMaxResults(limit)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int count() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            Query query =
                    em.createQuery("SELECT COUNT(p) FROM Product p");

            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
