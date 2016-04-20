/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers;

import apponsaresmanagement.jpa.controllers.exceptions.IllegalOrphanException;
import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.entities.Cliente;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carmen
 */
public class EditClientDataController implements Initializable {

    @FXML
    private BorderPane editClientDataPane;
    @FXML
    private Button editBtn;
    //the client that will be modified
    private Cliente clienttoEdit;
    private ClientPanelController controller;
    private Utils utils = null;

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

    public void setClienttoEdit(Cliente clienttoEdit) {
        this.clienttoEdit = clienttoEdit;
    }

    public Cliente getClienttoEdit() {
        return clienttoEdit;
    }

    public void setupClientPanelController() {
        //gets the controller of clientPanel and gives the corresponding setup to clientPanel
        if (controller == null) {
            AnchorPane pane;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL resource = getClass().getResource("/apponsaresmanagement/views/client/clientPanel.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                pane = (AnchorPane) fxmlLoader.load(resource.openStream());
                controller = fxmlLoader.getController();
                //set the name validator inactive
                controller.setNameValidator(false);
                controller.setValidators();
                controller.setClientFields(getClienttoEdit());
                editClientDataPane.setCenter(pane);
            } catch (IOException ex) {
                Logger.getLogger(EditClientDataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void editClient(ActionEvent event) throws IllegalOrphanException, NonexistentEntityException, Exception {
        updateClientData();
        getUtils().getClienteController().edit(getClienttoEdit());
        Stage auxStage = new Stage();
        String title = "Cliente editado correctamente.";
        String content = "Su cliente ha sido editado correctamente. Si desea editar otro cliente repita el proceso a través del menú.";
        AnchorPane panel = getUtils().setPopUpStageOK(title, content);
        getUtils().fixAuxWindow(auxStage, panel);
        auxStage.show();
    }

    public void updateClientData() {
        getClienttoEdit().setMovil(controller.getNumMovTxt().getText());
        getClienttoEdit().setFijo(controller.getNumFijoTxt().getText());
        getClienttoEdit().setEmail(controller.getEmailTxt().getText());
        getClienttoEdit().setContacto(controller.getContactTxt().getText());
        getClienttoEdit().setCalle(controller.getStreetTxt().getText());
        getClienttoEdit().setNumero(controller.getNumberTxt().getText());
        getClienttoEdit().setPoblacion(controller.getTownTxt().getText());
        getClienttoEdit().setProvincia(controller.getProvTxt().getText());
        getClienttoEdit().setCodigoPostal(Integer.parseInt(controller.getCpTxt().getText()));
        String dispatcher="";
        if(controller.getDispatcherCbx().isSelected()){
        dispatcher="D";
        }else{dispatcher="N";}
        getClienttoEdit().setDespachante(dispatcher);
        
    }
}
