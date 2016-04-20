package apponsaresmanagement.controllers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import apponsaresmanagement.jpa.entities.Cliente;
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
 */
public class SeeClientController implements Initializable {

    @FXML
    private TextField clientNameTxt;
    @FXML
    private Text validatorTxt;
    private Utils utils = null;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button searchClientBtn;
    private List<Cliente> Clientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public List<Cliente> getClientes() {
        return Clientes;
    }

    public void setClientes(List<Cliente> Clientes) {
        this.Clientes = Clientes;
    }

    public TextField getClientNameTxt() {
        return clientNameTxt;
    }

    public void setClientNameTxt(TextField clientNameTxt) {
        this.clientNameTxt = clientNameTxt;
    }

    public Text getValidatorTxt() {
        return validatorTxt;
    }

    public void setValidatorTxt(Text validatorTxt) {
        this.validatorTxt = validatorTxt;
    }

    //method that search for client by name and show client data in labels
    @FXML
    private void searchClient(ActionEvent event) throws IOException {
        if (!getClientNameTxt().getText().equals("")) {
            Clientes = getUtils().getClienteController().findClientesContainsName(getClientNameTxt().getText());
            if (Clientes.size() > 1) {
                //add a table view if we find more than one client
                getValidatorTxt().setText("");
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL resource = getClass().getResource("/apponsaresmanagement/views/client/clientsList.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
                ClientsListController controller = fxmlLoader.getController();
                controller.setClientsfound(Clientes);
                controller.setupTable();
                borderPane.setCenter(pane);

            } else if (Clientes.size() == 1) {
                getValidatorTxt().setText("");
                //if we find only one client we show his data:
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/client/seeClientData.fxml"));
                borderPane.setCenter(pane);
                //we set the data to the labels:
                Cliente cliente = Clientes.get(0);
                getUtils().setClientLabelsInfo(pane, cliente);

            } else {
                //client not found
               getValidatorTxt().setText("Cliente no encontrado. El clente buscado no se encuentra en su base de datos.");
            }

        }
    }
}
