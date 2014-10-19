/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblAnswer;
import entity.TblQuestion;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author khangtnse60992
 */
public class AnswerDao extends BaseDao<TblAnswer>{
    public List<TblAnswer> getListAnswer(TblQuestion questionId) {
        Query query = null;
        query = em.createNamedQuery("TblAnswer.findAllByQuestionId");
        query.setParameter("questionId", questionId);
        return query.getResultList();      
    }
    public void insertAnswer(int[] point,String[] content , TblQuestion tblQuestion) {
        TblAnswer answer = null;
        for(int i = 0 ; i < content.length; i++) {
            answer = new TblAnswer();
            answer.setContent(content[i]);
            answer.setPoint(point[i]);
            answer.setQuestionId(tblQuestion);
            insert(answer);
        }
        
    }
}
