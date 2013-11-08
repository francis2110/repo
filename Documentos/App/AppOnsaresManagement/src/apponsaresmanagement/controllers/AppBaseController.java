/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers;

import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import javafx.stage.Stage;
import org.eclipse.persistence.internal.helper.SimpleDatabaseType;

/**
 * FXML Controller class
 *
 * @author fran
 */
public class AppBaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    MenuItem nuevoClienteMIt;
    @FXML
    BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ToolBar backupBtn;
    private Utils utils = null;
    @FXML
    private MenuItem newTrailerItm;
    @FXML
    private MenuItem actualTrailerRental;
    @FXML
    private MenuItem oldTrailersItm;
    
    public MenuItem getNuevoClienteMIt() {
        return nuevoClienteMIt;
    }
    
    public BorderPane getBorderPane() {
        return borderPane;
    }
    
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    
    public ToolBar getBackupBtn() {
        return backupBtn;
    }
    
    @FXML
    private void newClient(ActionEvent event) throws IOException {
        //   Parent parent = borderPane.getParent();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/client/newClient.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void seeClient(ActionEvent event) throws IOException {
        //Parent parent = borderPane.getParent();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/client/seeClient.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void editClient(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/client/editClient.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void newTruckDriver(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/truckDriver/newTruckDriver.fxml"));
        borderPane.setCenter(pane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        
       
    }
    
    @FXML
    private void seeDriver(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/truckDriver/seeTruckDriver.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void editDriver(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/truckDriver/editDriver.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void newTravel(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/travels/newTravel.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void lookForTravel(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/travels/SearchTravels.fxml"));
        borderPane.setCenter(pane);
    }
    
    public Utils getUtils() {
        return utils;
    }

//    private void setTravelsKmstoClient(List<Viaje> travels){
//        //find the clients
//        List<Cliente> clients = getUtils().getClienteController().findClienteEntities();
//      for(Cliente c:clients){
//      for(Viaje t: travels){
//      if(c.getCifNif().equals(t.getCliente().getCifNif())){
//      c.setNumViajes(c.getNumViajes()+1);
//      
//      }
//      }
//      }
//    
//    }
    @FXML
    private void backupData(ActionEvent event) throws IOException, InterruptedException {
        String runtimeVersion = com.sun.javafx.runtime.VersionInfo.getRuntimeVersion();
        String dbName = "onsareslog";
        String dbUser = "root";
        String dbPass = "root";
        String executeCmd = "";

        //get the path of the application:
        String path = new File(".").getCanonicalPath();
        //check if the backup for this date already exists:
        //File file = new File(path + "/" + date + "Backup.sql");
        File savedFile = saveBackupInUserLocation();
        //savedFile.renameTo(new File(savedFile.getParentFile(),date+"Backup.sql"));
//                if (savedFile.exists()) {
//                    Stage auxStage = new Stage();
//                    String title = "Copia de seguridad reciente.";
//                    String content = "Ha realizado una copia de seguridad hoy. La aplicación considera suficiente poder generar una copia de seguridad al día..";
//                    AnchorPane panel = getUtils().setPopUpStageOK(title, content);
//                    getUtils().fixAuxWindow(auxStage, panel);
//                    auxStage.show();
//                } else 
        if (savedFile != null && !savedFile.exists()) {
            executeCmd = "mysqldump -u " + dbUser + " -p" + dbPass + " " + dbName + " -r " + savedFile.getAbsolutePath();
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup taken successfully");
            } else {
                System.out.println("Could not take mysql backup");
            }
        }
    }
    
    private File saveBackupInUserLocation() {
        SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy");
        String fileName = df.format(new Date()) + "Backup.sql";
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar copia de seguridad:");
        fileChooser.setInitialFileName(fileName);


        //set extension filter:
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SQL files (*.sql)", "*.sql");
        fileChooser.getExtensionFilters().add(extFilter);
        //fileChooser.setInitialDirectory(file);
        //show save dialog:

        File savedFile = fileChooser.showSaveDialog((Stage) getBorderPane().getScene().getWindow());
        return savedFile;
    }
    
    @FXML
    private void addNewTrailer(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/trailerRent/newTrailerData.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void actualTrailersRentalAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/apponsaresmanagement/views/trailerRent/trailerRental.fxml"));
        borderPane.setCenter(pane);
    }
    
    @FXML
    private void oldTrailersBtnAction(ActionEvent event) {
    }
}
