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
@Table(name = "tbl_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t"),
    @NamedQuery(name = "TblUser.findByUserId", query = "SELECT t FROM TblUser t WHERE t.userId = :userId"),
    @NamedQuery(name = "TblUser.findByUsername", query = "SELECT t FROM TblUser t WHERE t.username = :username"),
    @NamedQuery(name = "TblUser.findByScore", query = "SELECT t FROM TblUser t WHERE t.score = :score")})
public class TblUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "score")
    private int score;
    @Column(name = "is_admin")
    private int isAdmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<TblSession> tblSessionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<TblFavorite> tblFavoriteList;

    public TblUser() {
    }

    public TblUser(Integer userId) {
        this.userId = userId;
    }

    public TblUser(String username, int score, int isAdmin) {
        this.userId = userId;
        this.username = username;
        this.score = score;
        this.isAdmin = isAdmin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @XmlTransient
    public List<TblSession> getTblSessionList() {
        return tblSessionList;
    }

    public void setTblSessionList(List<TblSession> tblSessionList) {
        this.tblSessionList = tblSessionList;
    }

    @XmlTransient
    public List<TblFavorite> getTblFavoriteList() {
        return tblFavoriteList;
    }

    public void setTblFavoriteList(List<TblFavorite> tblFavoriteList) {
        this.tblFavoriteList = tblFavoriteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblUser[ userId=" + userId + " ]";
    }
    
}
