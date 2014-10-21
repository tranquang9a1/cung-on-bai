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
@Table(name = "tbl_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAnswer.findAll", query = "SELECT t FROM TblAnswer t"),
    @NamedQuery(name = "TblAnswer.findAllByQuestionId", query = "SELECT t FROM TblAnswer t where t.questionId = :questionId"),
    @NamedQuery(name = "TblAnswer.findByAnswerId", query = "SELECT t FROM TblAnswer t WHERE t.answerId = :answerId"),
    @NamedQuery(name = "TblAnswer.findWrongAnswerByQuestionId", query = "SELECT t FROM TblAnswer t WHERE t.point = 0 and t.questionId.questionId = :questionId"),
    @NamedQuery(name = "TblAnswer.findByPoint", query = "SELECT t FROM TblAnswer t WHERE t.point = :point")})
public class TblAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "answer_id",columnDefinition = "serial")
    private Integer answerId;
    @Basic(optional = false)
    @Lob
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @Column(name = "point")
    private int point;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answerId")
    private List<TblSessionQuestion> tblSessionQuestionList;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne(optional = false)
    private TblQuestion questionId;

    public TblAnswer() {
    }

    public TblAnswer(Integer answerId) {
        this.answerId = answerId;
    }

    public TblAnswer(Integer answerId, String content, int point) {
        this.answerId = answerId;
        this.content = content;
        this.point = point;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @XmlTransient
    public List<TblSessionQuestion> getTblSessionQuestionList() {
        return tblSessionQuestionList;
    }

    public void setTblSessionQuestionList(List<TblSessionQuestion> tblSessionQuestionList) {
        this.tblSessionQuestionList = tblSessionQuestionList;
    }

    public TblQuestion getQuestionId() {
        return questionId;
    }

    public void setQuestionId(TblQuestion questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerId != null ? answerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAnswer)) {
            return false;
        }
        TblAnswer other = (TblAnswer) object;
        if ((this.answerId == null && other.answerId != null) || (this.answerId != null && !this.answerId.equals(other.answerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblAnswer[ answerId=" + answerId + " ]";
    }
    
}
