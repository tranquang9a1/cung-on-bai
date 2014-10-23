/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.BaseDao.em;
import entity.TblSession;
import entity.TblSessionQuestion;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author computer
 */
public class SessionQuestionDao extends BaseDao<TblSessionQuestion> {

    public List<TblSessionQuestion> findBySessionId(int id) {
        Query query = em.createNamedQuery("TblSessionQuestion.findById");
        query.setParameter("id", id);
        return query.getResultList();
    }
}
