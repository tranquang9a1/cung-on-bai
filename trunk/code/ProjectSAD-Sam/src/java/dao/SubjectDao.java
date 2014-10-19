/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblSubject;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author khangtnse60992
 */
public class SubjectDao extends BaseDao<TblSubject>{
    public List<TblSubject> getListAllSubject(){
        Query query = null;
        query = em.createNamedQuery("TblSubject.findAll");
        return query.getResultList();
    }
    
}
