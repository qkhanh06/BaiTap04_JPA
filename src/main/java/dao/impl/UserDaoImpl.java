package dao.impl;

import dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.User;
import util.JpaUtil;

public class UserDaoImpl implements UserDao {

    @Override
    public void insert(User user) {

        EntityManager em =
                JpaUtil.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {
            transaction.begin();
            em.persist(user);
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
    public void update(User user) {

        EntityManager em =
                JpaUtil.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {
            transaction.begin();
            em.merge(user);
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
    public User findByUsername(String username) {

        EntityManager em =
                JpaUtil.getEntityManager();

        try {
            return em.find(User.class, username);
        } finally {
            em.close();
        }
    }
}
