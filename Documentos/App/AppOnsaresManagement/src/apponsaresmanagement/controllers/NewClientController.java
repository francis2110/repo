package apponsaresmanagement.controllers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import apponsaresmanagement.jpa.controllers.ClienteJpaController;
import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class NewClientController implements Initializable {

    private ClienteJpaController clienteController = null;
    private ClientPanelController clientPnlControler = null;
    private AnchorPane pane = null;
    private Utils utils = null;
    @FXML
    BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button insertBtn;
    @FXML
    private Label titleLbl;

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public ClientPanelController getClientPnlControler() {
        if (clientPnlControler == null) {
            clientPnlControler = new ClientPanelController();
        }
        return clientPnlControler;
    }

    public void setClientPnlControler(ClientPanelController clientPnlControler) {
        this.clientPnlControler = clientPnlControler;
    }

    @FXML
    private void insertarClient(ActionEvent event) throws IOException, PreexistingEntityException, Exception {

        String text = getUtils().getText("#validator", pane).getText();
//        if client data are ok
        if (text.equals("") && !getUtils().getTextField("#clientIdTxt", pane).getText().equals("")) {
            //sets values for new client
            Cliente cliente = new Cliente();
           clientPnlControler.setClient(cliente);
            // getUtils().setClientValues(cliente, pane);
            //save new client
            getUtils().getClienteController().create(cliente);
            Stage auxStage = new Stage();
            String title = "Cliente insertado correctamente.";
            String content = "Si desea introducir nuevos clientes rellene los campos y pulse el botón insertar. Para otras acciones"
                    + "tiene que navegar por el menú.";
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
            clientPnlControler.deleteClientFields();
            //getUtils().cleanClientPanelTextFields(pane);
        }



    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Parent parent = borderPane.getParent();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/client/clientPanel.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            pane = (AnchorPane) fxmlLoader.load(resource.openStream());
            clientPnlControler = fxmlLoader.getController();
            //the name validator is active
            clientPnlControler.setValidators();
            //  pane.lookup("nameTxt");
        } catch (IOException ex) {
            Logger.getLogger(NewClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(pane);
    }
}
