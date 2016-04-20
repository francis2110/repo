/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCifNif", query = "SELECT c FROM Cliente c WHERE c.cifNif = :cifNif"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByCalle", query = "SELECT c FROM Cliente c WHERE c.calle = :calle"),
    @NamedQuery(name = "Cliente.findByNumViajes", query = "SELECT c FROM Cliente c WHERE c.numViajes = :numViajes"),
    @NamedQuery(name = "Cliente.findByMovil", query = "SELECT c FROM Cliente c WHERE c.movil = :movil"),
    @NamedQuery(name = "Cliente.findByFijo", query = "SELECT c FROM Cliente c WHERE c.fijo = :fijo"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByNumero", query = "SELECT c FROM Cliente c WHERE c.numero = :numero"),
    @NamedQuery(name = "Cliente.findByCodigoPostal", query = "SELECT c FROM Cliente c WHERE c.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Cliente.findByPoblacion", query = "SELECT c FROM Cliente c WHERE c.poblacion = :poblacion"),
    @NamedQuery(name = "Cliente.findByProvincia", query = "SELECT c FROM Cliente c WHERE c.provincia = :provincia"),
    @NamedQuery(name = "Cliente.findByContacto", query = "SELECT c FROM Cliente c WHERE c.contacto = :contacto"),
    @NamedQuery(name = "Cliente.containsName", query = "SELECT c FROM  Cliente c WHERE c.nombre LIKE :nombre")})
public class Cliente implements Serializable {
    @Column(name = "despachante")
    private String despachante;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CifNif")
    private String cifNif;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Calle")
    private String calle;
    @Column(name = "NumViajes")
    private Integer numViajes;
    @Column(name = "movil")
    private String movil;
    @Column(name = "fijo")
    private String fijo;
    @Column(name = "email")
    private String email;
    @Column(name = "numero")
    private String numero;
    @Column(name = "codigoPostal")
    private Integer codigoPostal;
    @Column(name = "Poblacion")
    private String poblacion;
    @Column(name = "Provincia")
    private String provincia;
    @Column(name = "contacto")
    private String contacto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Viaje> viajeCollection;

    public Cliente() {
    }

    public Cliente(String cifNif) {
        this.cifNif = cifNif;
    }

    public String getCifNif() {
        return cifNif;
    }

    public void setCifNif(String cifNif) {
        this.cifNif = cifNif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumViajes() {
        return numViajes;
    }

    public void setNumViajes(Integer numViajes) {
        this.numViajes = numViajes;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getFijo() {
        return fijo;
    }

    public void setFijo(String fijo) {
        this.fijo = fijo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @XmlTransient
    public Collection<Viaje> getViajeCollection() {
        return viajeCollection;
    }

    public void setViajeCollection(Collection<Viaje> viajeCollection) {
        this.viajeCollection = viajeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cifNif != null ? cifNif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cifNif == null && other.cifNif != null) || (this.cifNif != null && !this.cifNif.equals(other.cifNif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.Cliente[ cifNif=" + cifNif + " ]";
    }

    public String getDespachante() {
        return despachante;
    }

    public void setDespachante(String despachante) {
        this.despachante = despachante;
    }
    
}
