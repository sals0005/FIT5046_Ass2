/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sarah
 */
@Entity
@Table(name = "CONSUMPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumption.findAll", query = "SELECT c FROM Consumption c")
    , @NamedQuery(name = "Consumption.findByConsumptionid", query = "SELECT c FROM Consumption c WHERE c.consumptionid = :consumptionid")
    , @NamedQuery(name = "Consumption.findByDate", query = "SELECT c FROM Consumption c WHERE c.date = :date")
    , @NamedQuery(name = "Consumption.findByQuantity", query = "SELECT c FROM Consumption c WHERE c.quantity = :quantity")
, @NamedQuery(name = "Consumption.findByUser", query = "SELECT c FROM Consumption c WHERE c.userid.userid = :userid")
        ,@NamedQuery(name = "Consumption.findByFood", query = "SELECT c FROM Consumption c WHERE c.fid.fid = :fid")
        ,@NamedQuery(name = "Consumption.findByFoodNameAndUId", query = "SELECT c FROM Consumption c WHERE c.fid.fname = :fname AND c.userid.userid = :userid")})
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONSUMPTIONID")
    private Integer consumptionid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private AppUser userid;
    @JoinColumn(name = "FID", referencedColumnName = "FID")
    @ManyToOne(optional = false)
    private Food fid;

    public Consumption() {
    }

    public Consumption(Integer consumptionid) {
        this.consumptionid = consumptionid;
    }

    public Consumption(Integer consumptionid, Date date, int quantity) {
        this.consumptionid = consumptionid;
        this.date = date;
        this.quantity = quantity;
    }

    public Integer getConsumptionid() {
        return consumptionid;
    }

    public void setConsumptionid(Integer consumptionid) {
        this.consumptionid = consumptionid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AppUser getUserid() {
        return userid;
    }

    public void setUserid(AppUser userid) {
        this.userid = userid;
    }

    public Food getFid() {
        return fid;
    }

    public void setFid(Food fid) {
        this.fid = fid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consumptionid != null ? consumptionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.consumptionid == null && other.consumptionid != null) || (this.consumptionid != null && !this.consumptionid.equals(other.consumptionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restws.Consumption[ consumptionid=" + consumptionid + " ]";
    }
    
    public int totalCaloriesConsumed()
            {
                int caloriesConsumed= 0;
                
                caloriesConsumed = (this.fid.getCalorie().intValue()* this.fid.getServingamount().intValue() * this.quantity);
                System.out.println(caloriesConsumed);
                return caloriesConsumed;
            }
    
}
