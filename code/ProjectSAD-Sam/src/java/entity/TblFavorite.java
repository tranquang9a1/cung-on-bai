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
@Table(name = "tbl_favorite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblFavorite.findAll", query = "SELECT t FROM TblFavorite t"),
    @NamedQuery(name = "TblFavorite.findByUserId", query = "SELECT t FROM TblFavorite t WHERE t.userId.userId = :userId"),
    @NamedQuery(name = "TblFavorite.findById", query = "SELECT t FROM TblFavorite t WHERE t.id = :id")})
public class TblFavorite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne(optional = false)
    private TblQuestion questionId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private TblUser userId;

    public TblFavorite() {
    }

    public TblFavorite(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblQuestion getQuestionId() {
        return questionId;
    }

    public void setQuestionId(TblQuestion questionId) {
        this.questionId = questionId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblFavorite)) {
            return false;
        }
        TblFavorite other = (TblFavorite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblFavorite[ id=" + id + " ]";
    }
    
}
