/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "averias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Averias.findAll", query = "SELECT a FROM Averias a"),
    @NamedQuery(name = "Averias.findByIdAveria", query = "SELECT a FROM Averias a WHERE a.averiasPK.idAveria = :idAveria"),
    @NamedQuery(name = "Averias.findByFecha", query = "SELECT a FROM Averias a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Averias.findByPrecio", query = "SELECT a FROM Averias a WHERE a.precio = :precio"),
    @NamedQuery(name = "Averias.findByTipoAveria", query = "SELECT a FROM Averias a WHERE a.tipoAveria = :tipoAveria"),
    @NamedQuery(name = "Averias.findByRemolquematricula", query = "SELECT a FROM Averias a WHERE a.averiasPK.remolquematricula = :remolquematricula")})
public class Averias implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AveriasPK averiasPK;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Precio")
    private BigDecimal precio;
    @Column(name = "tipo_averia")
    private String tipoAveria;
    @JoinColumn(name = "Remolque_matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Remolque remolque;

    public Averias() {
    }

    public Averias(AveriasPK averiasPK) {
        this.averiasPK = averiasPK;
    }

    public Averias(int idAveria, String remolquematricula) {
        this.averiasPK = new AveriasPK(idAveria, remolquematricula);
    }

    public AveriasPK getAveriasPK() {
        return averiasPK;
    }

    public void setAveriasPK(AveriasPK averiasPK) {
        this.averiasPK = averiasPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getTipoAveria() {
        return tipoAveria;
    }

    public void setTipoAveria(String tipoAveria) {
        this.tipoAveria = tipoAveria;
    }

    public Remolque getRemolque() {
        return remolque;
    }

    public void setRemolque(Remolque remolque) {
        this.remolque = remolque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (averiasPK != null ? averiasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Averias)) {
            return false;
        }
        Averias other = (Averias) object;
        if ((this.averiasPK == null && other.averiasPK != null) || (this.averiasPK != null && !this.averiasPK.equals(other.averiasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.Averias[ averiasPK=" + averiasPK + " ]";
    }
    
}
