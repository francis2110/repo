/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.utils;

import apponsaresmanagement.jpa.entities.Viaje;

/**
 *
 * @author fran
 */
public class viajeTabla extends Viaje{
    private String clientName, driverName, driverSurname1, driverSurname2;
    private Viaje viaje;

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSurname1() {
        return driverSurname1;
    }

    public void setDriverSurname1(String driverSurname1) {
        this.driverSurname1 = driverSurname1;
    }

    public String getDriverSurname2() {
        return driverSurname2;
    }

    public void setDriverSurname2(String driverSurname2) {
        this.driverSurname2 = driverSurname2;
    }
    
}
