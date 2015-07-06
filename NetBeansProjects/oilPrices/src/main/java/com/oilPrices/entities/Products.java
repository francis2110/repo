/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByIdProducts", query = "SELECT p FROM Products p WHERE p.productsPK.idProducts = :idProducts"),
    @NamedQuery(name = "Products.findByDateAdded", query = "SELECT p FROM Products p WHERE p.dateAdded = :dateAdded"),
    @NamedQuery(name = "Products.findByNombre", query = "SELECT p FROM Products p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Products.findByOilStationidOilStation", query = "SELECT p FROM Products p WHERE p.productsPK.oilStationidOilStation = :oilStationidOilStation")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductsPK productsPK;
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Collection<Precios> preciosCollection;
    @JoinColumn(name = "oilStation_idOilStation", referencedColumnName = "idOilStation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OilStation oilStation;

    public Products() {
    }

    public Products(ProductsPK productsPK) {
        this.productsPK = productsPK;
    }

    public Products(int idProducts, int oilStationidOilStation) {
        this.productsPK = new ProductsPK(idProducts, oilStationidOilStation);
    }

    public ProductsPK getProductsPK() {
        return productsPK;
    }

    public void setProductsPK(ProductsPK productsPK) {
        this.productsPK = productsPK;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Precios> getPreciosCollection() {
        return preciosCollection;
    }

    public void setPreciosCollection(Collection<Precios> preciosCollection) {
        this.preciosCollection = preciosCollection;
    }

    public OilStation getOilStation() {
        return oilStation;
    }

    public void setOilStation(OilStation oilStation) {
        this.oilStation = oilStation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productsPK != null ? productsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productsPK == null && other.productsPK != null) || (this.productsPK != null && !this.productsPK.equals(other.productsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.Products[ productsPK=" + productsPK + " ]";
    }
    
}
