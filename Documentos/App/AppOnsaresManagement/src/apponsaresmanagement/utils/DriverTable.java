/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.utils;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author carmen
 */
public class DriverTable {

    private SimpleStringProperty driverName, driverSurname, email;
    
    public DriverTable(String driverName, String driverSurname, String email) {
        this.driverName.set(driverName);
        this.driverSurname.set(driverSurname);
        this.email.set(email);
    }
    
    public SimpleStringProperty getDriverName() {
        return driverName;
    }
    
    public void setDriverName(SimpleStringProperty driverName) {
        this.driverName = driverName;
    }
    
    public SimpleStringProperty getDriverSurname() {
        return driverSurname;
    }
    
    public void setDriverSurname(SimpleStringProperty driverSurname) {
        this.driverSurname = driverSurname;
    }
    
    public SimpleStringProperty getEmail() {
        return email;
    }
    
    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }
}
