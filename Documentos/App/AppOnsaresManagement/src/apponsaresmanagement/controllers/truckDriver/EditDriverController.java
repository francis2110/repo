/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.truckDriver;

import apponsaresmanagement.jpa.entities.Transportista;
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
 * @author fran <fraordia@gmail.com>
 */
public class EditDriverController implements Initializable {

    @FXML
    private BorderPane editBorderPane;
    private Utils utils = null;
    private List<Transportista> drivers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            drivers = getUtils().getTruckDriverController().findTransportistaEntities();
            //shows in table view clients found
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/truckDriver/driversList.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
            DriversListController controller = fxmlLoader.getController();
            controller.setDriversFound(drivers);
            controller.setupTable();
            editBorderPane.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(EditDriverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }
}
