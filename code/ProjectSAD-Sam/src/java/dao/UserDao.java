/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.BaseDao.em;
import entity.TblUser;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author dinhquangtrung
 */
public class UserDao extends BaseDao<TblUser>{
    public List<TblUser> getAllUser() {
        Query query = em.createNamedQuery("TblUser.findAll");
        return query.getResultList();
    }
    public TblUser getUserByUsername(String username) {
        Query query = em.createNamedQuery("TblUser.findByUsername");
        query.setParameter("username", username);
        try {
            return (TblUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
