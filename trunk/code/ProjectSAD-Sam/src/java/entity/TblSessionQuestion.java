/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author khangtnse60992
 */
@Entity
@Table(name = "tbl_session_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSessionQuestion.findAll", query = "SELECT t FROM TblSessionQuestion t"),
    @NamedQuery(name = "TblSessionQuestion.findById", query = "SELECT t FROM TblSessionQuestion t WHERE t.id = :id")})
public class TblSessionQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    @ManyToOne(optional = false)
    private TblSession sessionId;
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id", nullable = true)
    @ManyToOne(optional = true)
    private TblAnswer answerId;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne(optional = false)
    private TblQuestion questionId;

    public TblSessionQuestion() {
    }

    public TblSessionQuestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblSession getSessionId() {
        return sessionId;
    }

    public void setSessionId(TblSession sessionId) {
        this.sessionId = sessionId;
    }

    public TblAnswer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(TblAnswer answerId) {
        this.answerId = answerId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSessionQuestion)) {
            return false;
        }
        TblSessionQuestion other = (TblSessionQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblSessionQuestion[ id=" + id + " ]";
    }
    
}
