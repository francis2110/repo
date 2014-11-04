package com.mycompany.serialcommunicationard;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class FXMLController implements Initializable {

    @FXML
    private Button blueLEDBttn;
    @FXML
    private Button redLEDBttn;
    @FXML
    private ComboBox<String> portsCmb;
    SerialCom serialUtils;
    @FXML
    private Button startCommBttn;
    final static int blueLedOff = 0, blueLedOn = 1, redLedOff = 2, redLedOn = 3;
    //indicates if leds are turn on or not
    boolean blueLed = false, redLed = false;

    boolean initOK;

    @FXML
    private Button closeCmmBttn;

    public Button getBlueLEDBttn() {
        return blueLEDBttn;
    }

    public void setBlueLEDBttn(Button blueLEDBttn) {
        this.blueLEDBttn = blueLEDBttn;
    }

    public Button getRedLEDBttn() {
        return redLEDBttn;
    }

    public void setRedLEDBttn(Button redLEDBttn) {
        this.redLEDBttn = redLEDBttn;
    }

    public ComboBox<String> getPortsCmb() {
        return portsCmb;
    }

    public void setPortsCmb(ComboBox<String> portsCmb) {
        this.portsCmb = portsCmb;
    }

    public Button getStartCommBttn() {
        return startCommBttn;
    }

    public void setStartCommBttn(Button starCommBttn) {
        this.startCommBttn = starCommBttn;
    }

    public boolean isInitOK() {
        return initOK;
    }

    public void setInitOK(boolean initOK) {
        this.initOK = initOK;
    }

    public Button getCloseCmmBttn() {
        return closeCmmBttn;
    }

    public void setCloseCmmBttn(Button closeCmmBttn) {
        this.closeCmmBttn = closeCmmBttn;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getBlueLEDBttn().setTextFill(Color.WHITE);
        getRedLEDBttn().setTextFill(Color.WHITE);
        serialUtils = new SerialCom();
        serialUtils.initialize();
//fill the combobox with available ports
        Enumeration portEnum = serialUtils.getPortEnum();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            System.out.println(currPortId.getName());
            getPortsCmb().getItems().add(currPortId.getName());
        }

    }

    @FXML
    private void blueButtonAction(ActionEvent event) {
        ImageView iv = new ImageView();
        iv.setFitHeight(70);
        iv.setFitWidth(60);

        iv.autosize();
        try {
            if (blueLed) {
                //turn off led
                serialUtils.getOutput().write(blueLedOff);
                iv.setImage(new Image("/images/lightOFF.jpg"));
                getBlueLEDBttn().setGraphic(iv);
                blueLed = false;
            } else if (!blueLed) {
                serialUtils.getOutput().write(blueLedOn);
                iv.setImage(new Image("/images/ledON.jpg"));
                getBlueLEDBttn().setGraphic(iv);

                blueLed = true;
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void redLEDAction(ActionEvent event) {
        ImageView iv = new ImageView();
        iv.setFitHeight(70);
        iv.setFitWidth(60);
        try {
            if (redLed) {
                serialUtils.getOutput().write(redLedOff);
                iv.setImage(new Image("/images/lightOFF.jpg"));
                getRedLEDBttn().setGraphic(iv);

                redLed = false;
            } else if (!redLed) {
                serialUtils.getOutput().write(redLedOn);
                iv.setImage(new Image("/images/ledON.jpg"));
                getRedLEDBttn().setGraphic(iv);

                redLed = true;
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void portSelection(ActionEvent event) throws NoSuchPortException {
        //  serialUtils.findPort(getPortsCmb().getSelectionModel().getSelectedItem());
        serialUtils.setPortId(CommPortIdentifier.getPortIdentifier(getPortsCmb().getSelectionModel().getSelectedItem()));
        if (serialUtils.getPortId() != null) {
            getStartCommBttn().setDisable(false);
        } else {
            getStartCommBttn().setDisable(true);
        }
    }

    @FXML
    private void startBttnAction(ActionEvent event) {
        setInitOK(serialUtils.initPortCommunication());
        if (isInitOK()) {
            getStartCommBttn().setDisable(true);
            getCloseCmmBttn().setDisable(false);
        } else {
            getStartCommBttn().setDisable(false);
            getCloseCmmBttn().setDisable(true);
        }

    }

    @FXML
    private void closeBttnAction(ActionEvent event) {
        serialUtils.close();
        getCloseCmmBttn().setDisable(true);
        getStartCommBttn().setDisable(false);
    }
}
