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
@Table(name = "tbl_session")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSession.findAll", query = "SELECT t FROM TblSession t"),
    @NamedQuery(name = "TblSession.findBySessionId", query = "SELECT t FROM TblSession t WHERE t.sessionId = :sessionId"),
    @NamedQuery(name = "TblSession.findByEndedDate", query = "SELECT t FROM TblSession t WHERE t.endedDate = :endedDate"),
    @NamedQuery(name = "TblSession.findByStartedDate", query = "SELECT t FROM TblSession t WHERE t.startedDate = :startedDate")})
public class TblSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "session_id")
    private Integer sessionId;
    @Basic(optional = false)
    @Column(name = "ended_date")
    private int endedDate;
    @Basic(optional = false)
    @Column(name = "started_date")
    private int startedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionId")
    private List<TblSessionQuestion> tblSessionQuestionList;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    @ManyToOne(optional = false)
    private TblSubject subjectId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private TblUser userId;

    public TblSession() {
    }

    public TblSession(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public TblSession(Integer sessionId, int endedDate, int startedDate) {
        this.sessionId = sessionId;
        this.endedDate = endedDate;
        this.startedDate = startedDate;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public int getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(int endedDate) {
        this.endedDate = endedDate;
    }

    public int getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(int startedDate) {
        this.startedDate = startedDate;
    }

    @XmlTransient
    public List<TblSessionQuestion> getTblSessionQuestionList() {
        return tblSessionQuestionList;
    }

    public void setTblSessionQuestionList(List<TblSessionQuestion> tblSessionQuestionList) {
        this.tblSessionQuestionList = tblSessionQuestionList;
    }

    public TblSubject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(TblSubject subjectId) {
        this.subjectId = subjectId;
    }

    public TblUser getUserId() {
        return userId;
    }

    public void setUserId(TblUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sessionId != null ? sessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSession)) {
            return false;
        }
        TblSession other = (TblSession) object;
        if ((this.sessionId == null && other.sessionId != null) || (this.sessionId != null && !this.sessionId.equals(other.sessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblSession[ sessionId=" + sessionId + " ]";
    }
    
}
