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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "precios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Precios.findAll", query = "SELECT p FROM Precios p"),
    @NamedQuery(name = "Precios.findByIdprecios", query = "SELECT p FROM Precios p WHERE p.preciosPK.idprecios = :idprecios"),
    @NamedQuery(name = "Precios.findByUpdateDate", query = "SELECT p FROM Precios p WHERE p.updateDate = :updateDate"),
    @NamedQuery(name = "Precios.findByValor", query = "SELECT p FROM Precios p WHERE p.valor = :valor"),
    @NamedQuery(name = "Precios.findByProductsidProducts", query = "SELECT p FROM Precios p WHERE p.preciosPK.productsidProducts = :productsidProducts"),
    @NamedQuery(name = "Precios.findByProductsoilStationidOilStation", query = "SELECT p FROM Precios p WHERE p.preciosPK.productsoilStationidOilStation = :productsoilStationidOilStation"),
@NamedQuery(name = "Precios.findByDateandOilStation", query = "SELECT p FROM Precios p WHERE p.preciosPK.productsoilStationidOilStation=:productsoilStationidOilStation AND p.updateDate = :updateDate"),
 @NamedQuery(name="Precios.findProductsByDate",query="SELECT p FROM Precios p  WHERE p.preciosPK.productsoilStationidOilStation=:productsoilStationidOilStation AND p.updateDate=:updateDate AND p.products.nombre=:prodName")})
public class Precios implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreciosPK preciosPK;
    @Column(name = "updateDate")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
     @Max(value=3,message = "max value of decimals passed")
    @Min(value=3, message = "min value of decimals not allowed")//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumns({
        @JoinColumn(name = "products_idProducts", referencedColumnName = "idProducts", insertable = false, updatable = false),
        @JoinColumn(name = "products_oilStation_idOilStation", referencedColumnName = "oilStation_idOilStation", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Products products;

    public Precios() {
    }

    public Precios(PreciosPK preciosPK) {
        this.preciosPK = preciosPK;
    }

    public Precios(PreciosPK preciosPK, BigDecimal valor) {
        this.preciosPK = preciosPK;
        this.valor = valor;
    }

    public Precios(int idprecios, int productsidProducts, int productsoilStationidOilStation) {
        this.preciosPK = new PreciosPK(idprecios, productsidProducts, productsoilStationidOilStation);
    }

    public PreciosPK getPreciosPK() {
        return preciosPK;
    }

    public void setPreciosPK(PreciosPK preciosPK) {
        this.preciosPK = preciosPK;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preciosPK != null ? preciosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Precios)) {
            return false;
        }
        Precios other = (Precios) object;
        if ((this.preciosPK == null && other.preciosPK != null) || (this.preciosPK != null && !this.preciosPK.equals(other.preciosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.Precios[ preciosPK=" + preciosPK + " ]";
    }
    
}
