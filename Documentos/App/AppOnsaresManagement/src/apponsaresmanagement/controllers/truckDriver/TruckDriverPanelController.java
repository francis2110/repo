/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.truckDriver;

import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.utils.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author carmen
 */
public class TruckDriverPanelController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField firstSurnameTxt;
    @FXML
    private TextField truckEnrollmentTxt;
    @FXML
    private TextField trailerTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private Text validatorTxt;
    @FXML
    private TextField nifTxt;
    private Utils utils = null;
    @FXML
    private TextField secondSurNameTxt;
    @FXML
    private TextField entidadTxt;
    @FXML
    private TextField officeNumb;
    @FXML
    private TextField controlDigitTxt;
    @FXML
    private TextField numberAccountTxt;
    private boolean nameValidator = true;

    public boolean isNameValidator() {
        return nameValidator;
    }

    public void setNameValidator(boolean nameValidator) {
        this.nameValidator = nameValidator;
    }

    public TextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(TextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public TextField getFirstSurnameTxt() {
        return firstSurnameTxt;
    }

    public void setFirstSurnameTxt(TextField firstSurnameTxt) {
        this.firstSurnameTxt = firstSurnameTxt;
    }

    public TextField getSecondSurNameTxt() {
        return secondSurNameTxt;
    }

    public void setSecondSurNameTxt(TextField secondSurNameTxt) {
        this.secondSurNameTxt = secondSurNameTxt;
    }

    public TextField getTruckEnrollmentTxt() {
        return truckEnrollmentTxt;
    }

    public void setTruckEnrollmentTxt(TextField truckEnrollmentTxt) {
        this.truckEnrollmentTxt = truckEnrollmentTxt;
    }

    public TextField getTrailerTxt() {
        return trailerTxt;
    }

    public void setTrailerTxt(TextField trailerTxt) {
        this.trailerTxt = trailerTxt;
    }

    public TextField getAddressTxt() {
        return addressTxt;
    }

    public void setAddressTxt(TextField addressTxt) {
        this.addressTxt = addressTxt;
    }

    public TextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(TextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    public TextField getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(TextField phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public TextField getNifTxt() {
        return nifTxt;
    }

    public void setNifTxt(TextField nifTxt) {
        this.nifTxt = nifTxt;
    }

    public Text getValidatorTxt() {
        return validatorTxt;
    }

    public void setValidatorTxt(Text validatorTxt) {
        this.validatorTxt = validatorTxt;
    }

    public TextField getEntidadTxt() {
        return entidadTxt;
    }

    public void setEntidadTxt(TextField entidadTxt) {
        this.entidadTxt = entidadTxt;
    }

    public TextField getOfficeNumb() {
        return officeNumb;
    }

    public void setOfficeNumb(TextField officeNumb) {
        this.officeNumb = officeNumb;
    }

    public TextField getControlDigitTxt() {
        return controlDigitTxt;
    }

    public void setControlDigitTxt(TextField controlDigitTxt) {
        this.controlDigitTxt = controlDigitTxt;
    }

    public TextField getNumberAccountTxt() {
        return numberAccountTxt;
    }

    public void setNumberAccountTxt(TextField numberAccountTxt) {
        this.numberAccountTxt = numberAccountTxt;
    }

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getEntidadTxt().setPrefColumnCount(4);
        getOfficeNumb().setPrefColumnCount(4);
        getControlDigitTxt().setPrefColumnCount(2);
        getNumberAccountTxt().setPrefColumnCount(10);
    }

    public void setDriverFields(Transportista t) {
        getNameTxt().setText(t.getNombre());
        getFirstSurnameTxt().setText(t.getApellido1());
        getSecondSurNameTxt().setText(t.getApellido2());
        getTruckEnrollmentTxt().setText(t.getMatriculaTractora());
        getTrailerTxt().setText(t.getMatriculaRemolque());
        getAddressTxt().setText(t.getDirección());
        getEmailTxt().setText(t.getEmail());
        getPhoneTxt().setText(t.getTelefono());
        getNifTxt().setText(t.getNif());
        if (!t.getNumCuenta().equals("")) {
            String accountNumber = t.getNumCuenta().toString();
            getEntidadTxt().setText(accountNumber.substring(0, 4));
            getOfficeNumb().setText(accountNumber.substring(4, 8));
            getControlDigitTxt().setText(accountNumber.substring(8, 10));
            getNumberAccountTxt().setText(accountNumber.substring(10, 20));
        }

    }

    public void deleteFields() {
        getNameTxt().setText("");
        getFirstSurnameTxt().setText("");
        getSecondSurNameTxt().setText("");
        getTruckEnrollmentTxt().setText("");
        getTrailerTxt().setText("");
        getAddressTxt().setText("");
        getEmailTxt().setText("");
        getPhoneTxt().setText("");
        getNifTxt().setText("");
        deleteAccountTextFields();
    }

    public void setValidators() {
        if (nameValidator) {
            getUtils().addNoNumbers(getNameTxt(), validatorTxt);
        }
        getUtils().addNoNumbers(getFirstSurnameTxt(), validatorTxt);
        getUtils().addNoNumbers(getSecondSurNameTxt(), validatorTxt);
        getUtils().addEmailListener(getEmailTxt(), validatorTxt);
        getUtils().addNumericListener(getPhoneTxt(), validatorTxt);
        if (nameValidator) {
            getUtils().addCIFTruckDriverListener(getNifTxt(), validatorTxt);
        }
        getUtils().notNullTextField(getEmailTxt(), validatorTxt);
        getUtils().notNullTextField(getNameTxt(), validatorTxt);
        getUtils().addNumericListener(getEntidadTxt(), validatorTxt);
        getUtils().addNumericListener(getOfficeNumb(), validatorTxt);
        getUtils().addNumericListener(getControlDigitTxt(), validatorTxt);
        getUtils().addNumericListener(getNumberAccountTxt(), validatorTxt);
        getUtils().numberCountListener(getEntidadTxt(), validatorTxt, 4);
        getUtils().numberCountListener(getOfficeNumb(), validatorTxt, 4);
        getUtils().numberCountListener(getControlDigitTxt(), validatorTxt, 2);
        getUtils().numberCountListener(getNumberAccountTxt(), validatorTxt, 10);
    }

    public String formNumberAccount() {
        //unifies the number of account
        return getEntidadTxt().getText() + getOfficeNumb().getText() + getControlDigitTxt().getText() + getNumberAccountTxt().getText();
    }

    public void deleteAccountTextFields() {
        getEntidadTxt().setText("");
        getOfficeNumb().setText("");
        getControlDigitTxt().setText("");
        getNumberAccountTxt().setText("");
    }

    @FXML
    public void limitNumberCharacters(KeyEvent event) {
        TextField txt = (TextField) event.getSource();

        if (KeyEvent.KEY_RELEASED == event.getEventType()) {
            if (txt == entidadTxt || txt == officeNumb) {
                //limit 4 characters:
                int charlimit = 4;
                verify(charlimit, txt);
            } else if (txt == controlDigitTxt) {
                //limit 2 characters:
                int charlimit = 2;
                verify(charlimit, txt);
            } else if (txt == numberAccountTxt) {
                //limit 10 characters:
                int charIimit = 10;
                verify(charIimit, txt);
            }
        }
    }

    public void verify(int limit, TextField txt) {
        //method that limits the number of characters of textfield
        if (txt.getText().length() > limit) {
            txt.setText(txt.getText().substring(0, limit));
        }
    }
}
