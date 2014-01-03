/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebEngine;

/**
 *
 * @author fran
 */
public class Utils {
 
    final static String roadTxtId = "roadBtn", satelliteTxtId = "satelliteBtn", hybridTxtId = "hyBridBtn", terrainTxtId = "terrainBtn";

    public void addMapsTypeListener(ToggleGroup toggleGp, final WebEngine webEngine) {
        //Checks phone number format
        toggleGp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                if (t1 != null) {
                    ToggleButton tBtn = (ToggleButton) t1;
                    String tBtnId = tBtn.getId();
                    if (tBtnId.equals(roadTxtId)) {
                        webEngine.executeScript("document.setMapTypeRoad()");
                    } else if (tBtnId.equals(satelliteTxtId)) {
                        webEngine.executeScript("document.setMapTypeSatellite()");
                    } else if (tBtnId.equals(hybridTxtId)) {
                        webEngine.executeScript("document.setMapTypeHybrid()");
                    } else if (tBtnId.equals(terrainTxtId)) {
                        webEngine.executeScript("document.setMapTypeTerrain()");
                    }
                }
            }
        });
    }
    public void addlookForDirectionListener(final TextField StreetTxt, final WebEngine webEngine){
        StreetTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //!t1 implies that the textfield lost focus->doesn't have new value
                if(!t1){
                webEngine.executeScript("document.goToLocation(\""+StreetTxt.getText().trim()+"\")");
                }
            }
        });
    }
    
}
