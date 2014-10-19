/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblQuestion;
import entity.TblSubject;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author khangtnse60992
 */
public class QuestionDao extends BaseDao<TblQuestion>{
    
    public List<TblQuestion> getListQuestion(TblSubject subject, int from , int to) {
        Query query = null;
        query = em.createNamedQuery("TblAnswer.findByAnswerId");
        query.setFirstResult(from);
        query.setMaxResults(to);
        return query.getResultList();
    }
    

}