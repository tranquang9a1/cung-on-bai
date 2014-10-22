/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.BaseDao.em;
import entity.TblMessage;
import entity.TblUser;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author dinhquangtrung
 */
public class MessageDao extends BaseDao<TblMessage> {
    public List<TblMessage> getAllMessage() {
        Query query = em.createNamedQuery("TblMessage.findAll");
        return query.getResultList();
    }
    public List<TblMessage> getAllMessage(int moment) {
        Query query = em.createNamedQuery("TblMessage.findAllFromMoment");
        query.setParameter("latestDate", moment);
        return query.getResultList();
    }
}
