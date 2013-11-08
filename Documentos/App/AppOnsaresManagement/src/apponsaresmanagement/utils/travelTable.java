/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.utils;

import apponsaresmanagement.jpa.entities.Cliente;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author fran
 */
public class travelTable {

    private SimpleStringProperty ingreso, pagoACamionero, kms, numeroContenedor,
            destinatario, poblacion, tipoPago, iva, estadoCliente, estadoTransportista, transportista;
private Cliente cliente;
    public travelTable(String ingreso, String pagoACamionero, String kms, String numeroContenedor, String destinatario, String poblacion, String tipoPago, String iva, String estadoCliente, String estadoTransportista, String transportista, Cliente cliente) {
        this.ingreso.set(ingreso);
        this.pagoACamionero.set(pagoACamionero);
        this.kms.set(kms);
        this.numeroContenedor.set(numeroContenedor);
        this.destinatario.set(destinatario);
        this.poblacion.set(poblacion);
        this.tipoPago.set(tipoPago);
        this.iva.set(iva);
        this.estadoCliente.set(estadoCliente);
        this.estadoTransportista.set(estadoTransportista);
        this.transportista.set(transportista);
        this.cliente=cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public SimpleStringProperty getIngreso() {
        return ingreso;
    }

    public void setIngreso(SimpleStringProperty ingreso) {
        this.ingreso = ingreso;
    }

    public SimpleStringProperty getPagoACamionero() {
        return pagoACamionero;
    }

    public void setPagoACamionero(SimpleStringProperty pagoACamionero) {
        this.pagoACamionero = pagoACamionero;
    }

    public SimpleStringProperty getKms() {
        return kms;
    }

    public void setKms(SimpleStringProperty kms) {
        this.kms = kms;
    }

    public SimpleStringProperty getNumeroContenedor() {
        return numeroContenedor;
    }

    public void setNumeroContenedor(SimpleStringProperty numeroContenedor) {
        this.numeroContenedor = numeroContenedor;
    }



    public SimpleStringProperty getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(SimpleStringProperty destinatario) {
        this.destinatario = destinatario;
    }

    public SimpleStringProperty getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(SimpleStringProperty poblacion) {
        this.poblacion = poblacion;
    }

  
    public SimpleStringProperty getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(SimpleStringProperty tipoPago) {
        this.tipoPago = tipoPago;
    }

    public SimpleStringProperty getIva() {
        return iva;
    }

    public void setIva(SimpleStringProperty iva) {
        this.iva = iva;
    }

    public SimpleStringProperty getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(SimpleStringProperty estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public SimpleStringProperty getEstadoTransportista() {
        return estadoTransportista;
    }

    public void setEstadoTransportista(SimpleStringProperty estadoTransportista) {
        this.estadoTransportista = estadoTransportista;
    }

    public SimpleStringProperty getTransportista() {
        return transportista;
    }

    public void setTransportista(SimpleStringProperty transportista) {
        this.transportista = transportista;
    }

   
}
