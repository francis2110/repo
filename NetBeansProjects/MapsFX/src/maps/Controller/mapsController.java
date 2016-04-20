/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maps.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import utils.Utils;

/**
 *
 * @author fran
 */
public class mapsController implements Initializable {

    @FXML
    private ToggleButton roadBtn;
    @FXML
    private ToggleGroup mapTypeTg;
    @FXML
    private ToggleButton satelliteBtn;
    @FXML
    private ToggleButton hyBridBtn;
    @FXML
    private ToggleButton terrainBtn;
    @FXML
    private TextField locationTxt;
    @FXML
    private Insets x1;
    @FXML
    private WebView mapsWV;
    @FXML
    private Button zoomInBtn;
    @FXML
    private Button zoomOutBtn;
    private Utils utils = null;

    public ToggleButton getRoadBtn() {
        return roadBtn;
    }

    public ToggleGroup getMapTypeTg() {
        return mapTypeTg;
    }

    public ToggleButton getSatelliteBtn() {
        return satelliteBtn;
    }

    public ToggleButton getHyBridBtn() {
        return hyBridBtn;
    }

    public ToggleButton getTerrainBtn() {
        return terrainBtn;
    }

    public TextField getLocationTxt() {
        return locationTxt;
    }

    public WebView getMapsWV() {
        return mapsWV;
    }

    public Button getZoomInBtn() {
        return zoomInBtn;
    }

    public Button getZoomOutBtn() {
        return zoomOutBtn;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final WebEngine webEngine = getMapsWV().getEngine();
        webEngine.load(this.getClass().getResource("/htmlMaps/googlemap.html").toString());
        getUtils().addMapsTypeListener(getMapTypeTg(), webEngine);
        getUtils().addlookForDirectionListener(getLocationTxt(), webEngine);
    }
}
