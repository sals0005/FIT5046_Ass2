/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restws;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sarah
 */
@Entity
@Table(name = "APP_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a")
    , @NamedQuery(name = "AppUser.findByUserid", query = "SELECT a FROM AppUser a WHERE a.userid = :userid")
    , @NamedQuery(name = "AppUser.findByName", query = "SELECT a FROM AppUser a WHERE a.name = :name")
    , @NamedQuery(name = "AppUser.findBySurename", query = "SELECT a FROM AppUser a WHERE a.surename = :surename")
    , @NamedQuery(name = "AppUser.findByEmail", query = "SELECT a FROM AppUser a WHERE a.email = :email")
    , @NamedQuery(name = "AppUser.findByDob", query = "SELECT a FROM AppUser a WHERE a.dob = :dob")
    , @NamedQuery(name = "AppUser.findByHeight", query = "SELECT a FROM AppUser a WHERE a.height = :height")
    , @NamedQuery(name = "AppUser.findByWeight", query = "SELECT a FROM AppUser a WHERE a.weight = :weight")
    , @NamedQuery(name = "AppUser.findByAddress", query = "SELECT a FROM AppUser a WHERE a.address = :address")
    , @NamedQuery(name = "AppUser.findByPostcode", query = "SELECT a FROM AppUser a WHERE a.postcode = :postcode")
    , @NamedQuery(name = "AppUser.findByActivitylevel", query = "SELECT a FROM AppUser a WHERE a.activitylevel = :activitylevel")
    , @NamedQuery(name = "AppUser.findByStepspermile", query = "SELECT a FROM AppUser a WHERE a.stepspermile = :stepspermile")
    , @NamedQuery(name = "AppUser.findByGender", query = "SELECT a FROM AppUser a WHERE a.gender = :gender")
   })
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "SURENAME")
    private String surename;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "HEIGHT")
    private BigDecimal height;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Size(max = 200)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "POSTCODE")
    private Integer postcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVITYLEVEL")
    private int activitylevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STEPSPERMILE")
    private int stepspermile;
    @Size(max = 10)
    @Column(name = "GENDER")
    private String gender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Credential> credentialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Report> reportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Consumption> consumptionCollection;

    public AppUser() {
    }

    public AppUser(Integer userid) {
        this.userid = userid;
    }

    public AppUser(Integer userid, String name, String surename, String email, Date dob, BigDecimal height, BigDecimal weight, int activitylevel, int stepspermile) {
        this.userid = userid;
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.activitylevel = activitylevel;
        this.stepspermile = stepspermile;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public int getActivitylevel() {
        return activitylevel;
    }

    public void setActivitylevel(int activitylevel) {
        this.activitylevel = activitylevel;
    }

    public int getStepspermile() {
        return stepspermile;
    }

    public void setStepspermile(int stepspermile) {
        this.stepspermile = stepspermile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

    @XmlTransient
    public Collection<Credential> getCredentialCollection() {
        return credentialCollection;
    }

    public void setCredentialCollection(Collection<Credential> credentialCollection) {
        this.credentialCollection = credentialCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restws.AppUser[ userid=" + userid + " ]";
    }

    public double caloriesBurnedStep() {
        double caloriesBurnedPerMile = 2.20462 * this.weight.doubleValue() * 0.49;
        double caloriburned = caloriesBurnedPerMile/this.stepspermile;
        return caloriburned;   
    }
     public double getBMRAmount() {
       double sec =  Calendar.getInstance().getTime().getTime()-this.dob.getTime() ;
        int age = (int)(sec*0.001*0.0167*0.0167*0.042*0.00274);
        double bmr;
        if (this.gender.equals('M') ||this.gender.equals("Male")) {
            bmr = (13.75 * this.weight.doubleValue()) + (5.003 * this.height.doubleValue())  - (6.755 * age ) + 66.5;
            return bmr;
        } else {
            bmr = (9.563 * this.weight.doubleValue()) + (1.85 * this.height.doubleValue()) - (4.676 * age ) + 655.1;
        }
        return bmr;
    }
     
     public int calorieBurnedRest()
     {
         int calorieBurnedRest = 0;
        int activyLevel = this.activitylevel;
        double bmr = getBMRAmount();
        switch (activyLevel) {
            case 1:
                calorieBurnedRest = (int)(bmr * 1.2);
                break;
            case 2:
                calorieBurnedRest = (int)(bmr * 1.375);
                break;
            case 3:
                calorieBurnedRest = (int)(bmr * 1.55);
                break;
            case 4:
                calorieBurnedRest = (int)(bmr * 1.725);
                break;
            case 5:
                calorieBurnedRest = (int)(bmr * 1.9);
                break;
        }
        return calorieBurnedRest;
     }
    
    
}
