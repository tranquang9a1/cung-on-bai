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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dinhquangtrung
 */
@Entity
@Table(name = "tbl_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblMessage.findAll", query = "SELECT t FROM TblMessage t"),
    @NamedQuery(name = "TblMessage.findAllFromMoment", query = "SELECT t FROM TblMessage t WHERE t.createdDate > :latestDate"),
    @NamedQuery(name = "TblMessage.findByMessageId", query = "SELECT t FROM TblMessage t WHERE t.messageId = :messageId"),
    @NamedQuery(name = "TblMessage.findByContent", query = "SELECT t FROM TblMessage t WHERE t.content = :content"),
    @NamedQuery(name = "TblMessage.findByFromUsername", query = "SELECT t FROM TblMessage t WHERE t.fromUsername = :fromUsername"),
    @NamedQuery(name = "TblMessage.findByCreatedDate", query = "SELECT t FROM TblMessage t WHERE t.createdDate = :createdDate")})
public class TblMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Column(name = "content")
    private String content;
    @Column(name = "fromUsername")
    private String fromUsername;
    @Column(name = "created_date")
    private Integer createdDate;

    public TblMessage() {
    }

    public TblMessage(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public Integer getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblMessage)) {
            return false;
        }
        TblMessage other = (TblMessage) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblMessage[ messageId=" + messageId + " ]";
    }
    
}
