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
public class ViajePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "albaran")
    private String albaran;
    @Basic(optional = false)
    @Column(name = "Cliente_CifNif")
    private String clienteCifNif;
    @Basic(optional = false)
    @Column(name = "Transportista_NIF")
    private String transportistaNIF;

    public ViajePK() {
    }

    public ViajePK(String albaran, String clienteCifNif, String transportistaNIF) {
        this.albaran = albaran;
        this.clienteCifNif = clienteCifNif;
        this.transportistaNIF = transportistaNIF;
    }

    public String getAlbaran() {
        return albaran;
    }

    public void setAlbaran(String albaran) {
        this.albaran = albaran;
    }

    public String getClienteCifNif() {
        return clienteCifNif;
    }

    public void setClienteCifNif(String clienteCifNif) {
        this.clienteCifNif = clienteCifNif;
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
        hash += (albaran != null ? albaran.hashCode() : 0);
        hash += (clienteCifNif != null ? clienteCifNif.hashCode() : 0);
        hash += (transportistaNIF != null ? transportistaNIF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViajePK)) {
            return false;
        }
        ViajePK other = (ViajePK) object;
        if ((this.albaran == null && other.albaran != null) || (this.albaran != null && !this.albaran.equals(other.albaran))) {
            return false;
        }
        if ((this.clienteCifNif == null && other.clienteCifNif != null) || (this.clienteCifNif != null && !this.clienteCifNif.equals(other.clienteCifNif))) {
            return false;
        }
        if ((this.transportistaNIF == null && other.transportistaNIF != null) || (this.transportistaNIF != null && !this.transportistaNIF.equals(other.transportistaNIF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.ViajePK[ albaran=" + albaran + ", clienteCifNif=" + clienteCifNif + ", transportistaNIF=" + transportistaNIF + " ]";
    }
    
}
