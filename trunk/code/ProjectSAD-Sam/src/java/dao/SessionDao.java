/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblSession;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author computer
 */
public class SessionDao extends BaseDao<TblSession> {

    public List<TblSession> getSessionId(int sessionId) {
        Query query = em.createNamedQuery("TblSession.findBySessionId");
        query.setParameter("sessionId", sessionId);
        return query.getResultList();
    }

    public List<TblSession> getSessionByUserId(int userId) {
        Query query = em.createNamedQuery("TblSession.findAllByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
