/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author fran
 */
@Embeddable
public class ContratoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idContrato")
    private int idContrato;
    @Basic(optional = false)
    @Column(name = "transportista_NIF")
    private String transportistaNIF;

    public ContratoPK() {
    }

    public ContratoPK(int idContrato, String transportistaNIF) {
        this.idContrato = idContrato;
        this.transportistaNIF = transportistaNIF;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getTransportistaNIF() {
        return transportistaNIF;
    }

    public void setTransportistaNIF(String transportistaNIF) {
        this.transportistaNIF = transportistaNIF;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idContrato;
        hash += (transportistaNIF != null ? transportistaNIF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoPK)) {
            return false;
        }
        ContratoPK other = (ContratoPK) object;
        if (this.idContrato != other.idContrato) {
            return false;
        }
        if ((this.transportistaNIF == null && other.transportistaNIF != null) || (this.transportistaNIF != null && !this.transportistaNIF.equals(other.transportistaNIF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.ContratoPK[ idContrato=" + idContrato + ", transportistaNIF=" + transportistaNIF + " ]";
    }
    
}
