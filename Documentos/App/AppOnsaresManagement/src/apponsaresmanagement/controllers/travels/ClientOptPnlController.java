/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.utils.Utils;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class ClientOptPnlController implements Initializable {

    @FXML
    private ComboBox<?> clientCb;
    private Utils utils = null;

    ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillClientsCb();
    }

    public ComboBox<?> getClientCb() {
        return clientCb;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    private void fillClientsCb() {
        //gets all the clients and fill the combobox
        List<Cliente> findClienteEntities = getUtils().getClienteController().findClienteEntities();
        ObservableList clientsList = FXCollections.observableArrayList();
        for (Cliente c : findClienteEntities) {
            if (!c.getDespachante().equals("D")) {
                //only fill the combobox with clients and not with dispatchers
                clientsList.add(c.getNombre());
            }
        }
        getClientCb().setItems(clientsList);
    }

    @FXML
    private void comboListener(ActionEvent event) {
    }
}
