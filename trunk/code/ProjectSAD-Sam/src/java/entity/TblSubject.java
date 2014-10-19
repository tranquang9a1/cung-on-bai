/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author khangtnse60992
 */
@Entity
@Table(name = "tbl_subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSubject.findAll", query = "SELECT t FROM TblSubject t"),
    @NamedQuery(name = "TblSubject.findBySubjectId", query = "SELECT t FROM TblSubject t WHERE t.subjectId = :subjectId"),
    @NamedQuery(name = "TblSubject.findBySubjectName", query = "SELECT t FROM TblSubject t WHERE t.subjectName = :subjectName")})
public class TblSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subject_id")
    private Integer subjectId;
    @Basic(optional = false)
    @Column(name = "subject_name")
    private String subjectName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private List<TblQuestion> tblQuestionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private List<TblSession> tblSessionList;

    public TblSubject() {
    }

    public TblSubject(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public TblSubject(Integer subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @XmlTransient
    public List<TblQuestion> getTblQuestionList() {
        return tblQuestionList;
    }

    public void setTblQuestionList(List<TblQuestion> tblQuestionList) {
        this.tblQuestionList = tblQuestionList;
    }

    @XmlTransient
    public List<TblSession> getTblSessionList() {
        return tblSessionList;
    }

    public void setTblSessionList(List<TblSession> tblSessionList) {
        this.tblSessionList = tblSessionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectId != null ? subjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSubject)) {
            return false;
        }
        TblSubject other = (TblSubject) object;
        if ((this.subjectId == null && other.subjectId != null) || (this.subjectId != null && !this.subjectId.equals(other.subjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblSubject[ subjectId=" + subjectId + " ]";
    }
    
}
