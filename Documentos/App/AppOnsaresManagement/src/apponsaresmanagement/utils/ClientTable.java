/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.utils;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Fran
 */
public class ClientTable {
    //private String clientName, contact, email;
    private SimpleStringProperty clientName, contact, email;

    public ClientTable(String clientName, String contact, String email) {
        this.clientName.set(clientName);
        this.contact .set(contact);
        this.email.set(email);
    }

    public SimpleStringProperty getClientName() {
        return clientName;
    }

    public void setClientName(SimpleStringProperty clientName) {
        this.clientName = clientName;
    }

    public SimpleStringProperty getContact() {
        return contact;
    }

    public void setContact(SimpleStringProperty contact) {
        this.contact = contact;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    
    
    
}
