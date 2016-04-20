/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fran <fraordia@gmail.com>
 */
public class AuxStageController implements Initializable {

    @FXML
    private Label contentLbl;
    @FXML
    private Text titleTxt;
    @FXML
    private Button acceptBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Label getContentLbl() {
        return contentLbl;
    }

    public Text getTitleTxt() {
        return titleTxt;
    }

    @FXML
    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) acceptBtn.getScene().getWindow();
        stage.close();
    }
}
