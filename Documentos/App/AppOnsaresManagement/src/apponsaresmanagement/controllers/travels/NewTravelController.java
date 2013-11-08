/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
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
public class NewTravelController implements Initializable {
    
    @FXML
    private BorderPane newTravelBPn;
    @FXML
    private Button createTravelBtn;
    @FXML
    private Label titleLbl;
    private TravelPanelController controller;
    private Utils utils = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AnchorPane pane = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/travels/travelPanel.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            pane = (AnchorPane) fxmlLoader.load(resource.openStream());
            controller = fxmlLoader.getController();
            
        } catch (IOException ex) {
            Logger.getLogger(NewTravelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getNewTravelBPn().setCenter(pane);
    }
    
    public BorderPane getNewTravelBPn() {
        return newTravelBPn;
    }
    
    public void setNewTravelBPn(BorderPane newTravelBPn) {
        this.newTravelBPn = newTravelBPn;
    }
    
    public Button getCreateTravelBtn() {
        return createTravelBtn;
    }
    
    public void setCreateTravelBtn(Button createTravelBtn) {
        this.createTravelBtn = createTravelBtn;
    }
    
    public Label getTitleLbl() {
        return titleLbl;
    }
    
    public void setTitleLbl(Label titleLbl) {
        this.titleLbl = titleLbl;
    }
    
    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }
    
    @FXML
    private void insertNewTravel(ActionEvent event) throws ParseException, PreexistingEntityException, Exception {
        if (controller.getClientCb().getSelectionModel().getSelectedItem() == null) {
            controller.getValidatorTxt().setText(" Seleccione el cliente del viaje.");
        } else if (controller.getDriverCb().getSelectionModel().getSelectedItem() == null) {
            controller.getValidatorTxt().setText("Seleccione un transportista para el viaje");
        } else if (controller.getClientPaymentCb().getSelectionModel().getSelectedItem() == null) {
            controller.getValidatorTxt().setText("Seleccione el estado del pago del cliente.");
        } else if (controller.getDriverPaymentCb().getSelectionModel().getSelectedItem() == null) {
            controller.getValidatorTxt().setText("Seleccione el estado del pago al transportista.");
        } else if (controller.getIvaCb().getSelectionModel().getSelectedItem() == null) {
            controller.getValidatorTxt().setText("Seleccione quien asume el iva del viaje.");
        } else if (!controller.getIvaCb().getSelectionModel().getSelectedItem().equals("Abonado por cliente")
                && controller.getTreasuryStateCb().getSelectionModel().getSelectedItem() == null) {
            controller.getValidatorTxt().setText("Seleccione el estado del iva por parte de Hacienda.");
        } else if (controller.getTravelTypeCb().getSelectionModel().getSelectedItem() == null) {
            controller.getValidatorTxt().setText("Seleccione si el viaje es Exportación o Terrestre.");
        } else {
            controller.getValidatorTxt().setText("");
        }
        if (controller.getValidatorTxt().getText().equals("")) {
            Viaje v = new Viaje();
            // getUtils().getTravelController().create(null);
            getUtils().setTravelValues(v, controller);
            getUtils().getTravelController().create(v);
            controller.clearFields();
            Stage auxStage = new Stage();
            String title = "Viaje creado correctamente";
            String content = "El viaje ha sido creado correctamente. Si desea introducir un nuevo viaje repita la operación.";
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
        }
        
    }
//    public void setTravelValues(Viaje v) throws ParseException {
//        //method to set the values for the travel with the panel fields
//        if (!controller.getDateTxt().getText().equals("")) {
//            v.setFecha(controller.getDate());
//        }
//        NumberFormat instance = DecimalFormat.getInstance();
//        if (!controller.getTravelValueTxt().getText().equals("")) {
//            v.setIngreso(new BigDecimal(controller.getTravelValueTxt().getText()));
//        }
//        if (!controller.getMoneyforDriverTxt().getText().equals("")) {
//            v.setPagoACamionero(new BigDecimal(controller.getMoneyforDriverTxt().getText()));
//        }
//        v.setKms(controller.getDistanceTxt().getText());
//
//        v.setNumeroContenedor(controller.getContainerTxt().getText());
//        v.setPoblacion(controller.getTownTxt().getText());
//        if (!controller.getPaymentTypeTxt().getText().equals("")) {
//            v.setTipoPago(Integer.parseInt(controller.getPaymentTypeTxt().getText()));
//        }
//        v.setDestinatario(controller.getAddresseeTxt().getText());
//        v.setAduana(controller.getCustomsTxt().getText().trim());
//        v.setGastosNaviera(controller.getShippingExpensesTxt().getText().trim());
//        v.setOtrosGastos(controller.getOthersDescriptionTFld().getText());
//        v.setCantidadOtros(controller.getOtherExpensesTxt().getText().trim());
//        String ivaOp = "";
//        if (controller.getIvaCb().getSelectionModel().getSelectedItem() != null) {
//            ivaOp = controller.getIvaCb().getSelectionModel().getSelectedItem().toString();
//            if (ivaOp.equals("Abonado por cliente")) {
//                v.setIva("S");
//            } else if (ivaOp.equals("No abonado por cliente")) {
//                v.setIva("N");
//            }
//        }
//        if (controller.getClientPaymentCb().getSelectionModel().getSelectedItem() != null) {
//            v.setEstadoCliente(controller.getClientPaymentCb().getSelectionModel().getSelectedItem().toString());
//        }
//        if (controller.getDriverPaymentCb().getSelectionModel().getSelectedItem() != null) {
//            v.setEstadoTransportista(controller.getDriverPaymentCb().getSelectionModel().getSelectedItem().toString());
//        }
//        if (controller.getClientCb().getSelectionModel().getSelectedItem() != null) {
//            v.setCliente(getClienteforTravel());
//        }
//        if (controller.getDriverCb().getSelectionModel().getSelectedItem() != null) {
//            v.setTransportista(getUtils().getTruckDriverController().findDriverbyNameAndSurname(controller.getDriverCb().getSelectionModel().getSelectedItem().toString()));
//        }
//        ViajePK vPK = new ViajePK(controller.generateAlbaranNumber(), v.getCliente().getCifNif(), v.getTransportista().getNif());
//        v.setViajePK(vPK);
//    }
//
//    public Cliente getClienteforTravel() {
//        //returns the first element of the list of clients found by name. If more than one client has the same name 
//        //WE HAVE TO IMPROVE THIS METHOD: show new stage with a grid with data of clients and the user clicks one
//        return getUtils().getClienteController().findClienteByName(controller.getClientCb().getSelectionModel().getSelectedItem().toString()).get(0);
//
//    }
}
