/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "updatePricesDate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UpdatePricesDate.findAll", query = "SELECT u FROM UpdatePricesDate u"),
    @NamedQuery(name = "UpdatePricesDate.findByIdDate", query = "SELECT u FROM UpdatePricesDate u WHERE u.idDate = :idDate")})
public class UpdatePricesDate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDate")
    @Temporal(TemporalType.DATE)
    private Date idDate;

    public UpdatePricesDate() {
    }

    public UpdatePricesDate(Date idDate) {
        this.idDate = idDate;
    }

    public Date getIdDate() {
        return idDate;
    }

    public void setIdDate(Date idDate) {
        this.idDate = idDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDate != null ? idDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UpdatePricesDate)) {
            return false;
        }
        UpdatePricesDate other = (UpdatePricesDate) object;
        if ((this.idDate == null && other.idDate != null) || (this.idDate != null && !this.idDate.equals(other.idDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.UpdatePricesDate[ idDate=" + idDate + " ]";
    }
    
}
