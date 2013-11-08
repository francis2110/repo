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
@Table(name = "transportista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transportista.findAll", query = "SELECT t FROM Transportista t"),
    @NamedQuery(name = "Transportista.findByNif", query = "SELECT t FROM Transportista t WHERE t.nif = :nif"),
    @NamedQuery(name = "Transportista.findByNombre", query = "SELECT t FROM Transportista t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Transportista.findByApellido1", query = "SELECT t FROM Transportista t WHERE t.apellido1 = :apellido1"),
    @NamedQuery(name = "Transportista.findByApellido2", query = "SELECT t FROM Transportista t WHERE t.apellido2 = :apellido2"),
    @NamedQuery(name = "Transportista.findByMatriculaTractora", query = "SELECT t FROM Transportista t WHERE t.matriculaTractora = :matriculaTractora"),
    @NamedQuery(name = "Transportista.findByMatriculaRemolque", query = "SELECT t FROM Transportista t WHERE t.matriculaRemolque = :matriculaRemolque"),
    @NamedQuery(name = "Transportista.findByDirecci\u00f3n", query = "SELECT t FROM Transportista t WHERE t.direcci\u00f3n = :direcci\u00f3n"),
    @NamedQuery(name = "Transportista.findByValoraci\u00f3n", query = "SELECT t FROM Transportista t WHERE t.valoraci\u00f3n = :valoraci\u00f3n"),
    @NamedQuery(name = "Transportista.findByEmail", query = "SELECT t FROM Transportista t WHERE t.email = :email"),
    @NamedQuery(name = "Transportista.findByTelefono", query = "SELECT t FROM Transportista t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Transportista.findByNumCuenta", query = "SELECT t FROM Transportista t WHERE t.numCuenta = :numCuenta"),
    @NamedQuery(name = "Transportista.containsName", query = "SELECT t FROM  Transportista t WHERE t.nombre LIKE :nombre"),
    @NamedQuery(name = "Transportista.findByNameAndSurname", query = "SELECT t FROM Transportista t WHERE t.nombre LIKE :nombre AND t.apellido1 LIKE :apellido1 AND t.apellido2 LIKE :apellido2")
})
public class Transportista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NIF")
    private String nif;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;
    @Column(name = "matricula_tractora")
    private String matriculaTractora;
    @Column(name = "matricula_remolque")
    private String matriculaRemolque;
    @Column(name = "direcci\u00f3n")
    private String dirección;
    @Column(name = "valoraci\u00f3n")
    private Integer valoración;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "numCuenta")
    private String numCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transportista")
    private Collection<Viaje> viajeCollection;

    public Transportista() {
    }

    public Transportista(String nif) {
        this.nif = nif;
    }

    public Transportista(String nif, String nombre, String apellido1) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getMatriculaTractora() {
        return matriculaTractora;
    }

    public void setMatriculaTractora(String matriculaTractora) {
        this.matriculaTractora = matriculaTractora;
    }

    public String getMatriculaRemolque() {
        return matriculaRemolque;
    }

    public void setMatriculaRemolque(String matriculaRemolque) {
        this.matriculaRemolque = matriculaRemolque;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public Integer getValoración() {
        return valoración;
    }

    public void setValoración(Integer valoración) {
        this.valoración = valoración;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
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
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transportista)) {
            return false;
        }
        Transportista other = (Transportista) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.Transportista[ nif=" + nif + " ]";
    }
}
