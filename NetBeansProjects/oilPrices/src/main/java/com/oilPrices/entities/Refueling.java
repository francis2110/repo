/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author fran
 */
@Entity
@Table(name = "refueling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refueling.findAll", query = "SELECT r FROM Refueling r"),
    @NamedQuery(name = "Refueling.findByIdrefueling", query = "SELECT r FROM Refueling r WHERE r.idrefueling = :idrefueling"),
    @NamedQuery(name = "Refueling.findByRefuelingDate", query = "SELECT r FROM Refueling r WHERE r.refuelingDate = :refuelingDate"),
    @NamedQuery(name = "Refueling.findByKilometers", query = "SELECT r FROM Refueling r WHERE r.kilometers = :kilometers"),
    @NamedQuery(name = "Refueling.findByPrice", query = "SELECT r FROM Refueling r WHERE r.price = :price"),
    @NamedQuery(name = "Refueling.findByLiters", query = "SELECT r FROM Refueling r WHERE r.liters = :liters")})
public class Refueling implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrefueling")
    private Integer idrefueling;
    @Basic(optional = false)
    @NotNull
    @Column(name = "refuelingDate")
    @Temporal(TemporalType.DATE)
    private Date refuelingDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "kilometers")
    private BigDecimal kilometers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liters")
    private BigDecimal liters;
    @JoinColumns({
        @JoinColumn(name = "cars_matricula", referencedColumnName = "matricula"),
        @JoinColumn(name = "cars_users_userID", referencedColumnName = "users_userID")})
    @ManyToOne(optional = false)
    private Cars cars;

    public Refueling() {
    }

    public Refueling(Integer idrefueling) {
        this.idrefueling = idrefueling;
    }

    public Refueling(Integer idrefueling, Date refuelingDate, BigDecimal kilometers, BigDecimal price, BigDecimal liters) {
        this.idrefueling = idrefueling;
        this.refuelingDate = refuelingDate;
        this.kilometers = kilometers;
        this.price = price;
        this.liters = liters;
    }

    public Integer getIdrefueling() {
        return idrefueling;
    }

    public void setIdrefueling(Integer idrefueling) {
        this.idrefueling = idrefueling;
    }

    public Date getRefuelingDate() {
        return refuelingDate;
    }

    public void setRefuelingDate(Date refuelingDate) {
        this.refuelingDate = refuelingDate;
    }

    public BigDecimal getKilometers() {
        return kilometers;
    }

    public void setKilometers(BigDecimal kilometers) {
        this.kilometers = kilometers;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLiters() {
        return liters;
    }

    public void setLiters(BigDecimal liters) {
        this.liters = liters;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrefueling != null ? idrefueling.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refueling)) {
            return false;
        }
        Refueling other = (Refueling) object;
        if ((this.idrefueling == null && other.idrefueling != null) || (this.idrefueling != null && !this.idrefueling.equals(other.idrefueling))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.Refueling[ idrefueling=" + idrefueling + " ]";
    }
    
}
