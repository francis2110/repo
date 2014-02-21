/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import showsocketgui.serverSocketMonoThread;

/**
 *
 * @author fran
 */
public class SampleController implements Initializable {
    
    @FXML
    private TextArea socketTxt;
    @FXML
    private Label lbl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serverSocketMonoThread sSocket = new serverSocketMonoThread();
        
        sSocket.getPrpTxt().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                try {
                    final String value = t1;
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            getSocketTxt().setText(value);
//                        }
//                    });
                    getSocketTxt().setText(getSocketTxt().getText()+" " +t1);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
                
            }
        });
        getSocketTxt().setText("Escuchando al socket...");
        Thread th = new Thread(sSocket);
        th.setDaemon(true);
        th.start();
    }
    
    public TextArea getSocketTxt() {
        return socketTxt;
    }
    
    public void setSocketTxt(TextArea socketTxt) {
        this.socketTxt = socketTxt;
    }
    
    public Label getLbl() {
        return lbl;
    }
    
    public void setLbl(Label lbl) {
        this.lbl = lbl;
    }
}
