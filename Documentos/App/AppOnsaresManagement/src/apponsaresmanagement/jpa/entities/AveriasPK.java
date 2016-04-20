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
public class AveriasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idAveria")
    private int idAveria;
    @Basic(optional = false)
    @Column(name = "Remolque_matricula")
    private String remolquematricula;

    public AveriasPK() {
    }

    public AveriasPK(int idAveria, String remolquematricula) {
        this.idAveria = idAveria;
        this.remolquematricula = remolquematricula;
    }

    public int getIdAveria() {
        return idAveria;
    }

    public void setIdAveria(int idAveria) {
        this.idAveria = idAveria;
    }

    public String getRemolquematricula() {
        return remolquematricula;
    }

    public void setRemolquematricula(String remolquematricula) {
        this.remolquematricula = remolquematricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAveria;
        hash += (remolquematricula != null ? remolquematricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AveriasPK)) {
            return false;
        }
        AveriasPK other = (AveriasPK) object;
        if (this.idAveria != other.idAveria) {
            return false;
        }
        if ((this.remolquematricula == null && other.remolquematricula != null) || (this.remolquematricula != null && !this.remolquematricula.equals(other.remolquematricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.AveriasPK[ idAveria=" + idAveria + ", remolquematricula=" + remolquematricula + " ]";
    }
    
}
