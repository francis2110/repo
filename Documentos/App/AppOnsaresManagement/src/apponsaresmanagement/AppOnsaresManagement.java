/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fran
 */
public class AppOnsaresManagement extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/Loggin.fxml"));

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("OnsaresLogistic");
        stage.setScene(scene);
        fixLoginStageSize(stage);
        stage.show();
    }
    //method for fix the size of the stage

    private void fixLoginStageSize(Stage stage) {
        int loginWidth = 600;
        int loginHeight = 400;
        stage.setMinWidth(loginWidth);
        stage.setMaxWidth(loginWidth);
        stage.setMinHeight(loginHeight);
        stage.setMaxHeight(loginHeight);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}