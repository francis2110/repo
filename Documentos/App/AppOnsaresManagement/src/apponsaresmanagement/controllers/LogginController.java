/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers;

import apponsaresmanagement.jpa.controllers.UsuariosJpaController;
import apponsaresmanagement.jpa.entities.Usuarios;
import apponsaresmanagement.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author fran
 */
public class LogginController implements Initializable {

    private DropShadow shadow = new DropShadow();
    private Utils utils = null;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField userTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Text validatorTxt;
    private UsuariosJpaController usersController = null;

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    private void setupStageSize(Stage primaryStage) {
        primaryStage.setMinWidth(800);
        primaryStage.setMaxWidth(getUtils().getScreenWidth());
        primaryStage.setMinHeight(600);
        primaryStage.setMaxHeight(getUtils().getScreenHeight());
    }

    @FXML
    //method that controls the login of users
    private void loginAction(ActionEvent event) throws IOException {
        Usuarios findUsuarios = getUsersController().findUsuarios(userTxt.getText());
        if (findUsuarios != null) {
            //if user name exists:
            validatorTxt.setText("");
            if (passwordTxt.getText().equals(findUsuarios.getPassword())) {
                //if password is correct:
                Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
                setupStageSize(primaryStage);
                Parent root = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/AppBase.fxml"));
                Scene scene = new Scene(root, 800, 600);
                primaryStage.setScene(scene);
            } else {
                //password is not correct
                validatorTxt.setText("Contraseña incorrecta.");
            }
        } else {
            //user name does not exist
            validatorTxt.setText("No existe un usuario con ese nombre.");
        }


    }
    //method that returns the controller of users

    private UsuariosJpaController getUsersController() {
        if (usersController == null) {
            usersController = new UsuariosJpaController();
        }
        return usersController;
    }

    @FXML
    private void shadowOn(MouseEvent event) {
        loginBtn.setEffect(shadow);
    }

    @FXML
    private void shadowOff(MouseEvent event) {
        loginBtn.setEffect(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
