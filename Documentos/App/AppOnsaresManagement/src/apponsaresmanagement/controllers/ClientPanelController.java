/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers;

import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.utils.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author carmen
 */
public class ClientPanelController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Font x1;
    @FXML
    private Color x2;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField clientIdTxt;
    @FXML
    private TextField numMovTxt;
    @FXML
    private TextField numFijoTxt;
    @FXML
    private TextField streetTxt;
    @FXML
    private TextField numberTxt;
    @FXML
    private TextField townTxt;
    @FXML
    private TextField provTxt;
    @FXML
    private TextField cpTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField contactTxt;
    @FXML
    private Text validator;
    private Utils utils = null;
    //property that manages if the validator of nameTxt is active or not
    private boolean nameValidator = true;
    @FXML
    private CheckBox dispatcherCbx;

    public Text getValidator() {
        return validator;
    }

    public void setValidator(Text validator) {
        this.validator = validator;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public TextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(TextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public TextField getClientIdTxt() {
        return clientIdTxt;
    }

    public void setClientIdTxt(TextField clientIdTxt) {
        this.clientIdTxt = clientIdTxt;
    }

    public TextField getNumMovTxt() {
        return numMovTxt;
    }

    public void setNumMovTxt(TextField numMovTxt) {
        this.numMovTxt = numMovTxt;
    }

    public TextField getNumFijoTxt() {
        return numFijoTxt;
    }

    public void setNumFijoTxt(TextField numFijoTxt) {
        this.numFijoTxt = numFijoTxt;
    }

    public TextField getStreetTxt() {
        return streetTxt;
    }

    public void setStreetTxt(TextField streetTxt) {
        this.streetTxt = streetTxt;
    }

    public TextField getNumberTxt() {
        return numberTxt;
    }

    public void setNumberTxt(TextField numberTxt) {
        this.numberTxt = numberTxt;
    }

    public TextField getTownTxt() {
        return townTxt;
    }

    public void setTownTxt(TextField townTxt) {
        this.townTxt = townTxt;
    }

    public TextField getProvTxt() {
        return provTxt;
    }

    public void setProvTxt(TextField provTxt) {
        this.provTxt = provTxt;
    }

    public TextField getCpTxt() {
        return cpTxt;
    }

    public void setCpTxt(TextField cpTxt) {
        this.cpTxt = cpTxt;
    }

    public TextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(TextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    public TextField getContactTxt() {
        return contactTxt;
    }

    public void setContactTxt(TextField contactTxt) {
        this.contactTxt = contactTxt;
    }

    public CheckBox getDispatcherCbx() {
        return dispatcherCbx;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public void setClient(Cliente client) {
        //sets the values of Cliente object with controls of the panel:
        client.setNombre(getNameTxt().getText().trim());
        client.setCifNif(getClientIdTxt().getText().trim());
        client.setMovil(getNumMovTxt().getText().trim());
        client.setFijo(getNumFijoTxt().getText().trim());
        client.setEmail(getEmailTxt().getText().trim());
        client.setContacto(getContactTxt().getText().trim());
        client.setCalle(getStreetTxt().getText().trim());
        client.setNumero(getNumberTxt().getText().trim());
        client.setPoblacion(getTownTxt().getText().trim());
        client.setProvincia(getProvTxt().getText().trim());
        client.setCodigoPostal(Integer.parseInt(getCpTxt().getText().trim()));
        if (getDispatcherCbx().isSelected()) {
            client.setDespachante("D");
        } else {
            client.setDespachante("N");
        }

    }

    public void setClientFields(Cliente client) {
        //method for set the values of textboxes:
        getNameTxt().setText(client.getNombre());
        getClientIdTxt().setText(client.getCifNif());
        getNumMovTxt().setText(client.getMovil());
        getNumFijoTxt().setText(client.getFijo());
        getEmailTxt().setText(client.getEmail());
        getContactTxt().setText(client.getContacto());
        getStreetTxt().setText(client.getCalle());
        getNumberTxt().setText(client.getNumero());
        getTownTxt().setText(client.getPoblacion());
        getProvTxt().setText(client.getProvincia());
        getCpTxt().setText(Integer.toString(client.getCodigoPostal()));
        if (client.getDespachante() != null) {
            if (client.getDespachante().equals("D")) {
                getDispatcherCbx().setSelected(true);
            } else if (client.getDespachante().equals("N")) {
                getDispatcherCbx().setSelected(false);
            }
        }
    }

    public void deleteClientFields() {
        getNameTxt().setText("");
        getClientIdTxt().setText("");
        getNumMovTxt().setText("");
        getNumFijoTxt().setText("");
        getEmailTxt().setText("");
        getContactTxt().setText("");
        getStreetTxt().setText("");
        getNumberTxt().setText("");
        getTownTxt().setText("");
        getProvTxt().setText("");
        getCpTxt().setText("");
        getDispatcherCbx().setSelected(false);

    }

    public boolean isNameValidator() {
        return nameValidator;
    }

    public void setNameValidator(boolean nameValidator) {
        this.nameValidator = nameValidator;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setValidators() {
        //name validator
        if (isNameValidator()) {
            //if is active we set the validator
            getUtils().addnameTxtListener(getNameTxt(), validator);
            //nif/cif validator
            getUtils().addclientIdTxtListener(getClientIdTxt(), validator);
        }
        if (!isNameValidator()) {
            //set the nameTxt and cifNIFtxt as not editable:
            getNameTxt().setEditable(false);
            getClientIdTxt().setEditable(false);
        }

        //validator num, mobile, home, CP
        getUtils().addNumericListener(getNumFijoTxt(), validator);
        getUtils().addNumericListener(getNumMovTxt(), validator);
        getUtils().addNumericListener(getNumberTxt(), validator);
        getUtils().addNumericListener(getCpTxt(), validator);

        getUtils().addEmailListener(getEmailTxt(), validator);

        getUtils().addNoNumbers(getContactTxt(), validator);
        getUtils().addNoNumbers(getTownTxt(), validator);
        getUtils().addNoNumbers(getProvTxt(), validator);
    }
}
