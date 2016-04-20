/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.jpa.entities.Factura;
import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.utils.Utils;
import apponsaresmanagement.utils.pdfUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class SearchTravelsController implements Initializable {

    @FXML
    private ComboBox<?> searchCriteriaCb;
    @FXML
    private TextField initDateTxt;
    @FXML
    private TextField endDateTxt;
    @FXML
    private TableView<?> travelsTv;
    private AnchorPane auxPane;
    private ClientOptPnlController clientControllerPnl;
    private DriverOptPnlController driverControllerPnl;
    private ContainerOptPnlController containerControllerPnl;
    private BillCodeController billControllerPnl;
    private TravelCodeController travelControllerPnl;
    @FXML
    private GridPane dataLimitsGPn;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private SplitPane splitPane;
    private Button searcnTravelBtn;
    String optSel;
    private Utils utils = null;
    @FXML
    private TableColumn<Viaje, Date> dateCol;
    @FXML
    private TableColumn<Viaje, String> clientCol;
    @FXML
    private TableColumn<Viaje, String> paymentTypeCol;
    @FXML
    private TableColumn<Viaje, String> containerNumberCol;
    @FXML
    private TableColumn<Viaje, String> entryCol;
    @FXML
    private TableColumn<Viaje, String> driverPaymentCol;
    @FXML
    private TableColumn<Viaje, String> distanceCol;
    @FXML
    private TableColumn<Viaje, String> ivaCol;
    @FXML
    private TableColumn<Viaje, String> clientStateCol;
    @FXML
    private TableColumn<Viaje, String> driverStateCol;
    @FXML
    private TableColumn<Viaje, String> driverNameCol;
    @FXML
    private TableColumn<Viaje, String> surname1Col;
    @FXML
    private TableColumn<Viaje, String> surname2Col;
    @FXML
    private TableColumn<Viaje, String> driverCol;
    @FXML
    private Pane dynamicPn;
    @FXML
    private GridPane otherDataGP;
    @FXML
    private Label totalKmsLbl;
    @FXML
    private Label averageTravelKmsLbl;
    @FXML
    private Label totalEntranceLbl;
    @FXML
    private Label averageTravelEntranceLbl;
    @FXML
    private Label gainLbl;
    @FXML
    private Label averageBenefitPercentageLbl;
    @FXML
    private TableColumn<Viaje, String> shippingExpensesColumn;
    @FXML
    private TableColumn<Viaje, String> customsColumn;
    @FXML
    private TableColumn<Viaje, String> otherExpensesColumn;
    @FXML
    private MenuItem visibilityColItm;
    @FXML
    private Button billBtn;
    @FXML
    private Button printBtn;
    @FXML
    private TableColumn<Viaje, String> sealColumn;
    @FXML
    private TableColumn<Viaje, String> containerTypeCol;
    @FXML
    private TableColumn<Viaje, String> packageCol;
    @FXML
    private TableColumn<Viaje, String> commodityCol;
    @FXML
    private TableColumn<Viaje, String> weightCol;
    @FXML
    private TableColumn<Viaje, String> originCol;
    @FXML
    private TableColumn<Viaje, String> destinationCol;
    @FXML
    private TableColumn<Viaje, String> travelTypeCol;
    @FXML
    private TableColumn<Viaje, String> collectionPlaceCol;
    @FXML
    private TableColumn<Viaje, String> loadPlaceCol;
    @FXML
    private TableColumn<Viaje, String> carrierCol;
    @FXML
    private TableColumn<Viaje, Date> arriveHourCol;
    @FXML
    private TableColumn<Viaje, Date> exitHourCol;
    @FXML
    private TableColumn<Viaje, String> deliveryPlaceCol;
    @FXML
    private TableColumn<Viaje, String> treasuryStateCol;
    @FXML
    private TableColumn<Viaje, String> shippingColumn;
    @FXML
    private TableColumn<Viaje, String> boatCol;
    @FXML
    private TableColumn<Viaje, String> dispatcherCol;
    private final String date = "date", entry = "entry", driverPayment = "driverPayment", distance = "distance", containerNumber = "containerNumber", seal = "seal", containerType = "containerType";
    private final String pakage = "package", commodity = "commodity", weight = "weight", origin = "origin", destination = "destination", travelType = "travelType", collectionPlace = "collectionPlace",
            loadPlace = "loadPlace", carrier = "carrier", arriveHour = "arriveHour", exitHour = "exitHour", deliveryPlace = "deliveryPlace", paymentType = "paymentType", iva = "iva", treasuryState = "treasuryState",
            clientState = "clientState", driverState = "driverState", client = "client", driver = "driver", shippingExpenses = "shippingExpenses", customs = "customs", otherExpenses = "otherExpenses", shipping = "shipping", boat = "boat",
            dispatcher = "dispatcher", reference = "reference", providedHour = "providedHour", containerState = "containerState";
    private TravelColumnsVisibilityController colVisibilityController = null;
    private HashMap<String, TableColumn> propTblCol = null;
    @FXML
    private GridPane legendGp;
    @FXML
    private Label colorOKLbl;
    @FXML
    private Label clientDelayColorLbl;
    @FXML
    private Label driverDelayColorLbl;
    @FXML
    private Label notRegisteredColorLbl;
    @FXML
    private Label registeredColorLbl;
    @FXML
    private Label treasuryStateColorLbl;
    @FXML
    private Label closedTravelLbl;
    @FXML
    private Label clientDelayLbl;
    @FXML
    private Label paymentToDriverDelayLbl;
    @FXML
    private Label notRegisteredTravelLbl;
    @FXML
    private Label registeredTravelLbl;
    @FXML
    private Label treasuryStateLbl;
    @FXML
    private TableColumn<Viaje, String> providedHourCol;
    @FXML
    private TableColumn<Viaje, String> referenceCol;
    @FXML
    private TableColumn<Viaje, String> containerStateCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillSearchCriteriaCb();
        chkPropCol();
    }

    public ComboBox<?> getSearchCriteriaCb() {
        return searchCriteriaCb;
    }

    public Pane getDynamicPn() {
        return dynamicPn;
    }

    public TextField getInitDateTxt() {
        return initDateTxt;
    }

    public TextField getEndDateTxt() {
        return endDateTxt;
    }

    public TableView<?> getTravelsTv() {
        return travelsTv;
    }

    public GridPane getDataLimitsGPn() {
        return dataLimitsGPn;
    }

    public AnchorPane getAuxPane() {
        return auxPane;
    }

    public Button getSearcnTravelBtn() {
        return searcnTravelBtn;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public String getOptSel() {
        return optSel;
    }

    public TableColumn<Viaje, Date> getDateCol() {
        return dateCol;
    }

    public void setDateCol(TableColumn<Viaje, Date> dateCol) {
        this.dateCol = dateCol;
    }

    public TableColumn<Viaje, String> getClientCol() {
        return clientCol;
    }

    public void setClientCol(TableColumn<Viaje, String> clientCol) {
        this.clientCol = clientCol;
    }

    public TableColumn<Viaje, String> getPaymentTypeCol() {
        return paymentTypeCol;
    }

    public void setPaymentTypeCol(TableColumn<Viaje, String> paymentTypeCol) {
        this.paymentTypeCol = paymentTypeCol;
    }

    public TableColumn<Viaje, String> getContainerStateCol() {
        return containerStateCol;
    }

    public TableColumn<Viaje, String> getContainerNumberCol() {
        return containerNumberCol;
    }

    public void setContainerNumberCol(TableColumn<Viaje, String> containerNumberCol) {
        this.containerNumberCol = containerNumberCol;
    }

    public TableColumn<Viaje, String> getEntryCol() {
        return entryCol;
    }

    public void setEntryCol(TableColumn<Viaje, String> entryCol) {
        this.entryCol = entryCol;
    }

    public TableColumn<Viaje, String> getDriverPaymentCol() {
        return driverPaymentCol;
    }

    public void setDriverPaymentCol(TableColumn<Viaje, String> driverPaymentCol) {
        this.driverPaymentCol = driverPaymentCol;
    }

    public TableColumn<Viaje, String> getDistanceCol() {
        return distanceCol;
    }

    public void setDistanceCol(TableColumn<Viaje, String> distanceCol) {
        this.distanceCol = distanceCol;
    }

    public TableColumn<Viaje, String> getIvaCol() {
        return ivaCol;
    }

    public void setIvaCol(TableColumn<Viaje, String> ivaCol) {
        this.ivaCol = ivaCol;
    }

    public TableColumn<Viaje, String> getClientStateCol() {
        return clientStateCol;
    }

    public void setClientStateCol(TableColumn<Viaje, String> clientStateCol) {
        this.clientStateCol = clientStateCol;
    }

    public TableColumn<Viaje, String> getDriverStateCol() {
        return driverStateCol;
    }

    public void setDriverStateCol(TableColumn<Viaje, String> driverStateCol) {
        this.driverStateCol = driverStateCol;
    }

    public TableColumn<Viaje, String> getDriverNameCol() {
        return driverNameCol;
    }

    public void setDriverNameCol(TableColumn<Viaje, String> driverNameCol) {
        this.driverNameCol = driverNameCol;
    }

    public TableColumn<Viaje, String> getSurname1Col() {
        return surname1Col;
    }

    public void setSurname1Col(TableColumn<Viaje, String> surname1Col) {
        this.surname1Col = surname1Col;
    }

    public TableColumn<Viaje, String> getSurname2Col() {
        return surname2Col;
    }

    public void setSurname2Col(TableColumn<Viaje, String> surname2Col) {
        this.surname2Col = surname2Col;
    }

    public TableColumn<Viaje, String> getDriverCol() {
        return driverCol;
    }

    public void setDriverCol(TableColumn<Viaje, String> driverCol) {
        this.driverCol = driverCol;
    }

    public TableColumn<Viaje, String> getShippingExpensesColumn() {
        return shippingExpensesColumn;
    }

    public void setShippingExpensesColumn(TableColumn<Viaje, String> shippingExpensesColumn) {
        this.shippingExpensesColumn = shippingExpensesColumn;
    }

    public TableColumn<Viaje, String> getCustomsColumn() {
        return customsColumn;
    }

    public void setCustomsColumn(TableColumn<Viaje, String> customsColumn) {
        this.customsColumn = customsColumn;
    }

    public TableColumn<Viaje, String> getOtherExpensesColumn() {
        return otherExpensesColumn;
    }

    public void setOtherExpensesColumn(TableColumn<Viaje, String> otherExpensesColumn) {
        this.otherExpensesColumn = otherExpensesColumn;
    }

    public GridPane getOtherDataGP() {
        return otherDataGP;
    }

    public void setOtherDataGP(GridPane otherDataGP) {
        this.otherDataGP = otherDataGP;
    }

    public Label getTotalKmsLbl() {
        return totalKmsLbl;
    }

    public void setTotalKmsLbl(Label totalKmsLbl) {
        this.totalKmsLbl = totalKmsLbl;
    }

    public Label getAverageTravelKmsLbl() {
        return averageTravelKmsLbl;
    }

    public void setAverageTravelKmsLbl(Label averageTravelKmsLbl) {
        this.averageTravelKmsLbl = averageTravelKmsLbl;
    }

    public Label getTotalEntranceLbl() {
        return totalEntranceLbl;
    }

    public void setTotalEntranceLbl(Label totalEntranceLbl) {
        this.totalEntranceLbl = totalEntranceLbl;
    }

    public Label getAverageTravelEntranceLbl() {
        return averageTravelEntranceLbl;
    }

    public void setAverageTravelEntranceLbl(Label averageTravelEntranceLbl) {
        this.averageTravelEntranceLbl = averageTravelEntranceLbl;
    }

    public Label getGainLbl() {
        return gainLbl;
    }

    public void setGainLbl(Label gainLbl) {
        this.gainLbl = gainLbl;
    }

    public Label getAverageBenefitPercentageLbl() {
        return averageBenefitPercentageLbl;
    }

    public void setAverageBenefitPercentageLbl(Label averageBenefitPercentageLbl) {
        this.averageBenefitPercentageLbl = averageBenefitPercentageLbl;
    }

    public MenuItem getVisibilityColItm() {
        return visibilityColItm;
    }

    public TableColumn<Viaje, String> getSealColumn() {
        return sealColumn;
    }

    public TableColumn<Viaje, String> getContainerTypeCol() {
        return containerTypeCol;
    }

    public TableColumn<Viaje, String> getPackageCol() {
        return packageCol;
    }

    public TableColumn<Viaje, String> getCommodityCol() {
        return commodityCol;
    }

    public TableColumn<Viaje, String> getWeightCol() {
        return weightCol;
    }

    public TableColumn<Viaje, String> getOriginCol() {
        return originCol;
    }

    public TableColumn<Viaje, String> getDestinationCol() {
        return destinationCol;
    }

    public TableColumn<Viaje, String> getTravelTypeCol() {
        return travelTypeCol;
    }

    public TableColumn<Viaje, String> getCollectionPlaceCol() {
        return collectionPlaceCol;
    }

    public TableColumn<Viaje, String> getLoadPlaceCol() {
        return loadPlaceCol;
    }

    public TableColumn<Viaje, String> getCarrierCol() {
        return carrierCol;
    }

    public TableColumn<Viaje, Date> getArriveHourCol() {
        return arriveHourCol;
    }

    public TableColumn<Viaje, Date> getExitHourCol() {
        return exitHourCol;
    }

    public TableColumn<Viaje, String> getDeliveryPlaceCol() {
        return deliveryPlaceCol;
    }

    public TableColumn<Viaje, String> getTreasuryStateCol() {
        return treasuryStateCol;
    }

    public TableColumn<Viaje, String> getShippingColumn() {
        return shippingColumn;
    }

    public TableColumn<Viaje, String> getBoatCol() {
        return boatCol;
    }

    public TableColumn<Viaje, String> getDispatcherCol() {
        return dispatcherCol;
    }

    public TableColumn<Viaje, String> getProvidedHourCol() {
        return providedHourCol;
    }

    public TableColumn<Viaje, String> getReferenceCol() {
        return referenceCol;
    }

    public GridPane getLegendGp() {
        return legendGp;
    }

    public Button getBillBtn() {
        return billBtn;
    }

    public Button getPrintBtn() {
        return printBtn;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public ClientOptPnlController getClientControllerPnl() {
        return clientControllerPnl;
    }

    public DriverOptPnlController getDriverControllerPnl() {
        return driverControllerPnl;
    }

    public ContainerOptPnlController getContainerControllerPnl() {
        return containerControllerPnl;
    }

    public BillCodeController getBillControllerPnl() {
        return billControllerPnl;
    }

    public TravelCodeController getTravelControllerPnl() {
        return travelControllerPnl;
    }

    private void fillSearchCriteriaCb() {
        //fills option for criteriaCb
        ObservableList criteriaLst = FXCollections.observableArrayList();
        String[] options = {"Cliente", "Transportista", "Contenedor", "Código de factura", "Código de viaje", "Pendientes de pago"};
        criteriaLst.addAll(options);
        getSearchCriteriaCb().setItems(criteriaLst);
    }

    @FXML
    private void setupOptions(ActionEvent event) throws IOException {
        getTravelsTv().setVisible(false);
        getOtherDataGP().setVisible(false);
        //setup controls for the search
        if (getSearchCriteriaCb().getSelectionModel().getSelectedItem() != null) {
            optSel = getSearchCriteriaCb().getSelectionModel().getSelectedItem().toString();
            getDynamicPn().getChildren().clear();
            auxPane = null;
            FXMLLoader fxmlLoader = new FXMLLoader();
            if (optSel.equals("Contenedor")) {
                //puts the controls for container
                URL resource = getClass().getResource("/apponsaresmanagement/views/travels/containerOptPnl.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                auxPane = (AnchorPane) fxmlLoader.load(resource.openStream());
                getDynamicPn().getChildren().add(auxPane);
                containerControllerPnl = fxmlLoader.getController();
            } else if (optSel.equals("Transportista")) {
                //puts the controls for driver
                URL resource = getClass().getResource("/apponsaresmanagement/views/travels/driverOptPnl.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                auxPane = (AnchorPane) fxmlLoader.load(resource.openStream());
                getDynamicPn().getChildren().add(auxPane);
                driverControllerPnl = fxmlLoader.getController();
            } else if (optSel.equals("Cliente")) {
                //puts the controls for client
                URL resource = getClass().getResource("/apponsaresmanagement/views/travels/clientOptPnl.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                auxPane = (AnchorPane) fxmlLoader.load(resource.openStream());
                getDynamicPn().getChildren().add(auxPane);
                clientControllerPnl = fxmlLoader.getController();
            } else if (optSel.equals("Código de factura")) {
                //puts controls for bill code:
                URL resource = getClass().getResource("/apponsaresmanagement/views/travels/billCode.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                auxPane = (AnchorPane) fxmlLoader.load(resource.openStream());
                getDynamicPn().getChildren().add(auxPane);
                billControllerPnl = fxmlLoader.getController();
            } else if (optSel.equals("Código de viaje")) {
                //puts controls for travel code:
                URL resource = getClass().getResource("/apponsaresmanagement/views/travels/travelCode.fxml");
                fxmlLoader.setLocation(resource);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                auxPane = (AnchorPane) fxmlLoader.load(resource.openStream());
                getDynamicPn().getChildren().add(auxPane);
                travelControllerPnl = fxmlLoader.getController();
            } else if (optSel.equals("Pendientes de pago")) {
                //does nothing. The application waits the user clicking the search button
            }
            getDataLimitsGPn().setVisible(true);
        } else {
            getDataLimitsGPn().setVisible(false);
        }
    }

    private boolean emptyDatesFields(Calendar initDate, Calendar endDate) throws IOException {
        //checks for empty  date fields. Returns true if one of them is empty, false in the other case

        if (initDate == null || endDate == null) {
            Stage auxStage = new Stage();
            String title = "Campos vacios";
            String content = "Rellene los campos correspondientes a las fechas. Si no sabe las fechas ponga valores extremos para asegurarse"
                    + "que su viaje está incluido en esa franja.";
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
            return true;
        }
        return false;
    }

    private void findTravels() throws IOException {
        //get values for the search:

        Calendar initDate = null, endDate = null;
        if (!getInitDateTxt().getText().equals("") && !getEndDateTxt().getText().equals("")) {
            initDate = getUtils().getDatewithFormat(getInitDateTxt().getText());
            endDate = getUtils().getDatewithFormat(getEndDateTxt().getText());
        }
        // getTravelsTv().getItems().clear();
        //the list of travels:
        List<Viaje> travelsInPeriod = null;
        if (optSel.equals("Cliente")) {
            //client name:
            if (getClientControllerPnl().getClientCb().getSelectionModel() != null && !emptyDatesFields(initDate, endDate)) {
                String name = getClientControllerPnl().getClientCb().getSelectionModel().getSelectedItem().toString();
                //get the client (the client must have singular name)
                Cliente client = getUtils().getClienteController().findClienteByName(name.trim()).get(0);
                travelsInPeriod = getUtils().getTravelController().getClientTravelsInPeriod(client.getCifNif(), initDate.getTime(), endDate.getTime());
            }
        } else if (optSel.equals("Transportista")) {
            if (getDriverControllerPnl().getDriverCb().getSelectionModel() != null && !emptyDatesFields(initDate, endDate)) {
                Transportista driver = getUtils().getTruckDriverController().findDriverbyNameAndSurname(getDriverControllerPnl().getDriverCb().getSelectionModel().getSelectedItem().toString());
                travelsInPeriod = getUtils().getTravelController().getDriverTravelsinPeriod(driver.getNif(), initDate.getTime(), endDate.getTime());
            }
        } else if (optSel.equals("Contenedor")) {
            String containerNum = getContainerControllerPnl().getContainerTxt().getText().trim();
            travelsInPeriod = getUtils().getTravelController().getTravelsByContainer(containerNum);
        } else if (optSel.equals("Código de factura")) {
            String billCode = getBillControllerPnl().getBillCodeTxt().getText().trim();
            travelsInPeriod = getUtils().getTravelController().getTravelsOfBill(billCode);
        } else if (optSel.equals("Código de viaje")) {
            String travelCode = getTravelControllerPnl().getTravelCodeTxt().getText().trim();
            travelsInPeriod = getUtils().getTravelController().getTravelByAlbaran(travelCode);
        } else if (optSel.equals("Pendientes de pago")) {
            //get unpaid travels
            List<Viaje> travels = getUtils().getTravelController().getUnpaidTravels("Pendiente");
            travelsInPeriod = FXCollections.observableList(travels);
        }
        //populate the table
        if (travelsInPeriod == null) {
            getTravelsTv().setVisible(false);
            getOtherDataGP().setVisible(false);
            getLegendGp().setVisible(false);
        } else if (travelsInPeriod.size() > 0) {
            getTravelsTv().setVisible(true);
            getOtherDataGP().setVisible(true);
            getLegendGp().setVisible(true);
            setupTable(travelsInPeriod);
            calculateData(travelsInPeriod);
        } else if (travelsInPeriod.size() == 0) {
            getTravelsTv().setVisible(false);
            getOtherDataGP().setVisible(false);
            getLegendGp().setVisible(false);
            Stage auxStage = new Stage();
            String title = "Viaje no encontrado.";
            String content = "No hay viajes con los criterios de busqueda seleccionados.";
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
        }
    }

    @FXML
    private void searchTravel(ActionEvent event) throws IOException {
        findTravels();
    }

    private void calculateData(List<Viaje> travels) {
        //calculate data for the driver and the company
        Double totalKms = new Double("0.00");
        //entrance for the company;
        Double totalEntrance = new Double("0.00");
        //all expenses for this travel:
        Double totalPayments = new Double("0.00");
        for (Viaje v : travels) {
            if (!v.getKms().equals("")) {
                totalKms = totalKms + Double.parseDouble(v.getKms().replace(",", "."));
            }
            if (v.getIngreso() != null && !v.getIngreso().toString().equals("")) {
                totalEntrance = totalEntrance + Double.parseDouble(v.getIngreso().toString());
            }
            if (v.getPagoACamionero() != null && !v.getPagoACamionero().equals("")) {
                totalPayments = totalPayments + Double.parseDouble(v.getPagoACamionero().toString());
            }
            if (!v.getAduana().equals("")) {
                totalPayments = totalPayments + Double.parseDouble(v.getAduana());
            }
            if (!v.getCantidadOtros().equals("")) {
                totalPayments = totalPayments + Double.parseDouble(v.getCantidadOtros());
            }
            if (!v.getGastosNaviera().equals("")) {
                totalPayments = totalPayments + Double.parseDouble(v.getGastosNaviera());
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        getTotalKmsLbl().setText(df.format(totalKms).toString() + " km.");
        getTotalEntranceLbl().setText(df.format(totalEntrance).toString() + " €");
        Double averageKms = totalKms / travels.size();
        getAverageTravelKmsLbl().setText(df.format(averageKms).toString() + " km.");
        Double averageEntrance = totalEntrance / travels.size();
        getAverageTravelEntranceLbl().setText(df.format(averageEntrance) + " €");
        Double gain = totalEntrance - totalPayments;
        getGainLbl().setText(df.format(gain).toString() + " €");
        Double benefitPercentage = ((totalEntrance - totalPayments) / totalEntrance) * 100;
        getAverageBenefitPercentageLbl().setText(df.format(benefitPercentage).toString() + " %");

    }

    public void setupTable(List<Viaje> clientTravelsInPeriod) {
        getTravelsTv().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        getDateCol().setCellValueFactory(new PropertyValueFactory<Viaje, Date>("fecha"));
//        getDateCol().setCellFactory(new Callback<TableColumn<Viaje, Date>, TableCell<Viaje, Date>>() {
//            @Override
//            public TableCell<Viaje, Date> call(TableColumn<Viaje, Date> p) {
//                return new TableCell<Viaje, Date>() {
//                    @Override
//                    protected void updateItem(Date item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (!empty) {
//                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                            setText(dateFormat.format(item.getTime()));
//                        }
//                    }
//                };
//
//            }
//        ;"
//        });
        getProvidedHourCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("horaPrevista"));
        getEntryCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("ingreso"));
        getDriverPaymentCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("pagoACamionero"));
        getDistanceCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("kms"));
        getContainerNumberCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("numeroContenedor"));
        getContainerStateCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("estadoContenedor"));
        getSealColumn().setCellValueFactory(new PropertyValueFactory<Viaje, String>("precinto"));
        getContainerTypeCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("tipoContenedor"));
        getPackageCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("bultos"));
        getCommodityCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("mercancia"));
        getWeightCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("peso"));
        getOriginCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("origen"));
        getDestinationCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("destino"));
        getTravelTypeCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("tipoViaje"));
        getReferenceCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("Referencia"));
        //        getTravelTypeCol().setCellFactory(new Callback<TableColumn<Viaje, String>, TableCell<Viaje, String>>() {
//            @Override
//            public TableCell<Viaje, String> call(TableColumn<Viaje, String> p) {
//                return new TableCell<Viaje, String>() {
//                    @Override
//                    protected void updateItem(String t, boolean empty) {
//                        if (!empty) {
//                            if (t.equals("T")) {
//                                setText("Terrestre");
//                            } else if (t.equals("EX")) {
//                                setText("Exporación");
//                            }
//                        }
//                    }
//                };
//            }
//        });
        getCollectionPlaceCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("lugarRecogida"));
        getLoadPlaceCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("lugarCarga"));
        getCarrierCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("cargador"));
        getArriveHourCol().setCellValueFactory(new PropertyValueFactory<Viaje, Date>("horaLlegada"));
//        getArriveHourCol().setCellFactory(new Callback<TableColumn<Viaje, Date>, TableCell<Viaje, Date>>() {
//            @Override
//            public TableCell<Viaje, Date> call(TableColumn<Viaje, Date> p) {
//                return new TableCell<Viaje, Date>() {
//                    @Override
//                    protected void updateItem(Date t, boolean empty) {
//                        super.updateItem(t, empty);
//                        if (!empty) {
//                            setText(getUtils().gethhmm(t));
//                        }
//                    }
//                };
//            }
//        });
        getExitHourCol().setCellValueFactory(new PropertyValueFactory<Viaje, Date>("horaSalida"));
//        getExitHourCol().setCellFactory(new Callback<TableColumn<Viaje, Date>, TableCell<Viaje, Date>>() {
//            @Override
//            public TableCell<Viaje, Date> call(TableColumn<Viaje, Date> p) {
//                return new TableCell<Viaje, Date>() {
//                    @Override
//                    protected void updateItem(Date t, boolean bln) {
//                        if (!bln) {
//                            setText(getUtils().gethhmm(t));
//                        }
//                    }
//                };
//
//
//
//
//            }
//        });
        getDeliveryPlaceCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("lugarEntrega"));
        getPaymentTypeCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("tipoPago"));
        getIvaCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("iva"));
//        getIvaCol().setCellFactory(new Callback<TableColumn<Viaje, String>, TableCell<Viaje, String>>() {
//            @Override
//            public TableCell<Viaje, String> call(TableColumn<Viaje, String> p) {
//                return new TableCell<Viaje, String>() {
//                    @Override
//                    protected void updateItem(String t, boolean bln) {
//                        super.updateItem(t, bln); //To change body of generated methods, choose Tools | Templates.
//                        if (!bln) {
//                            if (t.equals("S")) {
//                                setText("IVA asumido por cliente.");
//                            } else if (t.equals("N")) {
//                                setText("OnsaresLog. asume el IVA.");
//                            }
//                        }
//                    }
//                };
//            }
//        });
        getTreasuryStateCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("estadoHacienda"));
        getClientStateCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("estadoCliente"));
        getDriverStateCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("estadoTransportista"));
        getClientCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("clientName"));
        getDriverNameCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("driverName"));
        getSurname1Col().setCellValueFactory(new PropertyValueFactory<Viaje, String>("driverSurname1"));
        getSurname2Col().setCellValueFactory(new PropertyValueFactory<Viaje, String>("driverSurname2"));
        getShippingExpensesColumn().setCellValueFactory(new PropertyValueFactory<Viaje, String>("gastosNaviera"));
        getCustomsColumn().setCellValueFactory(new PropertyValueFactory<Viaje, String>("aduana"));
        getOtherExpensesColumn().setCellValueFactory(new PropertyValueFactory<Viaje, String>("cantidadOtros"));
        getShippingColumn().setCellValueFactory(new PropertyValueFactory<Viaje, String>("naviera"));
        getBoatCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("buque"));
        getDispatcherCol().setCellValueFactory(new PropertyValueFactory<Viaje, String>("despachante"));



        ObservableList clientTravelsLt = FXCollections.observableList(clientTravelsInPeriod);

        getTravelsTv().setItems(clientTravelsLt);
    }

    private void chkPropCol() {
        //initialize the hashmap with properties and columns:
        propTblCol = new HashMap<String, TableColumn>();
        propTblCol.put(date, getDateCol());
        propTblCol.put(entry, getEntryCol());
        propTblCol.put(driverPayment, getDriverPaymentCol());
        propTblCol.put(distance, getDistanceCol());
        propTblCol.put(containerNumber, getContainerNumberCol());
        propTblCol.put(seal, getSealColumn());
        propTblCol.put(containerType, getContainerTypeCol());
        propTblCol.put(pakage, getPackageCol());
        propTblCol.put(commodity, getCommodityCol());
        propTblCol.put(weight, getWeightCol());
        propTblCol.put(origin, getOriginCol());
        propTblCol.put(destination, getDestinationCol());
        propTblCol.put(travelType, getTravelTypeCol());
        propTblCol.put(collectionPlace, getCollectionPlaceCol());
        propTblCol.put(loadPlace, getLoadPlaceCol());
        propTblCol.put(carrier, getCarrierCol());
        propTblCol.put(arriveHour, getArriveHourCol());
        propTblCol.put(exitHour, getExitHourCol());
        propTblCol.put(deliveryPlace, getDeliveryPlaceCol());
        propTblCol.put(paymentType, getPaymentTypeCol());
        propTblCol.put(iva, getIvaCol());
        propTblCol.put(treasuryState, getTreasuryStateCol());
        propTblCol.put(clientState, getClientStateCol());
        propTblCol.put(driverState, getDriverStateCol());
        propTblCol.put(client, getClientCol());
        propTblCol.put(driver, getDriverCol());
        propTblCol.put(shippingExpenses, getShippingExpensesColumn());
        propTblCol.put(customs, getCustomsColumn());
        propTblCol.put(otherExpenses, getOtherExpensesColumn());
        propTblCol.put(shipping, getShippingColumn());
        propTblCol.put(boat, getBoatCol());
        propTblCol.put(dispatcher, getDispatcherCol());
        propTblCol.put(reference, getReferenceCol());
        propTblCol.put(providedHour, getProvidedHourCol());
        propTblCol.put(containerState, getContainerStateCol());
    }

    private void readColumnsVisible() throws FileNotFoundException, IOException, URISyntaxException {
        //reads the property file for see which column is vivible or not

        String propertiesPath = getUtils().getoverDistClassPath() + "properties/travelsTableColVisibility.properties";
        FileInputStream in = new FileInputStream(propertiesPath);
        Properties p = new Properties();
        p.load(in);
        in.close();
        Set<String> stringPropertyNames = p.stringPropertyNames();
        ObservableList<String> propertyNames = FXCollections.observableArrayList(stringPropertyNames);
        boolean visible = false;
        for (String propertyName : propertyNames) {
            if (p.get(propertyName).equals("true")) {
                visible = true;
            } else {
                visible = false;
            }
            propTblCol.get(propertyName).setVisible(visible);
        }
    }

    @FXML
    private void selectTravelDoubleClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            TableView travelsTb = (TableView) event.getSource();
            ObservableList<Viaje> selectedTravels = travelsTb.getSelectionModel().getSelectedItems();
            //variable for checking if travel is registered or not
            boolean registered = true;
            for (Viaje t : selectedTravels) {
                if (getUtils().getTravelState(t).equals("notRegistered")) {
                    registered = false;
                }
            }
            if (!registered) {
                getBillBtn().setVisible(true);
                getPrintBtn().setVisible(false);
            } else if (registered) {
                getBillBtn().setVisible(false);
                getPrintBtn().setVisible(true);
            }

        }
        if (event.getClickCount() == 2) {
            TableView travelsTb = (TableView) event.getSource();
            Viaje selectedTravel = (Viaje) travelsTb.getSelectionModel().getSelectedItem();
            BorderPane borderPane = (BorderPane) getAnchorPane().getParent();
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = getClass().getResource("/apponsaresmanagement/views/travels/editTravel.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            borderPane.setCenter((AnchorPane) fxmlLoader.load(resource.openStream()));
            EditTravelController controller = fxmlLoader.getController();
            controller.setSelectedTravel(selectedTravel);
            controller.fillTravels();
        }
    }

    @FXML
    private void showColVisibilityStage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/apponsaresmanagement/views/travels/travelColumnsVisibility.fxml");
        fxmlLoader.setLocation(resource);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
        colVisibilityController = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle("Visibilidad de columnas");
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                try {
                    readColumnsVisible();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SearchTravelsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SearchTravelsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(SearchTravelsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
//    private associateColumnswithProperties(){
//        Properties p=new Properties();
//        HashMap<TableColumn, > colAndckb= new HashMap<TableColumn, CheckBox>();
//        colAndckb.put(getDateCol(), colVisibilityController.getTravelDateckb());
//        colAndckb.put(getEntryCol(), colVisibilityController.getClientPaymentCkb());
//    }

    @FXML
    private void printControlDocument(ActionEvent event) throws IOException, FileNotFoundException, JRException, URISyntaxException {
        ObservableList<Viaje> selectedtravels = (ObservableList<Viaje>) getTravelsTv().getSelectionModel().getSelectedItems();

        //if there is more than one travel selected we throw an error message: only one travel can be selected each time
        String title = "", content = "";
        if (selectedtravels.size() > 1) {
            //more than one travel selected
            title = "Mensaje error";
            content = "Más de un viaje seleccionado. Se genera una orden de transporte por viaje.";
        } else if (selectedtravels.size() == 1) {
            //one travel selected:
            Viaje travel = selectedtravels.get(0);
            if (travel.getTipoViaje().equals("T")) {
                HashMap hp = new HashMap();
                String driverName = "";
                if (travel.getDriverName() != null) {
                    driverName = travel.getDriverName();
                }
                if (travel.getDriverSurname1() != null) {
                    driverName = driverName + travel.getDriverSurname1();
                }
                if (travel.getDriverSurname2() != null) {
                    driverName = driverName + travel.getDriverSurname2();
                }
                if (!driverName.equals("")) {
                    hp.put("driver", travel.getDriverName() + " " + travel.getDriverSurname1() + " " + travel.getDriverSurname2());
                }

                if (travel.getFecha() != null) {
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    hp.put("travelDate", df.format(travel.getFecha()));
                    hp.put("travelHour", travel.getHoraPrevista());
//                    SimpleDateFormat hhmmf = new SimpleDateFormat("hh:mm");
//                    hp.put("travelHour", hhmmf.format(travel.getFecha()));
                }

                hp.put("reference", travel.getReferencia());
                hp.put("travelId", travel.getViajePK().getAlbaran());
                hp.put("container", travel.getNumeroContenedor());
                hp.put("precinto", travel.getPrecinto());
                if (travel.getBultos() != null) {
                    hp.put("bultos", travel.getBultos().toString());
                }
                hp.put("mercancia", travel.getMercancia());
                if (travel.getPeso() != null) {
                    hp.put("peso", travel.getPeso().toString());
                }
                hp.put("nif", travel.getTransportista().getNif());
                hp.put("camion", travel.getTransportista().getMatriculaTractora());
                hp.put("remolque", travel.getTransportista().getMatriculaRemolque());
                hp.put("lugarRecogida", travel.getLugarRecogida());
                hp.put("lugarEntrega", travel.getLugarEntrega());
                hp.put("estadoContainer", travel.getEstadoContenedor());
                hp.put("lugarCarga", travel.getLugarCarga());
                hp.put("cargador", travel.getCargador());
                hp.put("observaciones", travel.getObservaciones());
                hp.put("logo", getUtils().getoverDistClassPath() + "reportTemplates/bola.jpg");
                pdfUtils utilsReport = new pdfUtils();
                utilsReport.generateControlDocument(hp);
            } else {
                //this document is only generated for road transport
                title = "Error tipo viaje";
                content = "La orden de transporte únicamente puede configurarse para viajes de transporte terrestre.";
            }
        } else {
            //none travel selected:
            title = "Viaje nulo";
            content = "No ha seleccionado ningún viaje para generar la orden de transporte.";
        }
        if (!content.equals("")) {
            Stage auxStage = new Stage();
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
        }

    }

    @FXML
    private void registerTravel(ActionEvent event) throws IOException, JRException, PreexistingEntityException, Exception {
        /**
         * Action method for register travels. We choose the travels we want and
         * then they are related to bill
         */
        ObservableList<Viaje> selectedtravels = (ObservableList<Viaje>) getTravelsTv().getSelectionModel().getSelectedItems();
        boolean ok = true;
        String client = selectedtravels.get(0).getClientName();
        for (int i = 1; i < selectedtravels.size(); i++) {
            if (!selectedtravels.get(i).getClientName().equals(selectedtravels.get(i - 1).getClientName())) {
                //we do the bill for the same client and then:
                ok = false;
                String title = "Error en factura";
                String content = "Los viajes seleccionados deben pertenecer al mismo cliente.";
                Stage auxStage = new Stage();
                AnchorPane panel = getUtils().setPopUpStageOK(title, content);
                getUtils().fixAuxWindow(auxStage, panel);
                auxStage.show();
                break;
            }
        }
        for (Viaje t : selectedtravels) {
            //we check that entry amount is set in travel
            if (t.getIngreso() == null) {
                ok = false;
                String title = "Error en factura";
                String content = "Los viajes seleccionados deben tener cumplimentado el campo 'Ingreso'.";
                Stage auxStage = new Stage();
                AnchorPane panel = getUtils().setPopUpStageOK(title, content);
                getUtils().fixAuxWindow(auxStage, panel);
                auxStage.show();
                break;
            }
            if (t.getFacturaidFactura() != null) {
                //if the travel belongs to other order can not be ordered in other bill:
                ok = false;
                String title = "Error en factura";
                String content = "Alguno de los viajes seleccionado está incluido en otra factura.";
                Stage auxStage = new Stage();
                AnchorPane panel = getUtils().setPopUpStageOK(title, content);
                getUtils().fixAuxWindow(auxStage, panel);
                auxStage.show();
                break;
            }
        }
        //if selected travels are ok
        if (ok) {
            boolean export = false;
            HashMap hm = new HashMap();
            hm.put("idFactura", getUtils().generateBillNumber());
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            hm.put("fecha", df.format(now));
            for (Viaje t : selectedtravels) {
                if (t.getTipoViaje().equals("EX")) {
                    export = true;
                    break;
                }
            }
            if (export) {
                hm.put("tipoFactura", "Exportación");
            } else {
                hm.put("tipoFactura", "Terrestre");
            }
            hm.put("nombreEmpresa", selectedtravels.get(0).getClientName());
            hm.put("direccion", "c/ " + selectedtravels.get(0).getCliente().getCalle() + ", " + " nº " + selectedtravels.get(0).getCliente().getNumero());
            hm.put("CP", selectedtravels.get(0).getCliente().getCodigoPostal().toString());
            hm.put("poblacion", selectedtravels.get(0).getCliente().getPoblacion());
            hm.put("provincia", selectedtravels.get(0).getCliente().getProvincia());
            hm.put("cif", selectedtravels.get(0).getCliente().getCifNif());
            //get the path of the logo:

            String propertiesPath = getUtils().getoverDistClassPath() + "reportTemplates/bola.jpg";
            hm.put("logo", propertiesPath);
            pdfUtils pdfUtils = new pdfUtils();
            pdfUtils.generateBill(hm, selectedtravels);

            boolean visible = getDestinationCol().isVisible();
            if (visible) {
                getDestinationCol().setVisible(false);
            } else {
                getDestinationCol().setVisible(true);
            }
        }
    }

    @FXML
    private void viewBill(ActionEvent event) throws IOException, JRException, PreexistingEntityException, Exception {
        /**
         * Action that hapens when the user wants to view the bill that contains
         * the selected travel. The user has to select only one travel. In other
         * case, the application throws a message.
         *
         * @params: ActionEvent when the user push the button.
         */
        if (getTravelsTv().getSelectionModel().getSelectedItems().size() > 1) {
            String title = "Error en selección de viajes";
            String content = "Para visualizar una factura previamente generada seleccionamos un único viaje. La factura"
                    + " que contiene este viaje se mostrará con todos los viajes que incluye.";
            Stage auxStage = new Stage();
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
        } else if (getTravelsTv().getSelectionModel().getSelectedItems().size() == 1) {
            //create a hashmap with the data of the bill:
            Viaje selectedTravel = (Viaje) getTravelsTv().getSelectionModel().getSelectedItem();
            HashMap hm = new HashMap();
            hm.put("idFactura", selectedTravel.getFacturaidFactura().getIdFactura());
            Factura bill = getUtils().getBillController().findFactura(selectedTravel.getFacturaidFactura().getIdFactura());
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            hm.put("fecha", df.format(bill.getFecha()));
            if (bill.getSerieFactura().equals("T")) {
                hm.put("tipoFactura", "Terrestre");
            } else if (bill.getSerieFactura().equals("E")) {
                hm.put("tipoFactura", "Exportación");
            }
            hm.put("nombreEmpresa", selectedTravel.getClientName());
            hm.put("direccion", "c/ " + selectedTravel.getCliente().getCalle() + ", " + " nº " + selectedTravel.getCliente().getNumero());
            hm.put("CP", selectedTravel.getCliente().getCodigoPostal().toString());
            hm.put("poblacion", selectedTravel.getCliente().getPoblacion());
            hm.put("provincia", selectedTravel.getCliente().getProvincia());
            hm.put("cif", selectedTravel.getCliente().getCifNif());
            String propertiesPath = getUtils().getoverDistClassPath() + "reportTemplates/bola.jpg";
            hm.put("logo", propertiesPath);
            pdfUtils pdfUtils = new pdfUtils();
            List<Viaje> travelsOfBill = getUtils().getTravelController().getTravelsOfBill((String) hm.get("idFactura"));
            pdfUtils.showBill(hm, FXCollections.observableList(travelsOfBill));
        }
    }

    @FXML
    private void printShippingDoc(ActionEvent event) throws IOException, URISyntaxException, JRException {
        /**
         * Action that hapens when the user wants to create the document for the
         * 'transitaria' that contains the selected travel. The user has to
         * select only one travel. In other case, the application throws a
         * message.
         *
         * @params: ActionEvent when the user push the button.
         */
        if (getTravelsTv().getSelectionModel().getSelectedItems().size() > 1) {
            String title = "Error en selección de viajes";
            String content = "Para visualizar el documento de la transitaria seleccionamos un único viaje. El documento"
                    + " es para un único viaje.";
            Stage auxStage = new Stage();
            AnchorPane panel = getUtils().setPopUpStageOK(title, content);
            getUtils().fixAuxWindow(auxStage, panel);
            auxStage.show();
        } else if (getTravelsTv().getSelectionModel().getSelectedItems().size() == 1) {

            Viaje travel = (Viaje) getTravelsTv().getSelectionModel().getSelectedItem();
            if (travel.getFacturaidFactura() == null || travel.getViajePK().getAlbaran() == null) {
                String title = "Error en selección de viajes";
                String content = "El viaje selccionado para cumplimientar el documento de la transitaria debe estar registrado y"
                        + " facturado.";
                Stage auxStage = new Stage();
                AnchorPane panel = getUtils().setPopUpStageOK(title, content);
                getUtils().fixAuxWindow(auxStage, panel);
                auxStage.show();
            } else {
                HashMap hm = new HashMap();
                hm.put("idViaje", travel.getViajePK().getAlbaran());
                hm.put("idFactura", travel.getFacturaidFactura().getIdFactura());
                hm.put("tipo", travel.getTipoContenedor());
                hm.put("naviera", travel.getNaviera());
                hm.put("buque", travel.getBuque());
                hm.put("origen", travel.getOrigen());
                hm.put("destino", travel.getDestino());
                hm.put("cliente", travel.getClientName());
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                hm.put("fecha", df.format(travel.getFecha()));
                hm.put("lugar", travel.getLugarCarga());
                hm.put("despachante", travel.getDespachante());
                hm.put("observaciones", travel.getObservaciones());
                new pdfUtils().generateShippingDoc(hm);
            }
        }
    }
}
