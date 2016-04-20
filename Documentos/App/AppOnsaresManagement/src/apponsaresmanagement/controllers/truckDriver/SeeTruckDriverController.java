/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.truckDriver;

import apponsaresmanagement.jpa.controllers.ViajeJpaController;
import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author fran
 *
 */
public class SeeTruckDriverController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField truckDriverTxt;
    @FXML
    private Button searchDriverBtn;
    private Utils utils = null;
    @FXML
    private Text validatorTxt;
    private List<Transportista> Drivers;
    private ViajeJpaController travels = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    

    public TextField getTruckDriverTxt() {
        return truckDriverTxt;
    }

    public void setTruckDriverTxt(TextField truckDriverTxt) {
        this.truckDriverTxt = truckDriverTxt;
    }

    public Button getSearchDriverBtn() {
        return searchDriverBtn;
    }

    public void setSearchDriverBtn(Button searchDriverBtn) {
        this.searchDriverBtn = searchDriverBtn;
    }

    public Text getValidatorTxt() {
        return validatorTxt;
    }

    public void setValidatorTxt(Text validatorTxt) {
        this.validatorTxt = validatorTxt;
    }

    public List<Transportista> getDrivers() {
        return Drivers;
    }

    public void setDrivers(List<Transportista> Drivers) {
        this.Drivers = Drivers;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    @FXML
    private void searchDriver(ActionEvent event) throws IOException {
        if (!getTruckDriverTxt().getText().equals("")) {
            //if textbox is not empty we look for the driver:
            List<Transportista> findDriverContainsName = getUtils().getTruckDriverController().findDriverContainsName(getTruckDriverTxt().getText());
            if (findDriverContainsName.size() > 1) {
                //if we have more than one driver with this name we add a table view:
                getValidatorTxt().setText("");
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL resource = getClass().getResource("/apponsaresmanagement/views/truckDriver/driversList.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
                DriversListController controller = fxmlLoader.getController();
                controller.setDriversFound(findDriverContainsName);
                controller.setupTable();
                getBorderPane().setCenter(pane);

            } else if (findDriverContainsName.size() == 1) {
                //if we have one driver with this name
                getValidatorTxt().setText("");
                //if we find only one client we show his data:

                //we set the data to the labels:
                Transportista driver = findDriverContainsName.get(0);
               getUtils().setDriversInfo(getBorderPane(),driver);
            } else {
                //we do not have any driver with this name
                getValidatorTxt().setText("Transportista no encontrado. El clente buscado no se encuentra en su base de datos.");
            }
        }
    }

   
}
