/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "remolque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remolque.findAll", query = "SELECT r FROM Remolque r"),
    @NamedQuery(name = "Remolque.findByMatricula", query = "SELECT r FROM Remolque r WHERE r.matricula = :matricula"),
    @NamedQuery(name = "Remolque.findByItv", query = "SELECT r FROM Remolque r WHERE r.itv = :itv"),
    @NamedQuery(name = "Remolque.findByEstado", query = "SELECT r FROM Remolque r WHERE r.estado = :estado"),
    @NamedQuery(name = "Remolque.findByNumAverias", query = "SELECT r FROM Remolque r WHERE r.numAverias = :numAverias"),
    @NamedQuery(name = "Remolque.findByFechaRemolque", query = "SELECT r FROM Remolque r WHERE r.fechaRemolque = :fechaRemolque"),
    @NamedQuery(name = "Remolque.findByFechaCompra", query = "SELECT r FROM Remolque r WHERE r.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "Remolque.findByPrecioCompra", query = "SELECT r FROM Remolque r WHERE r.precioCompra = :precioCompra"),
    @NamedQuery(name = "Remolque.findByPrecioVenta", query = "SELECT r FROM Remolque r WHERE r.precioVenta = :precioVenta"),
    @NamedQuery(name = "Remolque.findByPrecioAlquiler", query = "SELECT r FROM Remolque r WHERE r.precioAlquiler = :precioAlquiler")})
public class Remolque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "itv")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itv;
    @Column(name = "estado")
    private String estado;
    @Column(name = "num_averias")
    private Integer numAverias;
    @Column(name = "fechaRemolque")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRemolque;
    @Column(name = "fechaCompra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioCompra")
    private BigDecimal precioCompra;
    @Column(name = "precioVenta")
    private BigDecimal precioVenta;
    @Column(name = "precioAlquiler")
    private BigDecimal precioAlquiler;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remolquematricula")
    private Collection<Mantenimiento> mantenimientoCollection;

    public Remolque() {
    }

    public Remolque(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getItv() {
        return itv;
    }

    public void setItv(Date itv) {
        this.itv = itv;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumAverias() {
        return numAverias;
    }

    public void setNumAverias(Integer numAverias) {
        this.numAverias = numAverias;
    }

    public Date getFechaRemolque() {
        return fechaRemolque;
    }

    public void setFechaRemolque(Date fechaRemolque) {
        this.fechaRemolque = fechaRemolque;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(BigDecimal precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    @XmlTransient
    public Collection<Mantenimiento> getMantenimientoCollection() {
        return mantenimientoCollection;
    }

    public void setMantenimientoCollection(Collection<Mantenimiento> mantenimientoCollection) {
        this.mantenimientoCollection = mantenimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remolque)) {
            return false;
        }
        Remolque other = (Remolque) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.Remolque[ matricula=" + matricula + " ]";
    }
    
}
