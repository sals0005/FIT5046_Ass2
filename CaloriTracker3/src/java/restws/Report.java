/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByReportid", query = "SELECT r FROM Report r WHERE r.reportid = :reportid")
    , @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date")
    , @NamedQuery(name = "Report.findByTotalcaloriesconsumed", query = "SELECT r FROM Report r WHERE r.totalcaloriesconsumed = :totalcaloriesconsumed")
    , @NamedQuery(name = "Report.findByTotalcaloriesburned", query = "SELECT r FROM Report r WHERE r.totalcaloriesburned = :totalcaloriesburned")
    , @NamedQuery(name = "Report.findByTotalstepstaken", query = "SELECT r FROM Report r WHERE r.totalstepstaken = :totalstepstaken")
    , @NamedQuery(name = "Report.findByTotalcaloriegoal", query = "SELECT r FROM Report r WHERE r.totalcaloriegoal = :totalcaloriegoal")
     ,@NamedQuery(name = "Report.findByUser", query = "SELECT r FROM Report r WHERE r.userid.userid = :userid")
     })
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPORTID")
    private Integer reportid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALCALORIESCONSUMED")
    private BigDecimal totalcaloriesconsumed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALCALORIESBURNED")
    private BigDecimal totalcaloriesburned;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALSTEPSTAKEN")
    private int totalstepstaken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALCALORIEGOAL")
    private BigDecimal totalcaloriegoal;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private AppUser userid;

    public Report() {
    }

    public Report(Integer reportid) {
        this.reportid = reportid;
    }

    public Report(Integer reportid, Date date, BigDecimal totalcaloriesconsumed, BigDecimal totalcaloriesburned, int totalstepstaken, BigDecimal totalcaloriegoal) {
        this.reportid = reportid;
        this.date = date;
        this.totalcaloriesconsumed = totalcaloriesconsumed;
        this.totalcaloriesburned = totalcaloriesburned;
        this.totalstepstaken = totalstepstaken;
        this.totalcaloriegoal = totalcaloriegoal;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalcaloriesconsumed() {
        return totalcaloriesconsumed;
    }

    public void setTotalcaloriesconsumed(BigDecimal totalcaloriesconsumed) {
        this.totalcaloriesconsumed = totalcaloriesconsumed;
    }

    public BigDecimal getTotalcaloriesburned() {
        return totalcaloriesburned;
    }

    public void setTotalcaloriesburned(BigDecimal totalcaloriesburned) {
        this.totalcaloriesburned = totalcaloriesburned;
    }

    public int getTotalstepstaken() {
        return totalstepstaken;
    }

    public void setTotalstepstaken(int totalstepstaken) {
        this.totalstepstaken = totalstepstaken;
    }

    public BigDecimal getTotalcaloriegoal() {
        return totalcaloriegoal;
    }

    public void setTotalcaloriegoal(BigDecimal totalcaloriegoal) {
        this.totalcaloriegoal = totalcaloriegoal;
    }

    public AppUser getUserid() {
        return userid;
    }

    public void setUserid(AppUser userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportid != null ? reportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportid == null && other.reportid != null) || (this.reportid != null && !this.reportid.equals(other.reportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restws.Report[ reportid=" + reportid + " ]";
    }
    
}
