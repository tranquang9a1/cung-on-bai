/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.BaseDao.em;
import entity.TblAnswer;
import entity.TblQuestion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author khangtnse60992
 */
public class AnswerDao extends BaseDao<TblAnswer> {

    public List<TblAnswer> getListAnswer(TblQuestion questionId) {
        Query query = null;
        query = em.createNamedQuery("TblAnswer.findAllByQuestionId");
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }

    public void insertAnswer(int[] point, String[] content, TblQuestion tblQuestion) {
        TblAnswer answer = null;
        for (int i = 0; i < content.length; i++) {
            answer = new TblAnswer();
            answer.setContent(content[i]);
            answer.setPoint(point[i]);
            answer.setQuestionId(tblQuestion);
            insert(answer);
        }
    }

    public TblAnswer getAnswerById(int id) {
        Query query = em.createNamedQuery("TblAnswer.findByAnswerId");
        return (TblAnswer) query.getSingleResult();
    }

    public List<TblAnswer> getListAnswer(int[] point, String[] content, TblQuestion tblQuestion) {
        List<TblAnswer> tblAnswers = new ArrayList<TblAnswer>();
        TblAnswer answer = null;
        for (int i = 0; i < content.length; i++) {
            answer = new TblAnswer();
            answer.setContent(content[i]);
            answer.setPoint(point[i]);
            answer.setQuestionId(tblQuestion);
            tblAnswers.add(answer);
        }
        return tblAnswers;
    }

    public List<TblAnswer> getListAnswer(int[] id, int[] point, String[] content, TblQuestion tblQuestion) {
        TblAnswer answer = null;
        List<TblAnswer> tblAnswers = new ArrayList<TblAnswer>();
        for (int i = 0; i < content.length; i++) {
            try {
                answer = findById(TblAnswer.class, id[i]);

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                answer = new TblAnswer();
            }
            answer.setContent(content[i]);
            answer.setPoint(point[i]);
            answer.setQuestionId(tblQuestion);
            tblAnswers.add(answer);
        }
        return tblAnswers;
    }

    public void updateAnswer(int[] id, int[] point, String[] content, TblQuestion tblQuestion) {
        TblAnswer answer = null;

        for (int i = 0; i < id.length; i++) {
            answer = findById(TblAnswer.class, id[i]);
            answer.setContent(content[i]);
            answer.setPoint(point[i]);
            answer.setQuestionId(tblQuestion);
            update(answer);
        }
    }

    public TblAnswer findWrongAnswerByQuestionId(int questionId) {
        Query query = em.createNamedQuery("TblAnswer.findWrongAnswerByQuestionId");
        query.setParameter("questionId", questionId);
        return (TblAnswer) query.getResultList().get(0);
    }
}
