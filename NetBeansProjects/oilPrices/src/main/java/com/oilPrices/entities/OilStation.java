/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "oilStation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OilStation.findAll", query = "SELECT o FROM OilStation o"),
    @NamedQuery(name = "OilStation.findByIdOilStation", query = "SELECT o FROM OilStation o WHERE o.idOilStation = :idOilStation"),
    @NamedQuery(name = "OilStation.findByRotulo", query = "SELECT o FROM OilStation o WHERE o.rotulo = :rotulo"),
    @NamedQuery(name = "OilStation.findByMucinipio", query = "SELECT o FROM OilStation o WHERE o.mucinipio = :mucinipio"),
    @NamedQuery(name = "OilStation.findByLocalidad", query = "SELECT o FROM OilStation o WHERE o.localidad = :localidad"),
    @NamedQuery(name = "OilStation.findByCp", query = "SELECT o FROM OilStation o WHERE o.cp = :cp"),
    @NamedQuery(name = "OilStation.findByDireccion", query = "SELECT o FROM OilStation o WHERE o.direccion = :direccion"),
    @NamedQuery(name = "OilStation.findByLongitud", query = "SELECT o FROM OilStation o WHERE o.longitud = :longitud"),
    @NamedQuery(name = "OilStation.findByLatitud", query = "SELECT o FROM OilStation o WHERE o.latitud = :latitud"),
    @NamedQuery(name = "OilStation.findByProvincia", query = "SELECT o FROM OilStation o WHERE o.provincia = :provincia"),
    @NamedQuery(name = "OilStation.findByHorario", query = "SELECT o FROM OilStation o WHERE o.horario = :horario"),
 @NamedQuery(name = "OilStation.stationExists", query = "SELECT o FROM OilStation o WHERE o.rotulo=:rotulo AND o.latitud=:latitud AND o.longitud=:longitud")})
public class OilStation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOilStation")
    private Integer idOilStation;
    @Size(max = 45)
    @Column(name = "rotulo")
    private String rotulo;
    @Size(max = 45)
    @Column(name = "Mucinipio")
    private String mucinipio;
    @Size(max = 45)
    @Column(name = "Localidad")
    private String localidad;
    @Size(max = 45)
    @Column(name = "CP")
    private String cp;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "longitud")
    private String longitud;
    @Size(max = 45)
    @Column(name = "latitud")
    private String latitud;
    @Size(max = 45)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 45)
    @Column(name = "horario")
    private String horario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oilStation")
    private Collection<Products> productsCollection;

    public OilStation() {
    }

    public OilStation(Integer idOilStation) {
        this.idOilStation = idOilStation;
    }

    public Integer getIdOilStation() {
        return idOilStation;
    }

    public void setIdOilStation(Integer idOilStation) {
        this.idOilStation = idOilStation;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getMucinipio() {
        return mucinipio;
    }

    public void setMucinipio(String mucinipio) {
        this.mucinipio = mucinipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @XmlTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOilStation != null ? idOilStation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OilStation)) {
            return false;
        }
        OilStation other = (OilStation) object;
        if ((this.idOilStation == null && other.idOilStation != null) || (this.idOilStation != null && !this.idOilStation.equals(other.idOilStation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.OilStation[ idOilStation=" + idOilStation + " ]";
    }
    
}
