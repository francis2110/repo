/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fran
 */
@Embeddable
public class PreciosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idprecios")
    private int idprecios;
    @Basic(optional = false)
    @NotNull
    @Column(name = "products_idProducts")
    private int productsidProducts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "products_oilStation_idOilStation")
    private int productsoilStationidOilStation;

    public PreciosPK() {
    }

    public PreciosPK(int idprecios, int productsidProducts, int productsoilStationidOilStation) {
        this.idprecios = idprecios;
        this.productsidProducts = productsidProducts;
        this.productsoilStationidOilStation = productsoilStationidOilStation;
    }

    public int getIdprecios() {
        return idprecios;
    }

    public void setIdprecios(int idprecios) {
        this.idprecios = idprecios;
    }

    public int getProductsidProducts() {
        return productsidProducts;
    }

    public void setProductsidProducts(int productsidProducts) {
        this.productsidProducts = productsidProducts;
    }

    public int getProductsoilStationidOilStation() {
        return productsoilStationidOilStation;
    }

    public void setProductsoilStationidOilStation(int productsoilStationidOilStation) {
        this.productsoilStationidOilStation = productsoilStationidOilStation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idprecios;
        hash += (int) productsidProducts;
        hash += (int) productsoilStationidOilStation;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreciosPK)) {
            return false;
        }
        PreciosPK other = (PreciosPK) object;
        if (this.idprecios != other.idprecios) {
            return false;
        }
        if (this.productsidProducts != other.productsidProducts) {
            return false;
        }
        if (this.productsoilStationidOilStation != other.productsoilStationidOilStation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.PreciosPK[ idprecios=" + idprecios + ", productsidProducts=" + productsidProducts + ", productsoilStationidOilStation=" + productsoilStationidOilStation + " ]";
    }
    
}
