/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author linhvy
 */
public abstract class BaseDao<T> {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectSAD-SamPU");
    /**
     *
     */
    protected static EntityManager em = factory.createEntityManager();

    /**
     *
     * @param type
     * @return
     */
    public T insert(T type) {
        try {
            em.getTransaction().begin();
            em.persist(type);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return type;
    }

    /**
     *
     * @param type
     * @return
     */
    public T update(T type) {
        try {
            em.getTransaction().begin();
            em.merge(type);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
//            System.out.println(e.getMessage());
        }
        return type;
    }

    public void remove(T type) {
        try {
            em.getTransaction().begin();
            em.remove(type);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public T findById(Class<T> T, int id) {
        return (T) em.find(T, id);
    }
}
