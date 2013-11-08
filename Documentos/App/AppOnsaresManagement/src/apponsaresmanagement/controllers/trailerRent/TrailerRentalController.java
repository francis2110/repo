/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.trailerRent;

import apponsaresmanagement.jpa.entities.Remolque;
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
 * @author fran
 */
public class TrailerRentalController implements Initializable {

    @FXML
    private TableView<?> trailerTbl;
    @FXML
    private TableColumn<Remolque, String> trailerColumn;
    @FXML
    private TableColumn<Remolque, String> stateColumn;
    private Utils utils = null;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Remolque> trailers = getUtils().getTrailerController().findRemolqueEntities();
        setupTable(trailers);
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public TableView<?> getTrailerTbl() {
        return trailerTbl;
    }

    public TableColumn<Remolque, String> getTrailerColumn() {
        return trailerColumn;
    }

    public TableColumn<Remolque, String> getStateColumn() {
        return stateColumn;
    }

    public void setupTable(List<Remolque> trailer) {
        getStateColumn().setCellValueFactory(new PropertyValueFactory<Remolque, String>("estado"));
        getTrailerColumn().setCellValueFactory(new PropertyValueFactory<Remolque, String>("matricula"));
        ObservableList trailersObLst = FXCollections.observableList(trailer);
        deleteOldTrailers(trailersObLst);
        getTrailerTbl().setItems(trailersObLst);
    }

    private void deleteOldTrailers(ObservableList<Remolque> trailers) {
        //delete old trailers in observableList:
        for (Remolque t : trailers) {
            if (t.getEstado().equals("Desechado")) {
                trailers.remove(t);
            }
        }
    }

    @FXML
    private void clickTrailerTableRow(MouseEvent event) throws IOException {
        //we do double click on table row and we choose the trailer for edit its contract
        if (event.getClickCount() == 2) {
            TableView trailersTb = (TableView) event.getSource();
            Remolque selectedTrailer = (Remolque) trailersTb.getSelectionModel().getSelectedItem();
            BorderPane borderPane = (BorderPane) getAnchorPane().getParent();
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/trailerRent/editTrailerRental.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            borderPane.setCenter((AnchorPane) fxmlLoader.load(resource.openStream()));
            EditTrailerRentalController controller = fxmlLoader.getController();
            controller.setSelectedTrailer(selectedTrailer);
        }
    }
}
