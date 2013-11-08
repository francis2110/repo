/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.truckDriver;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author fran <fraordia@gmail.com>
 */
public class SeeDriversDataController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private Label nameLbl;
    @FXML
    private Label firstSurnameLbl;
    @FXML
    private Label secondSurnameLbl;
    @FXML
    private Label truckRegistrationNumLbl;
    @FXML
    private Label addressLbl;
    @FXML
    private Label travelLbl;
    @FXML
    private Label kmsLbl;
    @FXML
    private Label totalIncomeLbl;
    @FXML
    private Label lastMonthIncomeLbl;
    @FXML
    private Label appreciationLbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label phoneNumberLbl;
    @FXML
    private Label nifLbl;
    @FXML
    private Font x2;
    @FXML
    private Insets x3;
    @FXML
    private Label entidadLbl;
    @FXML
    private Label officeNumberLbl;
    @FXML
    private Label controlDigitNumberLbl;
    @FXML
    private Label accountNumberLbl;
    @FXML
    private Label trailerRegistrationLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    public Label getNameLbl() {
        return nameLbl;
    }

    public void setNameLbl(Label nameLbl) {
        this.nameLbl = nameLbl;
    }

    public Label getFirstSurnameLbl() {
        return firstSurnameLbl;
    }

    public void setFirstSurnameLbl(Label firstSurnameLbl) {
        this.firstSurnameLbl = firstSurnameLbl;
    }

    public Label getSecondSurnameLbl() {
        return secondSurnameLbl;
    }

    public void setSecondSurnameLbl(Label secondSurnameLbl) {
        this.secondSurnameLbl = secondSurnameLbl;
    }

    public Label getTruckRegistrationNumLbl() {
        return truckRegistrationNumLbl;
    }

    public void setTruckRegistrationNumLbl(Label truckRegistrationNumLbl) {
        this.truckRegistrationNumLbl = truckRegistrationNumLbl;
    }

    public Label getAddressLbl() {
        return addressLbl;
    }

    public void setAddressLbl(Label addressLbl) {
        this.addressLbl = addressLbl;
    }

    public Label getTravelLbl() {
        return travelLbl;
    }

    public void setTravelLbl(Label travelLbl) {
        this.travelLbl = travelLbl;
    }

    public Label getKmsLbl() {
        return kmsLbl;
    }

    public void setKmsLbl(Label kmsLbl) {
        this.kmsLbl = kmsLbl;
    }

    public Label getTotalIncomeLbl() {
        return totalIncomeLbl;
    }

    public void setTotalIncomeLbl(Label totalIncomeLbl) {
        this.totalIncomeLbl = totalIncomeLbl;
    }

    public Label getLastMonthIncomeLbl() {
        return lastMonthIncomeLbl;
    }

    public void setLastMonthIncomeLbl(Label lastMonthIncomeLbl) {
        this.lastMonthIncomeLbl = lastMonthIncomeLbl;
    }

    public Label getAppreciationLbl() {
        return appreciationLbl;
    }

    public void setAppreciationLbl(Label appreciationLbl) {
        this.appreciationLbl = appreciationLbl;
    }

    public Label getEmailLbl() {
        return emailLbl;
    }

    public void setEmailLbl(Label emailLbl) {
        this.emailLbl = emailLbl;
    }

    public Label getPhoneNumberLbl() {
        return phoneNumberLbl;
    }

    public void setPhoneNumberLbl(Label phoneNumberLbl) {
        this.phoneNumberLbl = phoneNumberLbl;
    }

    public Label getNifLbl() {
        return nifLbl;
    }

    public void setNifLbl(Label nifLbl) {
        this.nifLbl = nifLbl;
    }

   

    public Label getEntidadLbl() {
        return entidadLbl;
    }

    public void setEntidadLbl(Label entidadLbl) {
        this.entidadLbl = entidadLbl;
    }

    public Label getOfficeNumberLbl() {
        return officeNumberLbl;
    }

    public void setOfficeNumberLbl(Label officeNumberLbl) {
        this.officeNumberLbl = officeNumberLbl;
    }

    public Label getControlDigitNumberLbl() {
        return controlDigitNumberLbl;
    }

    public void setControlDigitNumberLbl(Label controlDigitNumberLbl) {
        this.controlDigitNumberLbl = controlDigitNumberLbl;
    }

    public Label getAccountNumberLbl() {
        return accountNumberLbl;
    }

    public void setAccountNumberLbl(Label accountNumberLbl) {
        this.accountNumberLbl = accountNumberLbl;
    }

    public Label getTrailerRegistrationLbl() {
        return trailerRegistrationLbl;
    }

    public void setTrailerRegistrationLbl(Label trailerRegistrationLbl) {
        this.trailerRegistrationLbl = trailerRegistrationLbl;
    }
    
}
