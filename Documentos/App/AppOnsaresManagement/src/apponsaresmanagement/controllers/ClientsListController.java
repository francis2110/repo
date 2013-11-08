/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers;

import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.utils.ClientTable;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author carmen
 */
public class ClientsListController implements Initializable {

    @FXML
    private AnchorPane tableContent;
    @FXML
    private TableView<ClientTable> clientsListTbl;
    @FXML
    private TableColumn<ClientTable, String> nameColumn;
    @FXML
    private TableColumn<ClientTable, String> contactColumn;
    //table 's data
    private ObservableList<ClientTable> data;
    private List<Cliente> clientsfound;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TableColumn<ClientTable, String> emailColumn;
    private Utils utils = null;
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public List<Cliente> getClientsfound() {
        return clientsfound;
    }

    public void setClientsfound(List<Cliente> clientsfound) {
        this.clientsfound = clientsfound;
    }

    public void setupTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClientTable, String>("nombre"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<ClientTable, String>("contacto"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<ClientTable, String>("email"));
        data = FXCollections.observableList((List) getClientsfound());
        clientsListTbl.setItems(data);
    }

    public void showClient(MouseEvent event) throws IOException {
        //if we do double click in table:
        if (event.getClickCount() == 2) {
            TableView table = (TableView) event.getSource();
            String parentId = getParentClicked(table);
            ObservableList<Cliente> selectedItems = table.getSelectionModel().getSelectedItems();
            BorderPane pane = (BorderPane) table.getScene().lookup("#borderPane");
            AnchorPane panel;
            if (parentId.equals("borderPane")) {
                panel = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/client/seeClientData.fxml"));
                getUtils().setClientLabelsInfo(panel, (Cliente) selectedItems.get(0));
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL resource = getClass().getResource("/apponsaresmanagement/views/client/editClientData.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                panel = (AnchorPane) fxmlLoader.load(resource.openStream());
                EditClientDataController controller = fxmlLoader.getController();
                controller.setClienttoEdit((Cliente) selectedItems.get(0));
                controller.setupClientPanelController();

            }
            pane.setCenter(panel);
        }
    }

    public String getParentClicked(TableView table) {
        //gets the scene from which is clicked the table. The table could be clicked for Edit client or for See data of clients:
        BorderPane pane = (BorderPane) table.getParent().getParent().getParent();
        return pane.getId();
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }
}
