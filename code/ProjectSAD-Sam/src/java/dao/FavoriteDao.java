/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblFavorite;
import entity.TblQuestion;
import entity.TblUser;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Huydt
 */
public class FavoriteDao extends BaseDao<TblFavorite> {

    public void insertFavoriteQuestion(TblUser user, String[] lst) {
        for (int i = 0; i<lst.length;i++){
            String questionId = lst[i];
            int id = Integer.parseInt(questionId);
            QuestionDao dao = new QuestionDao();
            TblQuestion question = dao.findById(TblQuestion.class, id);
            TblFavorite favorite = new TblFavorite();
            favorite.setQuestionId(question);
            favorite.setUserId(user);
            insert(favorite);
        }
    }
    public List<TblFavorite> findByUserId (int userId){
        Query query = em.createNamedQuery("TblFavorite.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
        
        
    }
    
    
}
