/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package showsocketgui;

import gui.controller.SampleController;
import java.io.InputStream;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author fran
 */
public class ShowSocketGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getResource("/gui/view/Sample.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();


        fxmlLoader.setLocation(resource);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = FXMLLoader.load(resource);
        AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
        SampleController controller = (SampleController) fxmlLoader.getController();
         final TextArea socketTxt = controller.getSocketTxt();
         
         Label lbl=controller.getLbl();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
//      final serverSocketMonoThread sSocket = new serverSocketMonoThread();
//        Bindings.bindBidirectional(lbl.textProperty(), sSocket.getPrpTxt());
//        
//                socketTxt.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
//                
////                t=sSocket.getTextProp();
//               
//                try {
//                     socketTxt.setText(sSocket.getTextProp());
//                   System.out.println( socketTxt.textProperty().get());
//                  
//                } catch (Exception ex) {
//                    System.err.println(ex.getMessage());
//                }
//
//            }
//        });
//
//        Thread th = new Thread(sSocket);
//        th.setDaemon(true);
//        th.start();
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