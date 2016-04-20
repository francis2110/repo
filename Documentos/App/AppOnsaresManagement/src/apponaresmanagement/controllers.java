/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponaresmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author fran <fraordia@gmail.com>
 */
public class controllers implements Initializable {
    @FXML
    private Label contentLbl;
    @FXML
    private Text titleTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setContentLbl(Label contentLbl) {
        this.contentLbl = contentLbl;
    }

    public void setTitleTxt(Text titleTxt) {
        this.titleTxt = titleTxt;
    }

    
    
}
