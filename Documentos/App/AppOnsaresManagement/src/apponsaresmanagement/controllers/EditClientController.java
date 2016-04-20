/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers;

import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author carmen
 */
public class EditClientController implements Initializable {
    BorderPane borderPane;
    private Utils utils = null;
    private List<Cliente> Clients;
    @FXML
    private BorderPane editBorderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            Clients = getUtils().getClienteController().findClienteEntities();
            //shows in table view clients found
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/client/clientsList.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
            ClientsListController controller = fxmlLoader.getController();
            controller.setClientsfound(Clients);
            controller.setupTable();
            editBorderPane.setCenter(pane);




            //    public void fillClientsComboBox() {
            //        List<Cliente> findClients = getUtils().getClienteController().findClienteEntities();
            //        List<String> clientsName = new ArrayList<>();
            //        ObservableList<String> clientsNameLst;
            //        int i = 0;
            //        for (Cliente client : findClients) {
            //            clientsName.add(findClients.get(i).getNombre());
            //             getClientsCbx().getItems().addAll(clientsName.get(i));
            //            i++;
            //        }
            //        i = 0;
            //           }

        } catch (IOException ex) {
            Logger.getLogger(EditClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }
}
