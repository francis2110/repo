/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.trailerRent;

import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Remolque;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class NewTrailerDataController implements Initializable {

    @FXML
    private Button addTrailerBtn;
    @FXML
    private BorderPane newTrailerBorderPane;
    private TrailerDataPanelController controller;
    private Utils utils = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/apponsaresmanagement/views/trailerRent/TrailerDataPanel.fxml");
        fxmlLoader.setLocation(resource);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            getNewTrailerBorderPane().setCenter((AnchorPane) fxmlLoader.load(resource.openStream()));
        } catch (IOException ex) {
            Logger.getLogger(NewTrailerDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setController((TrailerDataPanelController) fxmlLoader.getController());


    }

    public BorderPane getNewTrailerBorderPane() {
        return newTrailerBorderPane;
    }

    public TrailerDataPanelController getController() {
        return controller;
    }

    public void setController(TrailerDataPanelController controller) {
        this.controller = controller;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    @FXML
    private void addTrailer(ActionEvent event) throws PreexistingEntityException, Exception {
        if (getController().getValidatorTxt().getText().equals("")) {
            Remolque trailer = new Remolque();
            setTrailerData(trailer);
            getUtils().getTrailerController().create(trailer);
            Stage auxStage = new Stage();
            String title = "Nuevo remolque introducido correctamente";
            String content = "El remolque " + trailer.getMatricula() + " ha sido creado correctamente. Si desea registrar un nuevo remolque repita la operación.";
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
            clearFields();
        }

    }

    private void setTrailerData(Remolque trailer) {
        //sets the data of the panel into trailer entity
        trailer.setMatricula(getController().getTrailerRegisNumberTxt().getText().trim());
        trailer.setFechaRemolque(getUtils().getDatewithFormat(getController().getTrailerYearTxt().getText().trim()).getTime());
        trailer.setFechaCompra(getUtils().getDatewithFormat(getController().getTrailerPurchaseDateTxt().getText().trim()).getTime());
        trailer.setPrecioCompra(new BigDecimal(getController().getTrailerPriceTxt().getText()));
        trailer.setItv(getUtils().getDatewithFormat(getController().getItvDateTxt().getText().trim()).getTime());
        trailer.setEstado("Parado");
    }

    private void clearFields() {
        //clear all fields to be prepared for other operation.
        getController().getTrailerRegisNumberTxt().setText("");
        getController().getTrailerYearTxt().setText("");
        getController().getTrailerPurchaseDateTxt().setText("");
        getController().getTrailerPriceTxt().setText("");
        getController().getItvDateTxt().setText("");
    }
}
