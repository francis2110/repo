/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.truckDriver;

import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.utils.DriverTable;
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
public class DriversListController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TableColumn<DriverTable, String> nameColumn;
    @FXML
    private TableColumn<DriverTable, String> surnameColumn;
    @FXML
    private TableColumn<DriverTable, String> emailColumn;
    private Utils utils = null;
    private Transportista driver;
    private ObservableList<DriverTable> data;
    private List<Transportista> driversFound;
    @FXML
    private TableView<DriverTable> driversTbl;

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

    public List<Transportista> getDriversFound() {
        return driversFound;
    }

    public void setDriversFound(List<Transportista> driversFound) {
        this.driversFound = driversFound;
    }

    public void setupTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<DriverTable, String>("nombre"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<DriverTable, String>("apellido1"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<DriverTable, String>("email"));
        data = FXCollections.observableList((List) getDriversFound());
        driversTbl.setItems(data);
    }

    @FXML
    public void showDriver(MouseEvent event) throws IOException {
        //if we double click in table:
        if (event.getClickCount() == 2) {
            TableView table = (TableView) event.getSource();
            String parentId = getParentClicked(table);
            ObservableList<Transportista> selectedItems = table.getSelectionModel().getSelectedItems();
            BorderPane pane = (BorderPane) table.getScene().lookup("#borderPane");
            AnchorPane panel;
            if (parentId.equals("borderPane")) {
                getUtils().setDriversInfo(pane, (Transportista) selectedItems.get(0));
            } else if (parentId.equals("editBorderPane")) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL resource = getClass().getResource("/apponsaresmanagement/views/truckDriver/editDriverData.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                panel = (AnchorPane) fxmlLoader.load(resource.openStream());
                EditDriverDataController controller = fxmlLoader.getController();
                controller.setDriverToEdit(selectedItems.get(0));
                controller.setupDriverPanelController();
                pane.setCenter(panel);
            }


        }
    }

    public String getParentClicked(TableView table) {
        //gets the scene from which is clicked the table. The table could be clicked for Edit client or for See data of clients:
        BorderPane pane = (BorderPane) table.getParent().getParent().getParent();
        return pane.getId();
    }
}
