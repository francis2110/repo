/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author fran
 */
@Entity
@Table(name = "cars")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Cars c"),
    @NamedQuery(name = "Cars.findByMatricula", query = "SELECT c FROM Cars c WHERE c.carsPK.matricula = :matricula"),
    @NamedQuery(name = "Cars.findByFuelType", query = "SELECT c FROM Cars c WHERE c.fuelType = :fuelType"),
    @NamedQuery(name = "Cars.findByUsersuserID", query = "SELECT c FROM Cars c WHERE c.carsPK.usersuserID = :usersuserID"),
    @NamedQuery(name = "Cars.findByCarModel", query = "SELECT c FROM Cars c WHERE c.carModel = :carModel"),
    @NamedQuery(name = "Cars.findByDateRegistered", query = "SELECT c FROM Cars c WHERE c.dateRegistered = :dateRegistered"),
    @NamedQuery(name = "Cars.findByCarDate", query = "SELECT c FROM Cars c WHERE c.carDate = :carDate")})
public class Cars implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarsPK carsPK;
    @Size(max = 45)
    @Column(name = "fuelType")
    private String fuelType;
    @Size(max = 45)
    @Column(name = "carModel")
    private String carModel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.DATE)
    private Date dateRegistered;
    @Column(name = "carDate")
    @Temporal(TemporalType.DATE)
    private Date carDate;
    @JoinColumn(name = "users_userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cars")
    private Collection<Refueling> refuelingCollection;

    public Cars() {
    }

    public Cars(CarsPK carsPK) {
        this.carsPK = carsPK;
    }

    public Cars(CarsPK carsPK, Date dateRegistered) {
        this.carsPK = carsPK;
        this.dateRegistered = dateRegistered;
    }

    public Cars(String matricula, int usersuserID) {
        this.carsPK = new CarsPK(matricula, usersuserID);
    }

    public CarsPK getCarsPK() {
        return carsPK;
    }

    public void setCarsPK(CarsPK carsPK) {
        this.carsPK = carsPK;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getCarDate() {
        return carDate;
    }

    public void setCarDate(Date carDate) {
        this.carDate = carDate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @XmlTransient
    public Collection<Refueling> getRefuelingCollection() {
        return refuelingCollection;
    }

    public void setRefuelingCollection(Collection<Refueling> refuelingCollection) {
        this.refuelingCollection = refuelingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carsPK != null ? carsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cars)) {
            return false;
        }
        Cars other = (Cars) object;
        if ((this.carsPK == null && other.carsPK != null) || (this.carsPK != null && !this.carsPK.equals(other.carsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.Cars[ carsPK=" + carsPK + " ]";
    }
   
    
}
