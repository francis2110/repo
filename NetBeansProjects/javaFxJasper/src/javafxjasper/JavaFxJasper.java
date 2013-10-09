/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxjasper;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author fran
 */
public class JavaFxJasper extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));

        Scene scene = new Scene(root);
        createEmployees();
        stage.setScene(scene);
        stage.show();
    }

    public void createEmployees() {
        LinkedList<Empleado> listaEmpleados = new LinkedList<Empleado>();
        listaEmpleados.add(new Empleado("1234567890", "Juan Pérez", 450));
        listaEmpleados.add(new Empleado("0987654321", "Marcelo C", 500));
        listaEmpleados.add(new Empleado("1234509876", "Don Bill", 5000));
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("pruebaJavaFx.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(listaEmpleados));
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporteEnPdf.pdf"));
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
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