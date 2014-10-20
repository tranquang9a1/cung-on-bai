/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblAnswer;
import entity.TblQuestion;
import java.util.ArrayList;
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
    public List<TblAnswer> insertListAnswer(int[] point,String[] content , TblQuestion tblQuestion) {
        List<TblAnswer> tblAnswers = new ArrayList<TblAnswer>();
        TblAnswer answer = null;
        for(int i = 0 ; i < content.length; i++) {
            answer = new TblAnswer();
            answer.setContent(content[i]);
            answer.setPoint(point[i]);
            answer.setQuestionId(tblQuestion);
            tblAnswers.add(answer);
        }
        return  tblAnswers;
    }
    public void updateAnswer(int[] id,int[] point,String[] content , TblQuestion tblQuestion) {
        TblAnswer answer = null;
        
        for(int i = 0 ; i < id.length; i++) {
            answer = findById(TblAnswer.class, id[i]);
            answer.setContent(content[i]);
            answer.setPoint(point[i]);
            answer.setQuestionId(tblQuestion);
            update(answer);
        }
        
    }
}
