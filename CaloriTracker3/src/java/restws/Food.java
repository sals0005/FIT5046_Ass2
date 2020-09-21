/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sarah
 */
@Entity
@Table(name = "FOOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
    , @NamedQuery(name = "Food.findByFid", query = "SELECT f FROM Food f WHERE f.fid = :fid")
    , @NamedQuery(name = "Food.findByFname", query = "SELECT f FROM Food f WHERE f.fname = :fname")
    , @NamedQuery(name = "Food.findByFcategory", query = "SELECT f FROM Food f WHERE f.fcategory = :fcategory")
    , @NamedQuery(name = "Food.findByCalorie", query = "SELECT f FROM Food f WHERE f.calorie = :calorie")
    , @NamedQuery(name = "Food.findByServingunit", query = "SELECT f FROM Food f WHERE f.servingunit = :servingunit")
    , @NamedQuery(name = "Food.findByServingamount", query = "SELECT f FROM Food f WHERE f.servingamount = :servingamount")
    , @NamedQuery(name = "Food.findByFat", query = "SELECT f FROM Food f WHERE f.fat = :fat")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FID")
    private Integer fid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FCATEGORY")
    private String fcategory;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALORIE")
    private BigDecimal calorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SERVINGUNIT")
    private String servingunit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERVINGAMOUNT")
    private BigDecimal servingamount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAT")
    private BigDecimal fat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fid")
    private Collection<Consumption> consumptionCollection;

    public Food() {
    }

    public Food(Integer fid) {
        this.fid = fid;
    }

    public Food(Integer fid, String fname, String fcategory, BigDecimal calorie, String servingunit, BigDecimal servingamount, BigDecimal fat) {
        this.fid = fid;
        this.fname = fname;
        this.fcategory = fcategory;
        this.calorie = calorie;
        this.servingunit = servingunit;
        this.servingamount = servingamount;
        this.fat = fat;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFcategory() {
        return fcategory;
    }

    public void setFcategory(String fcategory) {
        this.fcategory = fcategory;
    }

    public BigDecimal getCalorie() {
        return calorie;
    }

    public void setCalorie(BigDecimal calorie) {
        this.calorie = calorie;
    }

    public String getServingunit() {
        return servingunit;
    }

    public void setServingunit(String servingunit) {
        this.servingunit = servingunit;
    }

    public BigDecimal getServingamount() {
        return servingamount;
    }

    public void setServingamount(BigDecimal servingamount) {
        this.servingamount = servingamount;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restws.Food[ fid=" + fid + " ]";
    }
    
}
