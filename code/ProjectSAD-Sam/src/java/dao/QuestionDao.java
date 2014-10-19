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
    
    public List<TblQuestion> getListQuestion(int subject, int from , int to) {
        Query query = null;
        query = em.createNamedQuery("TblQuestion.findBySubjectId");
        query.setParameter("subjectId", subject);
        query.setFirstResult(from);
        query.setMaxResults(to);
        return query.getResultList();
    }
    public List<TblQuestion> getListRandom(int number) {
        Query query = em.createNamedQuery("TblQuestion.findRandom");
        query.setMaxResults(number);
        return query.getResultList();
    }

}
