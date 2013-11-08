/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class TravelColumnsVisibilityController implements Initializable {

    @FXML
    private CheckBox travelDateckb;
    @FXML
    private CheckBox clientPaymentckb;
    @FXML
    private CheckBox driverPaymentCkb;
    @FXML
    private CheckBox distanceCkb;
    @FXML
    private CheckBox containerNumberCkb;
    @FXML
    private CheckBox sealCkb;
    @FXML
    private CheckBox containerTypeCkb;
    @FXML
    private CheckBox packageCkb;
    @FXML
    private CheckBox commodityCkb;
    @FXML
    private CheckBox weightCkb;
    @FXML
    private CheckBox originCkb;
    @FXML
    private CheckBox destinationCkb;
    @FXML
    private CheckBox travelTypeCkb;
    @FXML
    private CheckBox collectionPlaceCkb;
    @FXML
    private CheckBox loadPlaceCkb;
    @FXML
    private CheckBox carrierCkb;
    @FXML
    private CheckBox arriveHourCkb;
    @FXML
    private CheckBox exitHourCkb;
    @FXML
    private CheckBox deliveryPlaceCkb;
    @FXML
    private CheckBox paymentTypeCkb;
    @FXML
    private CheckBox ivaCkb;
    @FXML
    private CheckBox treasuryStateCkb;
    @FXML
    private CheckBox clientCkb;
    @FXML
    private CheckBox driverCkb;
    @FXML
    private CheckBox othersCkb;
    @FXML
    private CheckBox customCkb;
    @FXML
    private CheckBox shippingExpensesCkb;
    @FXML
    private CheckBox shippingCkb;
    @FXML
    private CheckBox boatCkb;
    @FXML
    private CheckBox dispatcherCkb;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private CheckBox clientPaymentStateCkb;
    @FXML
    private CheckBox driverPaymentStateCkb;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button acceptBtn;
    private HashMap<CheckBox, String> chkboxProp = null;
    private final String date = "date", entry = "entry", driverPayment = "driverPayment", distance = "distance", containerNumber = "containerNumber", seal = "seal", containerType = "containerType";
    private final String pakage = "package", commodity = "commodity", weight = "weight", origin = "origin", destination = "destination", travelType = "travelType", collectionPlace = "collectionPlace",
            loadPlace = "loadPlace", carrier = "carrier", arriveHour = "arriveHour", exitHour = "exitHour", deliveryPlace = "deliveryPlace", paymentType = "paymentType", iva = "iva", treasuryState = "treasuryState",
            clientState = "clientState", driverState = "driverState", client = "client", driver = "driver", shippingExpenses = "shippingExpenses", customs = "customs", otherExpenses = "otherExpenses", shipping = "shipping", boat = "boat",
            dispatcher = "dispatcher", reference = "reference", providedHour = "providedHour";
    @FXML
    private CheckBox referenceCkb;
    @FXML
    private CheckBox providedHourCkb;
    Utils utils = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            setHashMapCkbProp();
            readCheckboxSelected();
        } catch (IOException ex) {
            Logger.getLogger(TravelColumnsVisibilityController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(TravelColumnsVisibilityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CheckBox getTravelDateckb() {
        return travelDateckb;
    }

    public CheckBox getClientPaymentckb() {
        return clientPaymentckb;
    }

    public CheckBox getDriverPaymentCkb() {
        return driverPaymentCkb;
    }

    public CheckBox getDistanceCkb() {
        return distanceCkb;
    }

    public CheckBox getContainerNumberCkb() {
        return containerNumberCkb;
    }

    public CheckBox getSealCkb() {
        return sealCkb;
    }

    public CheckBox getContainerTypeCkb() {
        return containerTypeCkb;
    }

    public CheckBox getPackageCkb() {
        return packageCkb;
    }

    public CheckBox getCommodityCkb() {
        return commodityCkb;
    }

    public CheckBox getWeightCkb() {
        return weightCkb;
    }

    public CheckBox getOriginCkb() {
        return originCkb;
    }

    public CheckBox getDestinationCkb() {
        return destinationCkb;
    }

    public CheckBox getTravelTypeCkb() {
        return travelTypeCkb;
    }

    public CheckBox getCollectionPlaceCkb() {
        return collectionPlaceCkb;
    }

    public CheckBox getLoadPlaceCkb() {
        return loadPlaceCkb;
    }

    public CheckBox getCarrierCkb() {
        return carrierCkb;
    }

    public CheckBox getArriveHourCkb() {
        return arriveHourCkb;
    }

    public CheckBox getExitHourCkb() {
        return exitHourCkb;
    }

    public CheckBox getDeliveryPlaceCkb() {
        return deliveryPlaceCkb;
    }

    public CheckBox getPaymentTypeCkb() {
        return paymentTypeCkb;
    }

    public CheckBox getIvaCkb() {
        return ivaCkb;
    }

    public CheckBox getTreasuryStateCkb() {
        return treasuryStateCkb;
    }

    public CheckBox getClientCkb() {
        return clientCkb;
    }

    public CheckBox getDriverCkb() {
        return driverCkb;
    }

    public CheckBox getOthersCkb() {
        return othersCkb;
    }

    public CheckBox getCustomCkb() {
        return customCkb;
    }

    public CheckBox getShippingExpensesCkb() {
        return shippingExpensesCkb;
    }

    public CheckBox getShippingCkb() {
        return shippingCkb;
    }

    public CheckBox getBoatCkb() {
        return boatCkb;
    }

    public CheckBox getDispatcherCkb() {
        return dispatcherCkb;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public CheckBox getClientPaymentStateCkb() {
        return clientPaymentStateCkb;
    }

    public CheckBox getDriverPaymentStateCkb() {
        return driverPaymentStateCkb;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button getAcceptBtn() {
        return acceptBtn;
    }

    public CheckBox getReferenceCkb() {
        return referenceCkb;
    }

    public CheckBox getProvidedHourCkb() {
        return providedHourCkb;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    private void readCheckboxSelected() throws IOException, URISyntaxException {
        //reads the property file for set checkboxes selected or not in the panel:
        Properties p = new Properties();

        InputStream in = new FileInputStream(getUtils().getoverDistClassPath() + "/properties/travelsTableColVisibility.properties");
        p.load(in);
        Set<String> stringPropertyNames = p.stringPropertyNames();
        ObservableList<String> propertyNames = FXCollections.observableArrayList(stringPropertyNames);
        ObservableList<Node> children = getGridPane().getChildren();
        String propertyValue = "";
        for (Node child : children) {
            for (String name : propertyNames) {
                if (child instanceof CheckBox) {
                    CheckBox ckb = (CheckBox) child;
                    propertyValue = (String) p.get(chkboxProp.get(ckb));
                    if ("true".equals(propertyValue)) {
                        ckb.setSelected(true);
                    } else if ("false".equals(propertyValue)) {
                        ckb.setSelected(false);
                    }
                }
            }
        }
        in.close();
    }

    private void writeColumnsVisibility() throws FileNotFoundException, IOException, URISyntaxException {
        //sets properties of the property file depending on checkboxes selection
        ObservableList<Node> children = getGridPane().getChildren();
        Properties property = new Properties();
        String propertiesPath = getUtils().getoverDistClassPath() + "/properties/travelsTableColVisibility.properties";
        FileInputStream in = new FileInputStream(propertiesPath);
        property.load(in);
        in.close();

        String valueProperty = "";



        FileOutputStream out = new FileOutputStream(propertiesPath);
        for (Node child : children) {
            if (child instanceof CheckBox) {
                CheckBox ckb = (CheckBox) child;
                if (ckb.isSelected()) {
                    valueProperty = "true";
                } else {
                    valueProperty = "false";
                }
                property.setProperty(chkboxProp.get(ckb), valueProperty);

            }
        }
        property.store(out, null);
        out.close();
    }

    private void setHashMapCkbProp() throws IOException {
        //inits the hashmap between checkboxes and string
        chkboxProp = new HashMap<CheckBox, String>();
        chkboxProp.put(getTravelDateckb(), date);
        chkboxProp.put(getProvidedHourCkb(), providedHour);
        chkboxProp.put(getClientPaymentckb(), entry);
        chkboxProp.put(getDriverPaymentCkb(), driverPayment);
        chkboxProp.put(getDistanceCkb(), distance);
        chkboxProp.put(getContainerNumberCkb(), containerNumber);
        chkboxProp.put(getSealCkb(), seal);
        chkboxProp.put(getContainerTypeCkb(), containerType);
        chkboxProp.put(getPackageCkb(), pakage);
        chkboxProp.put(getCommodityCkb(), commodity);
        chkboxProp.put(getReferenceCkb(), reference);
        chkboxProp.put(getWeightCkb(), weight);
        chkboxProp.put(getOriginCkb(), origin);
        chkboxProp.put(getDestinationCkb(), destination);
        chkboxProp.put(getTravelTypeCkb(), travelType);
        chkboxProp.put(getCollectionPlaceCkb(), collectionPlace);
        chkboxProp.put(getLoadPlaceCkb(), loadPlace);
        chkboxProp.put(getCarrierCkb(), carrier);
        chkboxProp.put(getArriveHourCkb(), arriveHour);
        chkboxProp.put(getExitHourCkb(), exitHour);
        chkboxProp.put(getDeliveryPlaceCkb(), deliveryPlace);
        chkboxProp.put(getPaymentTypeCkb(), paymentType);
        chkboxProp.put(getIvaCkb(), iva);
        chkboxProp.put(getTreasuryStateCkb(), treasuryState);
        chkboxProp.put(getClientPaymentStateCkb(), clientState);
        chkboxProp.put(getDriverPaymentStateCkb(), driverState);
        chkboxProp.put(getClientCkb(), client);
        chkboxProp.put(getDriverCkb(), driver);
        chkboxProp.put(getShippingExpensesCkb(), shippingExpenses);
        chkboxProp.put(getCustomCkb(), customs);
        chkboxProp.put(getOthersCkb(), otherExpenses);
        chkboxProp.put(getShippingCkb(), shipping);
        chkboxProp.put(getBoatCkb(), boat);
        chkboxProp.put(getDispatcherCkb(), dispatcher);
    }

    @FXML
    private void setVisibilityforColumns(ActionEvent event) throws FileNotFoundException, IOException, URISyntaxException {
        writeColumnsVisibility();
        Scene scene = getAnchorPane().getScene();
        if (scene != null) {
            Window window = scene.getWindow();
            if (window != null) {
                window.hide();
            }
        }
    }
}
