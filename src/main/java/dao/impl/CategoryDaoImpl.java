package dao.impl;

import java.util.List;

import dao.CategoryDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Category;
import util.JpaUtil;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void insert(Category category) {

        EntityManager em =
                JpaUtil.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {
            transaction.begin();
            em.persist(category);
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
    public void update(Category category) {

        EntityManager em =
                JpaUtil.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {
            transaction.begin();
            em.merge(category);
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
    public void edit(Category category) {
        update(category);
    }

    @Override
    public void delete(int cateid) throws Exception {

        EntityManager em =
                JpaUtil.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {
            transaction.begin();

            Category category =
                    em.find(Category.class, cateid);

            if (category != null) {
                em.remove(category);
            } else {
                throw new Exception("Khong tim thay category");
            }

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
    public Category findById(int cateid) {

        EntityManager em =
                JpaUtil.getEntityManager();

        try {
            return em.find(Category.class, cateid);
        } finally {
            em.close();
        }
    }

    @Override
    public Category get(int id) {
        return findById(id);
    }

    @Override
    public Category findByCategoryname(String name) throws Exception {

        EntityManager em =
                JpaUtil.getEntityManager();

        String jpql =
                "SELECT c FROM Category c "
                + "WHERE c.categoryname = :catename";

        try {
            TypedQuery<Category> query =
                    em.createQuery(jpql, Category.class);

            query.setParameter("catename", name);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Category get(String name) {
        try {
            return findByCategoryname(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Category> findAll() {

        EntityManager em =
                JpaUtil.getEntityManager();

        try {
            TypedQuery<Category> query =
                    em.createNamedQuery(
                            "Category.findAll",
                            Category.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> getAll() {
        return findAll();
    }

    @Override
    public List<Category> searchByName(String catname) {

        EntityManager em =
                JpaUtil.getEntityManager();

        String jpql =
                "SELECT c FROM Category c "
                + "WHERE c.categoryname LIKE :catename";

        try {
            TypedQuery<Category> query =
                    em.createQuery(jpql, Category.class);

            query.setParameter("catename", "%" + catname + "%");

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> search(String keyword) {
        return searchByName(keyword);
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {

        EntityManager em =
                JpaUtil.getEntityManager();

        try {
            TypedQuery<Category> query =
                    em.createNamedQuery(
                            "Category.findAll",
                            Category.class);

            query.setFirstResult(page * pagesize);
            query.setMaxResults(pagesize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int count() {

        EntityManager em =
                JpaUtil.getEntityManager();

        try {
            Query query =
                    em.createQuery(
                            "SELECT COUNT(c) FROM Category c");

            return ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
