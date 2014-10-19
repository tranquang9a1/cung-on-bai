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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_question")
@XmlRootElement
@NamedQueries({ 
//    SELECT column FROM table
//ORDER BY RANDOM()
//LIMIT 1
    @NamedQuery(name = "TblQuestion.findAll", query = "SELECT t FROM TblQuestion t"),
     @NamedQuery(name = "TblQuestion.findRandom", query = "SELECT t FROM TblQuestion t WHERE t.subjectId.subjectId = :subjectId ORDER BY RAND()"),
    @NamedQuery(name = "TblQuestion.findBySubjectId", query = "SELECT t FROM TblQuestion t WHERE t.subjectId = :subjectId"),
    @NamedQuery(name = "TblQuestion.findByQuestionId", query = "SELECT t FROM TblQuestion t WHERE t.questionId = :questionId")})
public class TblQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id")
    private Integer questionId;
    @Basic(optional = false)
    @Lob
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    @ManyToOne(optional = false)
    private TblSubject subjectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private List<TblSessionQuestion> tblSessionQuestionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private List<TblFavorite> tblFavoriteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId" , fetch = FetchType.LAZY)
    private List<TblAnswer> tblAnswerList;

    public TblQuestion() {
    }

    public TblQuestion(Integer questionId) {
        this.questionId = questionId;
    }

    public TblQuestion(Integer questionId, String content) {
        this.questionId = questionId;
        this.content = content;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TblSubject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(TblSubject subjectId) {
        this.subjectId = subjectId;
    }

    @XmlTransient
    public List<TblSessionQuestion> getTblSessionQuestionList() {
        return tblSessionQuestionList;
    }

    public void setTblSessionQuestionList(List<TblSessionQuestion> tblSessionQuestionList) {
        this.tblSessionQuestionList = tblSessionQuestionList;
    }

    @XmlTransient
    public List<TblFavorite> getTblFavoriteList() {
        return tblFavoriteList;
    }

    public void setTblFavoriteList(List<TblFavorite> tblFavoriteList) {
        this.tblFavoriteList = tblFavoriteList;
    }

    @XmlTransient
    public List<TblAnswer> getTblAnswerList() {
        return tblAnswerList;
    }

    public void setTblAnswerList(List<TblAnswer> tblAnswerList) {
        this.tblAnswerList = tblAnswerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblQuestion)) {
            return false;
        }
        TblQuestion other = (TblQuestion) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblQuestion[ questionId=" + questionId + " ]";
    }
    
}
