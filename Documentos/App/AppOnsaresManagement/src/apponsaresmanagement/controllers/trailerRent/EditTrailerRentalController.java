/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.trailerRent;

import apponsaresmanagement.jpa.entities.Remolque;
import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.utils.Utils;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class EditTrailerRentalController implements Initializable {

        private Label titleLbl;
        private ComboBox<?> stateCb;
        private ComboBox<?> driverCb;
        private TextField monthlyPaymentTxt;
        private GridPane alreadyRentedGP;
        private TextField initDateTxt;
        private TextField delayMonthsTxt;
        private TextField debtTxt;
        private TextField paidAmountTxt;
    private Remolque selectedTrailer;
    private Utils utils = null;
    @FXML
    private Button modifyBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getTitleLbl().setText(getTitleLbl().getText() + " "+getSelectedTrailer().getMatricula());
        fillDriversCb();
        fillStateCb();
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public Remolque getSelectedTrailer() {
        return selectedTrailer;
    }

    public void setSelectedTrailer(Remolque selectedTrailer) {
        this.selectedTrailer = selectedTrailer;
    }

    public Label getTitleLbl() {
        return titleLbl;
    }

    public ComboBox<?> getStateCb() {
        return stateCb;
    }

    public ComboBox<?> getDriverCb() {
        return driverCb;
    }

    public TextField getMonthlyPaymentTxt() {
        return monthlyPaymentTxt;
    }

    public GridPane getAlreadyRentedGP() {
        return alreadyRentedGP;
    }

    public TextField getInitDateTxt() {
        return initDateTxt;
    }

    public TextField getDelayMonthsTxt() {
        return delayMonthsTxt;
    }

    public TextField getDebtTxt() {
        return debtTxt;
    }

    public TextField getPaidAmountTxt() {
        return paidAmountTxt;
    }

    private void fillStateCb() {
        //set the possible states of the trailer. 
        String[] states = {"Alquilado", "Enganche", "Ninguno"};
        ObservableList trailerStates = FXCollections.observableArrayList();
        trailerStates.addAll(states);
        getStateCb().setItems(trailerStates);
    }

    private void fillDriversCb() {
        //gets drivers and fill the combobox
        List<Transportista> findTransportistaEntities = getUtils().getTruckDriverController().findTransportistaEntities();
        ObservableList driversList = FXCollections.observableArrayList();
        for (Transportista t : findTransportistaEntities) {
            driversList.add(t.getNombre().trim() + " " + t.getApellido1().trim() + " " + t.getApellido2().trim());
        }
        getDriverCb().setItems(driversList);
    }

    @FXML
    private void modifyTrailer(ActionEvent event) {
    }
}
