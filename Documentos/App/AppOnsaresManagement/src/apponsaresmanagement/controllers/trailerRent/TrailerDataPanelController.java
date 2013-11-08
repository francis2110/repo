/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.trailerRent;

import apponsaresmanagement.utils.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class TrailerDataPanelController implements Initializable {

    @FXML
    private TextField trailerPriceTxt;
    @FXML
    private TextField trailerRegisNumberTxt;
    @FXML
    private TextField trailerYearTxt;
        
    private Utils utils = null;
    @FXML
    private Text validatorTxt;
    @FXML
    private TextField trailerPurchaseDateTxt;
    @FXML
    private TextField itvDateTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getUtils().eurosListener(getTrailerPriceTxt(),getValidatorTxt());
        getUtils().addTrailerTxtListener(getTrailerRegisNumberTxt(), getValidatorTxt());
    }

    public TextField getTrailerPriceTxt() {
        return trailerPriceTxt;
    }

    public void setTrailerPriceTxt(TextField trailerPriceTxt) {
        this.trailerPriceTxt = trailerPriceTxt;
    }

    public Text getValidatorTxt() {
        return validatorTxt;
    }

    public void setValidatorTxt(Text validatorTxt) {
        this.validatorTxt = validatorTxt;
    }


    public TextField getTrailerRegisNumberTxt() {
        return trailerRegisNumberTxt;
    }

    public void setTrailerRegisNumberTxt(TextField trailerRegisNumberTxt) {
        this.trailerRegisNumberTxt = trailerRegisNumberTxt;
    }

    public TextField getTrailerYearTxt() {
        return trailerYearTxt;
    }

    public void setTrailerYearTxt(TextField trailerYearTxt) {
        this.trailerYearTxt = trailerYearTxt;
    }

    public TextField getTrailerPurchaseDateTxt() {
        return trailerPurchaseDateTxt;
    }

    public void setTrailerPurchaseDateTxt(TextField trailerPurchaseDateTxt) {
        this.trailerPurchaseDateTxt = trailerPurchaseDateTxt;
    }

    public TextField getItvDateTxt() {
        return itvDateTxt;
    }

    public void setItvDateTxt(TextField itvDateTxt) {
        this.itvDateTxt = itvDateTxt;
    }
    

  
    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }
}
