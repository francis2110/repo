/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.truckDriver;

import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carmen
 */
public class NewTruckDriverController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane newTruckDriverPnl;
    private AnchorPane pane;
    private Utils utils = null;
    private TruckDriverPanelController controller;
    @FXML
    private Label titleLbl;

    public BorderPane getNewTruckDriverPnl() {
        return newTruckDriverPnl;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //TODO 
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/truckDriver/truckDriverPanel.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            pane = (AnchorPane) fxmlLoader.load(resource.openStream());
            controller = (TruckDriverPanelController) fxmlLoader.getController();
            //the name validator is active
            controller.setValidators();
        } catch (IOException ex) {
            Logger.getLogger(NewTruckDriverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getNewTruckDriverPnl().setCenter(pane);
    }

    public TruckDriverPanelController getController() {
        return controller;
    }

    @FXML
    private void insertTruckDriver(ActionEvent event) throws PreexistingEntityException, Exception {
        String text = getUtils().getText("#validatorTxt", pane).getText();
//        if truck driver data are ok
        if (text.equals("")) {
            Transportista truckDriver = new Transportista();
            setTruckDriverValues(truckDriver);
            getUtils().getTruckDriverController().create(truckDriver);
            Stage auxStage = new Stage();
            String title = "Transportista insertado correctamente.";
            String content = "Los datos del transportista han sido guardados correctamente. Si desea introducir los datos de un nuevo transportista repita   la operación.";
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
            deleteTextFields();
        }
    }

    private void setTruckDriverValues(Transportista driver) {
        //sets values for truck driver
        driver.setNombre(getUtils().getTextField("#nameTxt", pane).getText());
        driver.setApellido1(getUtils().getTextField("#firstSurnameTxt", pane).getText());
        driver.setApellido2(getUtils().getTextField("#secondSurNameTxt", pane).getText());
        driver.setMatriculaTractora(getUtils().getTextField("#truckEnrollmentTxt", pane).getText());
        driver.setMatriculaRemolque(getUtils().getTextField("#trailerTxt", pane).getText());
        driver.setDirección(getUtils().getTextField("#addressTxt", pane).getText());
        driver.setEmail(getUtils().getTextField("#emailTxt", pane).getText());
        driver.setTelefono(getUtils().getTextField("#phoneTxt", pane).getText());
        driver.setNif(getUtils().getTextField("#nifTxt", pane).getText());
        driver.setNumCuenta(controller.formNumberAccount());
    }

    private void deleteTextFields() {
        //clean textfields
        getUtils().getTextField("#nameTxt", pane).setText("");
        getUtils().getTextField("#firstSurnameTxt", pane).setText("");
        getUtils().getTextField("#secondSurNameTxt", pane).setText("");
        getUtils().getTextField("#truckEnrollmentTxt", pane).setText("");
        getUtils().getTextField("#trailerTxt", pane).setText("");
        getUtils().getTextField("#addressTxt", pane).setText("");
        getUtils().getTextField("#emailTxt", pane).setText("");
        getUtils().getTextField("#phoneTxt", pane).setText("");
        getUtils().getTextField("#nifTxt", pane).setText("");
        controller.deleteAccountTextFields();
    }
}
