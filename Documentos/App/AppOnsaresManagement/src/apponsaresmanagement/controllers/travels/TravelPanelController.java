/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.utils.Utils;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class TravelPanelController implements Initializable {

    @FXML
    private Label albaranLbl;
    @FXML
    private Label addresseeLbl;
    @FXML
    private ComboBox<?> ivaCb;
    @FXML
    private ComboBox<?> clientPaymentCb;
    @FXML
    private ComboBox<?> driverPaymentCb;
    @FXML
    private ComboBox<?> clientCb;
    @FXML
    private ComboBox<?> driverCb;
    @FXML
    private TextField dateTxt;
    @FXML
    private TextField travelValueTxt;
    @FXML
    private TextField moneyforDriverTxt;
    @FXML
    private TextField distanceTxt;
    @FXML
    private TextField containerTxt;
    @FXML
    private TextField paymentTypeTxt;
    private Utils utils = null;
    private Date date = new Date();
    @FXML
    private Text validatorTxt;
    @FXML
    private AnchorPane panelPn;
    @FXML
    private TextField shippingExpensesTxt;
    @FXML
    private TextField customsTxt;
    @FXML
    private TextField otherExpensesTxt;
    @FXML
    private TextArea othersDescriptionTFld;
    @FXML
    private TextField originTxt;
    @FXML
    private TextField sealTxt;
    @FXML
    private TextField containerTypeTxt;
    @FXML
    private TextField destinationTxt;
    @FXML
    private ComboBox<?> travelTypeCb;
    @FXML
    private TextField packageTxt;
    @FXML
    private TextField commodityTxt;
    @FXML
    private TextField weightTxt;
    @FXML
    private TextField collectionPlaceTxt;
    @FXML
    private TextField exitHourTxt;
    @FXML
    private TextField carrierTxt;
    @FXML
    private TextField deliveryPlaceTxt;
    @FXML
    private ComboBox<?> treasuryStateCb;
    @FXML
    private TextField shippingTxt;
    @FXML
    private ComboBox<?> dispatcherCb;
    @FXML
    private TextField boatTxt;
    @FXML
    private TextArea observationsTxt;
    @FXML
    private TextField arriveHourTxt;
    @FXML
    private TextField loadPlaceTxt;
    @FXML
    private GridPane travelGp;
    @FXML
    private CheckBox containerStateCkb;
    @FXML
    private TextField providedHourTxt;
    @FXML
    private TextField referenceTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillIVACb();
        addChangeListenerIvaCb();
        fillPaymentCb();
        fillClientsCbAndDispatcherCb();
        fillDriversCb();
        fillTravelTypeCb();
        fillTreasuryStateCb();
        setValidators();
    }

    public TextField getShippingExpensesTxt() {
        return shippingExpensesTxt;
    }

    public TextField getCustomsTxt() {
        return customsTxt;
    }

    public TextField getOtherExpensesTxt() {
        return otherExpensesTxt;
    }

    public TextArea getOthersDescriptionTFld() {
        return othersDescriptionTFld;
    }

    public Label getAlbaranLbl() {
        return albaranLbl;
    }

    public Label getAddresseeLbl() {
        return addresseeLbl;
    }

    public ComboBox<?> getIvaCb() {
        return ivaCb;
    }

    public ComboBox<?> getClientPaymentCb() {
        return clientPaymentCb;
    }

    public ComboBox<?> getDriverPaymentCb() {
        return driverPaymentCb;
    }

    public ComboBox<?> getClientCb() {
        return clientCb;
    }

    public ComboBox<?> getDriverCb() {
        return driverCb;
    }

    public TextField getDateTxt() {
        return dateTxt;
    }

    public TextField getTravelValueTxt() {
        return travelValueTxt;
    }

    public TextField getMoneyforDriverTxt() {
        return moneyforDriverTxt;
    }

    public TextField getDistanceTxt() {
        return distanceTxt;
    }

    public TextField getContainerTxt() {
        return containerTxt;
    }

    public TextField getPaymentTypeTxt() {
        return paymentTypeTxt;
    }

    public Text getValidatorTxt() {
        return validatorTxt;
    }

    public AnchorPane getPanelPn() {
        return panelPn;
    }

    public TextField getOriginTxt() {
        return originTxt;
    }

    public TextField getSealTxt() {
        return sealTxt;
    }

    public TextField getContainerTypeTxt() {
        return containerTypeTxt;
    }

    public TextField getDestinationTxt() {
        return destinationTxt;
    }

    public ComboBox<?> getTravelTypeCb() {
        return travelTypeCb;
    }

    public ComboBox<?> getDispatcherCb() {
        return dispatcherCb;
    }

    public TextField getPackageTxt() {
        return packageTxt;
    }

    public TextField getCommodityTxt() {
        return commodityTxt;
    }

    public TextField getWeightTxt() {
        return weightTxt;
    }

    public TextField getLoadPlaceTxt() {
        return loadPlaceTxt;
    }

    public TextField getCarrierTxt() {
        return carrierTxt;
    }

    public TextField getCollectionPlaceTxt() {
        return collectionPlaceTxt;
    }

    public TextField getExitHourTxt() {
        return exitHourTxt;
    }

    public TextField getArriveHourTxt() {
        return arriveHourTxt;
    }

    public TextField getDeliveryPlaceTxt() {
        return deliveryPlaceTxt;
    }

    public ComboBox<?> getTreasuryStateCb() {
        return treasuryStateCb;
    }

    public TextField getShippingTxt() {
        return shippingTxt;
    }

    public TextField getBoatTxt() {
        return boatTxt;
    }

    public TextArea getObservationsTxt() {
        return observationsTxt;
    }

    public GridPane getTravelGp() {
        return travelGp;
    }

    public CheckBox getContainerStateCkb() {
        return containerStateCkb;
    }

    public TextField getProvidedHourTxt() {
        return providedHourTxt;
    }

    public TextField getReferenceTxt() {
        return referenceTxt;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public Date getDate() {
        Calendar cal = getUtils().getDatewithFormat(getDateTxt().getText());
        if (getProvidedHourTxt().getText().equals("")) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 1);
        } else {
            String hourMin = getProvidedHourTxt().getText();
            if (!hourMin.equals("")) {
                String[] hhMM = hourMin.split(":");
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhMM[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(hhMM[1]));
            }
            ;
        }
        return cal.getTime();
    }

    private void fillIVACb() {
        //method that fill combobox of if the iva is paid or not:
        ObservableList ivaOp = FXCollections.observableArrayList("Abonado por cliente", "No abonado por cliente");
        getIvaCb().setItems(ivaOp);
    }

    private void fillPaymentCb() {
        //method that fills combos of the state of payments to drivers and the payment of clients
        ObservableList paymentStateOp = FXCollections.observableArrayList("Pagado", "Pendiente");
        getClientPaymentCb().setItems(paymentStateOp);
        getDriverPaymentCb().setItems(paymentStateOp);
    }

    private void fillTravelTypeCb() {
//method that fills the types of travels that company done:
        ObservableList travelsType = FXCollections.observableArrayList("Exportación", "Terrestre");
        getTravelTypeCb().setItems(travelsType);
    }

    private void fillTreasuryStateCb() {
        //method that fills the state of the  payment of the treasury
        ObservableList treasuryStateLst = FXCollections.observableArrayList("Pendiente", "Pagado", "Ninguno");
        getTreasuryStateCb().setItems(treasuryStateLst);
    }

    private void fillClientsCbAndDispatcherCb() {
        //gets all the clients and fill the combobox
        List<Cliente> findClienteEntities = getUtils().getClienteController().findClienteEntities();
        ObservableList clientsList = FXCollections.observableArrayList();
        ObservableList dispatcherLst = FXCollections.observableArrayList();
        for (Cliente c : findClienteEntities) {
            if (c.getDespachante().equals("D")) {
                dispatcherLst.add(c.getNombre());
            } else {
                clientsList.add(c.getNombre());
            }
        }
        getClientCb().setItems(clientsList);
        getDispatcherCb().setItems(dispatcherLst);
    }

    private void fillDriversCb() {
        //gets drivers and fill the combobox
        List<Transportista> findTransportistaEntities = getUtils().getTruckDriverController().findTransportistaEntities();
        ObservableList driversList = FXCollections.observableArrayList();
        for (Transportista t : findTransportistaEntities) {
            driversList.add(t.getNombre().trim() + " " + t.getApellido1().trim() + " " + t.getApellido2().trim());
        }
        getDriverCb().setItems(driversList);
    }

    public String generateAlbaranNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        Integer year = cal.get(Calendar.YEAR);
        //Integer numberTravels = getUtils().getTravelController().getViajeCount();
        Integer numberTravels = getUtils().getTravelController().getTravelsInYear(year.toString());
        return numberTravels.toString() + "/" + year.toString().substring(2);

    }

    private void setValidators() {
        //method that set the validators of textboxes:
        getUtils().addNoNumbers(getOriginTxt(), getValidatorTxt());
        getUtils().addNoNumbers(getDestinationTxt(), getValidatorTxt());
        getUtils().addNoNumbers(getCollectionPlaceTxt(), getValidatorTxt());
        getUtils().addNoNumbers(getDeliveryPlaceTxt(), getValidatorTxt());
        getUtils().eurosListener(getTravelValueTxt(), getValidatorTxt());
        getUtils().eurosListener(getMoneyforDriverTxt(), getValidatorTxt());
        getUtils().eurosListener(getCustomsTxt(), getValidatorTxt());
        getUtils().eurosListener(getOtherExpensesTxt(), getValidatorTxt());
        getUtils().eurosListener(getShippingExpensesTxt(), getValidatorTxt());
        //No listenerValidator for distance because, finally it is a string
//        getUtils().distanceListener(getDistanceTxt(), getValidatorTxt());
        getUtils().dateValidator(getDateTxt(), getValidatorTxt());
        getUtils().timeListener(getArriveHourTxt(), getValidatorTxt());
        getUtils().timeListener(getExitHourTxt(), getValidatorTxt());
        getUtils().timeListener(getProvidedHourTxt(), getValidatorTxt());

    }

    public void clearFields() {
        ObservableList<Node> children = getTravelGp().getChildren();
        for (Node c : children) {
            if (c instanceof TextField) {
                TextField txt = (TextField) c;
                txt.setText("");
            } else if (c instanceof TextArea) {
                TextArea txt = (TextArea) c;
                txt.setText("");
            } else if (c instanceof ComboBox) {
                ComboBox cmb = (ComboBox) c;
                if (cmb.getSelectionModel() != null) {
                    cmb.getSelectionModel().clearSelection();
                } else if (c instanceof CheckBox) {
                    CheckBox ckb = (CheckBox) c;
                    ckb.setSelected(false);
                }
            }
        }
        //        getDateTxt().setText("");
        //        getTravelValueTxt().setText("");
        //        getMoneyforDriverTxt().setText("");
        //        getDistanceTxt().setText("");
        //        getContainerTxt().setText("");
        //        getSealTxt().setText("");
        //        getContainerTypeTxt().setText("");
        //        getPackageTxt().setText("");
        //        getCommodityTxt().setText("");
        //        getWeightTxt().setText("");
        //        getOriginTxt().setText("");
        //        getDestinationTxt().setText("");
        //        getCollectionPlaceTxt().setText("");
        //        getLoadPlaceTxt().setText("");
        //        getCarrierTxt().setText("");
        //        getArriveHourTxt().setText("");
        //        getExitHourTxt().setText("");
        //        getDeliveryPlaceTxt().setText("");
        //        getPaymentTypeTxt().setText("");
        //        getIvaCb().getSelectionModel().clearSelection();
        //        getClientPaymentCb().getSelectionModel().clearSelection();
        //        getDriverPaymentCb().getSelectionModel().clearSelection();
        //        getClientCb().getSelectionModel().clearSelection();
        //        getDriverCb().getSelectionModel().clearSelection();
        //        getCustomsTxt().setText("");
        //        getOtherExpensesTxt().setText("");
        //        getShippingExpensesTxt().setText("");
        //        getOthersDescriptionTFld().setText("");

    }

    public void setFields(Viaje travel) {
        //set data of the panel in case the user wants edit travel data:
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       
        if (travel.getFecha() != null) {
            getDateTxt().setText(df.format(travel.getFecha()));
            getProvidedHourTxt().setText(getUtils().gethhmm(travel.getFecha()));
        }
        if (travel.getIngreso() != null) {
            getTravelValueTxt().setText(travel.getIngreso().toString());
        }
        if (travel.getPagoACamionero() != null) {
            getMoneyforDriverTxt().setText(travel.getPagoACamionero().toString());
        }
        getDistanceTxt().setText(travel.getKms());
        getContainerTxt().setText(travel.getNumeroContenedor());
        getSealTxt().setText(travel.getPrecinto());
        getContainerTypeTxt().setText(travel.getTipoContenedor());
        if (travel.getReferencia() != null) {
            getReferenceTxt().setText(travel.getReferencia());
        }
        if (travel.getBultos() != null) {
            getPackageTxt().setText(travel.getBultos().toString());
        }
        if (travel.getEstadoContenedor() != null && travel.getEstadoContenedor().equals("lleno")) {
            getContainerStateCkb().setSelected(true);
        } else {
            getContainerStateCkb().setSelected(false);
        }
        getCommodityTxt().setText(travel.getMercancia());
        if (travel.getPeso() != null) {
            getWeightTxt().setText(travel.getPeso().toString());
        }
        if (travel.getOrigen() != null) {
            getOriginTxt().setText(travel.getOrigen());
        }
        if (travel.getDestino() != null) {
            getDestinationTxt().setText(travel.getDestino());
        }
        getCollectionPlaceTxt().setText(travel.getLugarRecogida());
        getLoadPlaceTxt().setText(travel.getLugarCarga());
        getCarrierTxt().setText(travel.getCargador());
        SimpleDateFormat hhmmDf = new SimpleDateFormat("hh:mm");
        if (travel.getHoraLlegada() != null) {
            getArriveHourTxt().setText(getUtils().gethhmm(travel.getHoraLlegada()));
        }
        if (travel.getHoraSalida() != null) {
            getExitHourTxt().setText(getUtils().gethhmm(travel.getHoraSalida()));
        }
        getDeliveryPlaceTxt().setText(travel.getLugarEntrega());
        if (travel.getTipoPago() != null) {
            getPaymentTypeTxt().setText(travel.getTipoPago().toString());
        }

        getShippingExpensesTxt().setText(travel.getGastosNaviera());
        getCustomsTxt().setText(travel.getAduana());
        getOthersDescriptionTFld().setText(travel.getOtrosGastos());
        getOtherExpensesTxt().setText(travel.getCantidadOtros());
        getShippingTxt().setText(travel.getNaviera());
        getBoatTxt().setText(travel.getBuque());
        getObservationsTxt().setText(travel.getObservaciones());

        Transportista driver = travel.getTransportista();
        if (travel.getIva() != null) {
            ObservableList ivaItems = getIvaCb().getItems();
            String iva = "";
            if (travel.getIva().equals("S")) {
                iva = "Abonado por cliente";
            } else if (travel.getIva().equals("N")) {
                iva = "No abonado por cliente";
            }
            for (int i = 0; i < ivaItems.size(); i++) {
                if (iva.equals(ivaItems.get(i))) {
                    getIvaCb().getSelectionModel().select(i);
                }
            }
        }
        if (travel.getEstadoCliente() != null) {
            ObservableList clientPaymentItems = getClientPaymentCb().getItems();
            for (int i = 0; i < clientPaymentItems.size(); i++) {
                if (travel.getEstadoCliente().equals(clientPaymentItems.get(i))) {
                    getClientPaymentCb().getSelectionModel().select(i);
                }
            }
        }
        if (travel.getEstadoTransportista() != null) {
            ObservableList driverPaymentItemsList = getDriverPaymentCb().getItems();
            for (int i = 0; i < driverPaymentItemsList.size(); i++) {
                if (travel.getEstadoTransportista().equals(driverPaymentItemsList.get(i))) {
                    getDriverPaymentCb().getSelectionModel().select(i);
                }
            }
        }
        if (travel.getCliente() != null) {
            ObservableList clientItems = getClientCb().getItems();
            for (int i = 0; i < clientItems.size(); i++) {
                if (travel.getCliente().getNombre().equals(clientItems.get(i))) {
                    getClientCb().getSelectionModel().select(i);
                }
            }
        }
        if (travel.getTransportista() != null) {
            ObservableList driverItems = getDriverCb().getItems();
            String fullDriverName = travel.getTransportista().getNombre().trim() + " " + travel.getTransportista().getApellido1().trim() + " " + travel.getTransportista().getApellido2().trim();
            for (int i = 0; i < driverItems.size(); i++) {
                if (fullDriverName.equals(driverItems.get(i))) {
                    getDriverCb().getSelectionModel().select(i);
                }
            }
        }
        String travelType = travel.getTipoViaje();
        if (travelType != null) {
            ObservableList travelsTypeItems = getTravelTypeCb().getItems();
            if (travelType.equals("T")) {
                travelType = "Terrestre";
            } else if (travel.getTipoViaje().equals("EX")) {
                travelType = "Exportación";
            }
            for (int i = 0; i < travelsTypeItems.size(); i++) {
                if (travelType.equals(travelsTypeItems.get(i))) {
                    getTravelTypeCb().getSelectionModel().select(i);
                }
            }
        }
        if (travel.getEstadoHacienda() != null) {
            ObservableList treasuryStateItems = getTreasuryStateCb().getItems();
            for (int i = 0; i < treasuryStateItems.size(); i++) {
                if (travel.getEstadoHacienda().equals(treasuryStateItems.get(i))) {
                    getTreasuryStateCb().getSelectionModel().select(i);
                }
            }
            if (!travel.getEstadoHacienda().equals("Ninguno")) {
                getTreasuryStateCb().setVisible(true);
            }
        }
        ObservableList dispatcherItems = getDispatcherCb().getItems();
        for (int i = 0; i < dispatcherItems.size(); i++) {
            if (travel.getDespachante() != null && travel.getDespachante().equals(dispatcherItems.get(i))) {
                getDispatcherCb().getSelectionModel().select(i);
            }
        }
    }

    private void addChangeListenerIvaCb() {
        getIvaCb().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> ov, Object t, Object t1) {
                if (getIvaCb().getSelectionModel().getSelectedItem() != null) {
                    String optionSelected = getIvaCb().getSelectionModel().getSelectedItem().toString().trim();
                    if (optionSelected.equals("No abonado por cliente")) {
                        getTreasuryStateCb().visibleProperty().set(true);
                    } else {
                        getTreasuryStateCb().visibleProperty().set(false);
                    }
                }

            }
        });
    }
//    @FXML
//    private void checkTypeOfIva(MouseEvent event) {
//        if (getIvaCb().getSelectionModel().getSelectedItem() != null && getIvaCb().getSelectionModel().getSelectedItem().toString().equals("No abonado por cliente")) {
//            getTreasuryStateCb().visibleProperty().set(true);
//        } else {
//            getTreasuryStateCb().visibleProperty().set(false);
//        }
//    }
}
