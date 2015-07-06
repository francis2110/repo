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
import javax.validation.constraints.Size;

/**
 *
 * @author fran
 */
@Embeddable
public class CarsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "users_userID")
    private int usersuserID;

    public CarsPK() {
    }

    public CarsPK(String matricula, int usersuserID) {
        this.matricula = matricula;
        this.usersuserID = usersuserID;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getUsersuserID() {
        return usersuserID;
    }

    public void setUsersuserID(int usersuserID) {
        this.usersuserID = usersuserID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        hash += (int) usersuserID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarsPK)) {
            return false;
        }
        CarsPK other = (CarsPK) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        if (this.usersuserID != other.usersuserID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.CarsPK[ matricula=" + matricula + ", usersuserID=" + usersuserID + " ]";
    }
    
}
