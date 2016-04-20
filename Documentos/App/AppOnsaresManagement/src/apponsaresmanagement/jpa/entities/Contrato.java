/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByIdContrato", query = "SELECT c FROM Contrato c WHERE c.contratoPK.idContrato = :idContrato"),
    @NamedQuery(name = "Contrato.findByEstadoContrato", query = "SELECT c FROM Contrato c WHERE c.estadoContrato = :estadoContrato"),
    @NamedQuery(name = "Contrato.findByInicioAlquiler", query = "SELECT c FROM Contrato c WHERE c.inicioAlquiler = :inicioAlquiler"),
    @NamedQuery(name = "Contrato.findByFinAlquiler", query = "SELECT c FROM Contrato c WHERE c.finAlquiler = :finAlquiler"),
    @NamedQuery(name = "Contrato.findByMesesRetraso", query = "SELECT c FROM Contrato c WHERE c.mesesRetraso = :mesesRetraso"),
    @NamedQuery(name = "Contrato.findByDeuda", query = "SELECT c FROM Contrato c WHERE c.deuda = :deuda"),
    @NamedQuery(name = "Contrato.findByCantidadPagada", query = "SELECT c FROM Contrato c WHERE c.cantidadPagada = :cantidadPagada"),
    @NamedQuery(name = "Contrato.findByTransportistaNIF", query = "SELECT c FROM Contrato c WHERE c.contratoPK.transportistaNIF = :transportistaNIF")})
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratoPK contratoPK;
    @Basic(optional = false)
    @Column(name = "estadoContrato")
    private String estadoContrato;
    @Basic(optional = false)
    @Column(name = "inicio_alquiler")
    @Temporal(TemporalType.DATE)
    private Date inicioAlquiler;
    @Column(name = "fin_alquiler")
    @Temporal(TemporalType.DATE)
    private Date finAlquiler;
    @Column(name = "meses_retraso")
    private Integer mesesRetraso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "deuda")
    private BigDecimal deuda;
    @Column(name = "cantidadPagada")
    private BigDecimal cantidadPagada;
    @JoinColumn(name = "transportista_NIF", referencedColumnName = "NIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transportista transportista;
    @JoinColumn(name = "remolque_matricula", referencedColumnName = "matricula")
    @ManyToOne(optional = false)
    private Remolque remolqueMatricula;

    public Contrato() {
    }

    public Contrato(ContratoPK contratoPK) {
        this.contratoPK = contratoPK;
    }

    public Contrato(ContratoPK contratoPK, String estadoContrato, Date inicioAlquiler) {
        this.contratoPK = contratoPK;
        this.estadoContrato = estadoContrato;
        this.inicioAlquiler = inicioAlquiler;
    }

    public Contrato(int idContrato, String transportistaNIF) {
        this.contratoPK = new ContratoPK(idContrato, transportistaNIF);
    }

    public ContratoPK getContratoPK() {
        return contratoPK;
    }

    public void setContratoPK(ContratoPK contratoPK) {
        this.contratoPK = contratoPK;
    }

    public String getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    public Date getInicioAlquiler() {
        return inicioAlquiler;
    }

    public void setInicioAlquiler(Date inicioAlquiler) {
        this.inicioAlquiler = inicioAlquiler;
    }

    public Date getFinAlquiler() {
        return finAlquiler;
    }

    public void setFinAlquiler(Date finAlquiler) {
        this.finAlquiler = finAlquiler;
    }

    public Integer getMesesRetraso() {
        return mesesRetraso;
    }

    public void setMesesRetraso(Integer mesesRetraso) {
        this.mesesRetraso = mesesRetraso;
    }

    public BigDecimal getDeuda() {
        return deuda;
    }

    public void setDeuda(BigDecimal deuda) {
        this.deuda = deuda;
    }

    public BigDecimal getCantidadPagada() {
        return cantidadPagada;
    }

    public void setCantidadPagada(BigDecimal cantidadPagada) {
        this.cantidadPagada = cantidadPagada;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public Remolque getRemolqueMatricula() {
        return remolqueMatricula;
    }

    public void setRemolqueMatricula(Remolque remolqueMatricula) {
        this.remolqueMatricula = remolqueMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratoPK != null ? contratoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.contratoPK == null && other.contratoPK != null) || (this.contratoPK != null && !this.contratoPK.equals(other.contratoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.Contrato[ contratoPK=" + contratoPK + " ]";
    }
    
}
