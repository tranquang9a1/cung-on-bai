/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.BaseDao.em;
import entity.TblSubject;
import java.util.List;
import javax.persistence.Query;

/**
 *findBySubjectId
 * @author khangtnse60992
 */
public class SubjectDao extends BaseDao<TblSubject>{
    public List<TblSubject> getListAllSubject(){
        Query query = em.createNamedQuery("TblSubject.findAll");
        return query.getResultList();
    }
     public TblSubject findBySubjectId(int subjectId){
        Query query = em.createNamedQuery("TblSubject.findBySubjectId");
        query.setParameter("subjectId", subjectId);
        return (TblSubject) query.getSingleResult();
    }
}
