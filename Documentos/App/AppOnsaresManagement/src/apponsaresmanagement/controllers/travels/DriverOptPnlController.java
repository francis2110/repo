/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.utils.Utils;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class DriverOptPnlController implements Initializable {

    ;
    @FXML
    private ComboBox<?> driverCb;
    private Utils utils = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillDriversCb();
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
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

    public ComboBox<?> getDriverCb() {
        return driverCb;
    }
}
