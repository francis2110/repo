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
public class ProductsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idProducts")
    private int idProducts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "oilStation_idOilStation")
    private int oilStationidOilStation;

    public ProductsPK() {
    }

    public ProductsPK(int idProducts, int oilStationidOilStation) {
        this.idProducts = idProducts;
        this.oilStationidOilStation = oilStationidOilStation;
    }

    public int getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(int idProducts) {
        this.idProducts = idProducts;
    }

    public int getOilStationidOilStation() {
        return oilStationidOilStation;
    }

    public void setOilStationidOilStation(int oilStationidOilStation) {
        this.oilStationidOilStation = oilStationidOilStation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducts;
        hash += (int) oilStationidOilStation;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsPK)) {
            return false;
        }
        ProductsPK other = (ProductsPK) object;
        if (this.idProducts != other.idProducts) {
            return false;
        }
        if (this.oilStationidOilStation != other.oilStationidOilStation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.ProductsPK[ idProducts=" + idProducts + ", oilStationidOilStation=" + oilStationidOilStation + " ]";
    }
    
}
