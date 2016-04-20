/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.jpa.controllers.exceptions.NonexistentEntityException;
import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.jpa.entities.ViajePK;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
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
public class EditTravelController implements Initializable {
    
    @FXML
    private Button editBtn;
    private TravelPanelController controller;
    @FXML
    private BorderPane editBPnl;
    private Viaje selectedTravel;
    private Utils utils = null;
    @FXML
    private Label albaranLbl;

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
        getEditBPnl().setCenter(pane);
    }
    
    public Button getEditBtn() {
        return editBtn;
    }
    
    public void setEditBtn(Button editBtn) {
        this.editBtn = editBtn;
    }
    
    public TravelPanelController getController() {
        return controller;
    }
    
    public void setController(TravelPanelController controller) {
        this.controller = controller;
    }
    
    public BorderPane getEditBPnl() {
        return editBPnl;
    }
    
    public void setEditBPnl(BorderPane editBPnl) {
        this.editBPnl = editBPnl;
    }
    
    public Label getAlbaranLbl() {
        return albaranLbl;
    }
    
    public void setAlbaranLbl(Label albaranLbl) {
        this.albaranLbl = albaranLbl;
    }
    
    public Viaje getSelectedTravel() {
        return selectedTravel;
    }
    
    public void setSelectedTravel(Viaje selectedTravel) {
        this.selectedTravel = selectedTravel;
    }
    
    public void fillTravels() {
        //calls the method that fills the panel of travels with corresponding data
        getAlbaranLbl().setText(getSelectedTravel().getViajePK().getAlbaran());
        controller.setFields(getSelectedTravel());
    }
    
    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }
    
    @FXML
    private void editTravel(ActionEvent event) throws ParseException, NonexistentEntityException, Exception {
        if (controller.getValidatorTxt().getText().equals("")) {
           getUtils(). setTravelValues(selectedTravel,getController());
            //if we modify the client or the driver we have to destroy the travel and generate another new one
            if (selectedTravel.getViajePK().getClienteCifNif() != selectedTravel.getCliente().getCifNif() || selectedTravel.getViajePK().getTransportistaNIF() != selectedTravel.getTransportista().getNif()) {
                ViajePK vPK = new ViajePK(selectedTravel.getViajePK().getAlbaran(), selectedTravel.getCliente().getCifNif(), selectedTravel.getTransportista().getNif());
                getUtils().getTravelController().destroy(selectedTravel.getViajePK());
                selectedTravel.setViajePK(vPK);
                getUtils().getTravelController().create(selectedTravel);
            } else {
                getUtils().getTravelController().edit(selectedTravel);
            }
            Stage auxStage = new Stage();
            String title = "Viaje editado correctamente";
            String content = "El viaje " + selectedTravel.getViajePK().getAlbaran() + " ha sido editado correctamente. Si desea editar  un nuevo viaje repita la operación.";
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
            getAlbaranLbl().setText("");
            controller.clearFields();
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
//        v.setKms(controller.getDistanceTxt().getText().trim());
//        v.setNumeroContenedor(controller.getContainerTxt().getText().trim());
//        v.setPrecinto(controller.getSealTxt().getText().trim());
//        v.setTipoContenedor(controller.getContainerTypeTxt().getText().trim());
//        v.setBultos(Integer.parseInt(controller.getPackageTxt().getText().trim()));
//        v.setMercancia(controller.getCommodityTxt().getText().trim());
//        v.setPeso(Integer.parseInt(controller.getWeightTxt().getText().trim()));
//        v.setOrigen(controller.getOriginTxt().getText().trim());
//        v.setDestino(controller.getDestinationTxt().getText().trim());
//        v.setLugarRecogida(controller.getCollectionPlaceTxt().getText().trim());
//        v.setLugarCarga(controller.getLoadPlaceTxt().getText().trim());
//        v.setCargador(controller.getCarrierTxt().getText().trim());
//        Calendar arriveCal = Calendar.getInstance();
//        arriveCal.setTime(controller.getDate());
//        String arriveHour = controller.getArriveHourTxt().getText().trim();
//        String[] hhMM = arriveHour.split(":");
//        arriveCal.set(arriveCal.get(Calendar.YEAR), arriveCal.get(Calendar.MONTH), arriveCal.get(Calendar.DAY_OF_MONTH), Integer.parseInt(hhMM[0]), Integer.parseInt(hhMM[1]));
//        v.setHoraLlegada(arriveCal.getTime());
//        String exitHour = controller.getExitHourTxt().getText().trim();
//        hhMM = exitHour.split(":");
//        Calendar exitCal = Calendar.getInstance();
//        exitCal.setTime(controller.getDate());
//        exitCal.set(exitCal.get(Calendar.YEAR), exitCal.get(Calendar.MONTH), exitCal.get(Calendar.DAY_OF_MONTH), Integer.parseInt(hhMM[0]), Integer.parseInt(hhMM[1]));
//        v.setHoraSalida(exitCal.getTime());
//        v.setLugarEntrega(controller.getDeliveryPlaceTxt().getText().trim());
//        if (!controller.getPaymentTypeTxt().getText().equals("")) {
//            v.setTipoPago(Integer.parseInt(controller.getPaymentTypeTxt().getText()));
//        }
//        v.setGastosNaviera(controller.getShippingExpensesTxt().getText().trim());
//        v.setAduana(controller.getCustomsTxt().getText().trim());
//        v.setOtrosGastos(controller.getOthersDescriptionTFld().getText());
//        v.setCantidadOtros(controller.getOtherExpensesTxt().getText().trim());
//        v.setNaviera(getController().getShippingTxt().getText().trim());
//        v.setBuque(getController().getBoatTxt().getText().trim());
//        v.setObservaciones(getController().getObservationsTxt().getText().trim());
//        
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
//        if (controller.getTravelTypeCb().getSelectionModel().getSelectedItem() != null) {
//            String travelType = controller.getTravelTypeCb().getSelectionModel().getSelectedItem().toString().trim();
//            if (travelType.equals("Terrestre")) {
//                v.setTipoViaje("T");
//            } else {
//                v.setTipoViaje("EX");
//            }
//        }
//        if (controller.getTreasuryStateCb().getSelectionModel().getSelectedItem() != null) {
//            v.setEstadoHacienda(controller.getTreasuryStateCb().getSelectionModel().getSelectedItem().toString().trim());
//        }
//        if (controller.getDispatcherCb().getSelectionModel().getSelectedItem() != null) {
//            v.setDespachante(controller.getDispatcherCb().getSelectionModel().getSelectedItem().toString().trim());
//        }
//        // ViajePK vPK = new ViajePK(v.getViajePK().getAlbaran(), v.getCliente().getCifNif(), v.getTransportista().getNif());
//
//    }
//    
//    public Cliente getClienteforTravel() {
//        //returns the first element of the list of clients found by name. If more than one client has the same name 
//        //WE HAVE TO IMPROVE THIS METHOD: show new stage with a grid with data of clients and the user clicks one
//        return getUtils().getClienteController().findClienteByName(controller.getClientCb().getSelectionModel().getSelectedItem().toString()).get(0);
//        
//    }
}
