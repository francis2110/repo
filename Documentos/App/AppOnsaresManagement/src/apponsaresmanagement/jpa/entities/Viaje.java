/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.jpa.entities;

import apponsaresmanagement.utils.Utils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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
@Table(name = "viaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viaje.findAll", query = "SELECT v FROM Viaje v"),
    @NamedQuery(name = "Viaje.findByAlbaran", query = "SELECT v FROM Viaje v WHERE v.viajePK.albaran = :albaran"),
    @NamedQuery(name = "Viaje.findByFecha", query = "SELECT v FROM Viaje v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Viaje.findByIngreso", query = "SELECT v FROM Viaje v WHERE v.ingreso = :ingreso"),
    @NamedQuery(name = "Viaje.findByPagoACamionero", query = "SELECT v FROM Viaje v WHERE v.pagoACamionero = :pagoACamionero"),
    @NamedQuery(name = "Viaje.findByKms", query = "SELECT v FROM Viaje v WHERE v.kms = :kms"),
    @NamedQuery(name = "Viaje.findByNumeroContenedor", query = "SELECT v FROM Viaje v WHERE v.numeroContenedor = :numeroContenedor"),
    @NamedQuery(name = "Viaje.findByTipoPago", query = "SELECT v FROM Viaje v WHERE v.tipoPago = :tipoPago"),
    @NamedQuery(name = "Viaje.findByIva", query = "SELECT v FROM Viaje v WHERE v.iva = :iva"),
    @NamedQuery(name = "Viaje.findByEstadoCliente", query = "SELECT v FROM Viaje v WHERE v.estadoCliente = :estadoCliente"),
    @NamedQuery(name = "Viaje.findByEstadoTransportista", query = "SELECT v FROM Viaje v WHERE v.estadoTransportista = :estadoTransportista"),
    @NamedQuery(name = "Viaje.findByClienteCifNif", query = "SELECT v FROM Viaje v WHERE v.viajePK.clienteCifNif = :clienteCifNif"),
    @NamedQuery(name = "Viaje.findByTransportistaNIF", query = "SELECT v FROM Viaje v WHERE v.viajePK.transportistaNIF = :transportistaNIF"),
    @NamedQuery(name = "Viaje.getYearTravels", query = "SELECT count(v) From Viaje v Where v.fecha>:initDate AND v.fecha<:endDate"),
    @NamedQuery(name = "Viaje.getClientTravelsByDate", query = "SELECT v FROM Viaje v  WHERE v.viajePK.clienteCifNif=:clientNif AND v.fecha>=:initDate AND v.fecha<=:endDate"),
    @NamedQuery(name = "Viaje.getDriverTravelsByDate", query = "SELECT v FROM Viaje v  WHERE v.viajePK.transportistaNIF=:driverNif AND v.fecha>=:initDate AND v.fecha<=:endDate"),
    @NamedQuery(name = "Viaje.getTravelsByBillCode", query = "SELECT v FROM Viaje v WHERE v.facturaidFactura.idFactura=:idFactura")})
public class Viaje implements Serializable {
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "referencia")
    private String referencia;
    @Column(name = "Peso")
    private Double peso;
    @Column(name = "despachante")
    private String despachante;
    @Column(name = "origen")
    private String origen;
    @Column(name = "destino")
    private String destino;
    @Column(name = "bultos")
    private Integer bultos;
    @Column(name = "Mercancia")
    private String mercancia;
    @Column(name = "lugarRecogida")
    private String lugarRecogida;
    @Column(name = "lugarEntrega")
    private String lugarEntrega;
    @Column(name = "lugarCarga")
    private String lugarCarga;
    @Column(name = "cargador")
    private String cargador;
    @Column(name = "horaLlegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaLlegada;
    @Column(name = "horaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSalida;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "estadoContenedor")
    private String estadoContenedor;
    @Column(name = "tipoContenedor")
    private String tipoContenedor;
    @Column(name = "naviera")
    private String naviera;
    @Column(name = "buque")
    private String buque;
    @Column(name = "tipoViaje")
    private String tipoViaje;
    @JoinColumn(name = "factura_idFactura", referencedColumnName = "idFactura")
    @ManyToOne
    private Factura facturaidFactura;
    @Column(name = "precinto")
    private String precinto;
    @Column(name = "estadoHacienda")
    private String estadoHacienda;
    @Column(name = "aduana")
    private String aduana;
    @Column(name = "gastosNaviera")
    private String gastosNaviera;
    @Column(name = "otrosGastos")
    private String otrosGastos;
    @Column(name = "cantidadOtros")
    private String cantidadOtros;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ViajePK viajePK;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ingreso")
    private BigDecimal ingreso;
    @Column(name = "pago_a_camionero")
    private BigDecimal pagoACamionero;
    @Column(name = "kms")
    private String kms;
    @Column(name = "numero_contenedor")
    private String numeroContenedor;
    @Column(name = "tipo_pago")
    private Integer tipoPago;
    @Column(name = "IVA")
    private String iva;
    @Basic(optional = false)
    @Column(name = "estado_cliente")
    private String estadoCliente;
    @Basic(optional = false)
    @Column(name = "estado_transportista")
    private String estadoTransportista;
    @JoinColumn(name = "Transportista_NIF", referencedColumnName = "NIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transportista transportista;
    @JoinColumn(name = "Cliente_CifNif", referencedColumnName = "CifNif", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Viaje() {
    }

    public Viaje(ViajePK viajePK) {
        this.viajePK = viajePK;
    }

    public Viaje(ViajePK viajePK, String estadoCliente, String estadoTransportista) {
        this.viajePK = viajePK;
        this.estadoCliente = estadoCliente;
        this.estadoTransportista = estadoTransportista;
    }

    public Viaje(String albaran, String clienteCifNif, String transportistaNIF) {
        this.viajePK = new ViajePK(albaran, clienteCifNif, transportistaNIF);
    }

    public ViajePK getViajePK() {
        return viajePK;
    }

    public void setViajePK(ViajePK viajePK) {
        this.viajePK = viajePK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraPrevista() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getFecha());
        if(cal.get(Calendar.MINUTE)<10){
        return cal.get(Calendar.HOUR_OF_DAY) + ":" +"0"+ cal.get(Calendar.MINUTE);
        }
        return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);

    }

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public BigDecimal getPagoACamionero() {
        return pagoACamionero;
    }

    public void setPagoACamionero(BigDecimal pagoACamionero) {
        this.pagoACamionero = pagoACamionero;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public String getNumeroContenedor() {
        return numeroContenedor;
    }

    public void setNumeroContenedor(String numeroContenedor) {
        this.numeroContenedor = numeroContenedor;
    }

    public Integer getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public String getEstadoTransportista() {
        return estadoTransportista;
    }

    public void setEstadoTransportista(String estadoTransportista) {
        this.estadoTransportista = estadoTransportista;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public String getDriverName() {
        return getTransportista().getNombre();
    }

    public String getDriverSurname1() {
        return getTransportista().getApellido1();
    }

    public String getDriverSurname2() {
        return getTransportista().getApellido2();
    }

    public Factura getFacturaidFactura() {
        return facturaidFactura;
    }

    public void setFacturaidFactura(Factura facturaidFactura) {
        this.facturaidFactura = facturaidFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getClientName() {
        return getCliente().getNombre();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viajePK != null ? viajePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viaje)) {
            return false;
        }
        Viaje other = (Viaje) object;
        if ((this.viajePK == null && other.viajePK != null) || (this.viajePK != null && !this.viajePK.equals(other.viajePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "apponsaresmanagement.jpa.entities.Viaje[ viajePK=" + viajePK + " ]";
    }

    public String getAduana() {
        return aduana;
    }

    public void setAduana(String aduana) {
        this.aduana = aduana;
    }

    public String getGastosNaviera() {
        return gastosNaviera;
    }

    public void setGastosNaviera(String gastosNaviera) {
        this.gastosNaviera = gastosNaviera;
    }

    public String getOtrosGastos() {
        return otrosGastos;
    }

    public void setOtrosGastos(String otrosGastos) {
        this.otrosGastos = otrosGastos;
    }

    public String getCantidadOtros() {
        return cantidadOtros;
    }

    public void setCantidadOtros(String cantidadOtros) {
        this.cantidadOtros = cantidadOtros;
    }

    public String getPrecinto() {
        return precinto;
    }

    public void setPrecinto(String precinto) {
        this.precinto = precinto;
    }

    public String getEstadoHacienda() {
        return estadoHacienda;
    }

    public void setEstadoHacienda(String estadoHacienda) {
        this.estadoHacienda = estadoHacienda;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getBultos() {
        return bultos;
    }

    public void setBultos(Integer bultos) {
        this.bultos = bultos;
    }

    public String getMercancia() {
        return mercancia;
    }

    public void setMercancia(String mercancia) {
        this.mercancia = mercancia;
    }

    public String getLugarRecogida() {
        return lugarRecogida;
    }

    public void setLugarRecogida(String lugarRecogida) {
        this.lugarRecogida = lugarRecogida;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public String getLugarCarga() {
        return lugarCarga;
    }

    public void setLugarCarga(String lugarCarga) {
        this.lugarCarga = lugarCarga;
    }

    public String getCargador() {
        return cargador;
    }

    public void setCargador(String cargador) {
        this.cargador = cargador;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoContenedor() {
        return estadoContenedor;
    }

    public void setEstadoContenedor(String estadoContenedor) {
        this.estadoContenedor = estadoContenedor;
    }

    public String getTipoContenedor() {
        return tipoContenedor;
    }

    public void setTipoContenedor(String tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }

    public String getNaviera() {
        return naviera;
    }

    public void setNaviera(String naviera) {
        this.naviera = naviera;
    }

    public String getBuque() {
        return buque;
    }

    public void setBuque(String buque) {
        this.buque = buque;
    }

    public String getDespachante() {
        return despachante;
    }

    public void setDespachante(String despachante) {
        this.despachante = despachante;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
  
}
