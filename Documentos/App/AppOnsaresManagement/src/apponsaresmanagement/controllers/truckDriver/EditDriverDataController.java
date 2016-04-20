/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.truckDriver;

import apponsaresmanagement.controllers.EditClientDataController;
import apponsaresmanagement.jpa.controllers.exceptions.IllegalOrphanException;
import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fran <fraordia@gmail.com>
 */
public class EditDriverDataController implements Initializable {

    @FXML
    private Button editBtn;
    private Utils utils = null;
    private Transportista driverToEdit;
    private TruckDriverPanelController controller;
    @FXML
    private BorderPane editDriverDataPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Transportista getDriverToEdit() {
        return driverToEdit;
    }

    public void setDriverToEdit(Transportista driverToEdit) {
        this.driverToEdit = driverToEdit;
    }

    public TruckDriverPanelController getController() {
        if (controller == null) {
            controller = new TruckDriverPanelController();
        }
        return controller;
    }

    public void setController(TruckDriverPanelController controller) {
        this.controller = controller;
    }

    public void setupDriverPanelController() {
        //gets the controller of clientPanel and gives the corresponding setup to clientPanel

        AnchorPane pane;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/truckDriver/truckDriverPanel.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            pane = (AnchorPane) fxmlLoader.load(resource.openStream());
            controller = fxmlLoader.getController();
            //set the name validator inactive
            getController().setNameValidator(false);
            getController().getNameTxt().setEditable(false);
            getController().getNifTxt().setEditable(false);
            getController().setValidators();
            getController().setDriverFields(driverToEdit);
            editDriverDataPane.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(EditClientDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    private void setDriverFields() {
        driverToEdit.setApellido1(getController().getFirstSurnameTxt().getText());
        driverToEdit.setApellido2(getController().getSecondSurNameTxt().getText());
        driverToEdit.setMatriculaTractora(getController().getTruckEnrollmentTxt().getText());
        driverToEdit.setMatriculaRemolque(getController().getTrailerTxt().getText());
        driverToEdit.setDirección(getController().getAddressTxt().getText());
        driverToEdit.setEmail(getController().getEmailTxt().getText());
        driverToEdit.setTelefono(getController().getPhoneTxt().getText());
        driverToEdit.setNumCuenta(getNumberAccount());
    }

    private String getNumberAccount() {
        return getController().getEntidadTxt().getText() + getController().getOfficeNumb().getText() + getController().getControlDigitTxt().getText() + getController().getNumberAccountTxt().getText();
    }

    @FXML
    private void editDriver(ActionEvent event) throws IllegalOrphanException, NonexistentEntityException, Exception {
        setDriverFields();
        getUtils().getTruckDriverController().edit(driverToEdit);
        getController().deleteFields();
        Stage auxStage = new Stage();
        String title="Transportista editado correctamente.";
        String content="El transportista ha sido modificado. Si desea editar o realizar otra operación deberá navegar"
                + "a través del menú.";
        AnchorPane panel=getUtils().setPopUpStageOK(title, content);
        getUtils().fixAuxWindow(auxStage, panel);
        auxStage.show();
    }
    //fix the size of the aux window
    private void fixInsertDriversWindow(Stage auxStage, Parent root) {
        Scene scene = new Scene(root, 400, 300);
        auxStage.setScene(scene);
        auxStage.setMaxHeight(300);
        auxStage.setMinHeight(300);
        auxStage.setMaxWidth(400);
        auxStage.setMinWidth(400);
    }
}
