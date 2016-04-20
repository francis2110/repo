/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.utils;

import apponsaresmanagement.jpa.controllers.exceptions.PreexistingEntityException;
import apponsaresmanagement.jpa.entities.Factura;
import apponsaresmanagement.jpa.entities.Viaje;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRCompiler;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fran
 */
public class pdfUtils {
    
    private Utils utils = null;
    
    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public void generateShippingDoc(HashMap hm) throws URISyntaxException, JRException {
        
        String path = getUtils().getoverDistClassPath() + "/reportTemplates/transitaria.jasper";
        createParametersDoc(hm, path);
    }

    public void generateControlDocument(HashMap hm) throws FileNotFoundException, JRException, URISyntaxException {
        String path = getUtils().getoverDistClassPath() + "/reportTemplates/controlDoc.jasper";
        //JasperCompileManager.compileReportToFile(getUtils().getoverDistClassPath() + "/reportTemplates/controlDoc.jrxml", path);
        createParametersDoc(hm, path);
        
    }

    public void createParametersDoc(HashMap hm, String path) throws JRException {
        /**
         method that creates a pdf document from a .jasper template. In this document we only need parameters.
         * @ HashMap hm: contains the data of the document.  path: the path of the jasper template.
         */
        JasperReport report = (JasperReport) JRLoader.loadObject(new File(path));
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, hm, new JREmptyDataSource());
        JasperViewer viewer = new JasperViewer(jasperPrint);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel = (JPanel) viewer.getContentPane();
        frame.setContentPane(panel);
        frame.setSize(800, 600);
        
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    public void generateBill(HashMap hm, ObservableList<Viaje> selectedTravels) throws JRException, PreexistingEntityException, Exception {
        /**
         * generate the bill
         */
        showBill(hm, selectedTravels);
        //fill the fields of bill
        Factura bill = new Factura();
        bill.setFecha(new Date());
        bill.setIdFactura(hm.get("idFactura").toString());
        if (hm.get("tipoFactura").equals("Terrestre")) {
            bill.setSerieFactura("T");
        } else if (hm.get("tipoFactura").equals("Exportación")) {
            bill.setSerieFactura("E");
        }
        bill.setViajeCollection(selectedTravels);
        getUtils().getBillController().create(bill);
        
        for (Viaje t : selectedTravels) {
            t.setFacturaidFactura(bill);
            getUtils().getTravelController().edit(t);
        }
    }
    
    public void showBill(HashMap hm, ObservableList<Viaje> selectedTravels) throws JRException, URISyntaxException {
        /**
         * method that shows the bill in the screen.
         *
         */
        String path = getUtils().getoverDistClassPath() + "reportTemplates/bill.jasper";
        //JasperCompileManager.compileReportToFile(getUtils().getoverDistClassPath() + "/reportTemplates/bill.jrxml", path);
        JasperReport report = (JasperReport) JRLoader.loadObject(new File(path));
        hm.put("billElements", generateTableElements(selectedTravels));
        fillBillSummary(hm, selectedTravels);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, hm, new JREmptyDataSource());
        JasperViewer viewer = new JasperViewer(jasperPrint);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel = (JPanel) viewer.getContentPane();
        frame.setContentPane(panel);
        frame.setSize(800, 600);
        
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    private void fillBillSummary(HashMap hm, ObservableList<Viaje> selectedTravels) {
        /**
         * method that fills summary in report
         *
         * @params hm that contains the data for the report, selectedTravels:
         * travels selected to order
         * @return void
         */
        ObservableList<billElements> travels = (ObservableList<billElements>) hm.get("billElements");
        BigDecimal iva = new BigDecimal("0.00");
        Double totalBase = new Double("0.00");
        
        for (Viaje t : selectedTravels) {
            //if the travel is type 'T' and iva is paid by the client:
//               iva.setScale(2, RoundingMode.HALF_UP);
            if (t.getTipoViaje().equals("T") && t.getIva().equals("S")) {
                iva = iva.add(t.getIngreso().multiply(new BigDecimal(0.21)).divide(new BigDecimal(1.21), 2));
            }
            //sets the id of the bill that travel belongs
        }
        iva = iva.setScale(2, RoundingMode.HALF_UP);
        
        for (billElements b : travels) {
            totalBase = totalBase + Double.parseDouble(b.getImporte());
        }
        DecimalFormat df = new DecimalFormat("#.##");
        hm.put("Base imponible", df.format(totalBase));
        hm.put("IVA", iva.toString());
        
        hm.put("TOTAL", df.format(new Double(iva.toString()) + totalBase));
    }
    
    public ObservableList<billElements> generateTableElements(ObservableList<Viaje> selectedTravels) {
        /**
         * method for set the elements of the bills in the table
         *
         * @params List of selected travels
         */
        ObservableList<billElements> billList = FXCollections.observableArrayList();
        for (Viaje t : selectedTravels) {
            billElements billElement = new billElements();
            billElement.setCodViaje(t.getViajePK().getAlbaran());
            if (t.getTipoViaje().equals("EX")) {
                billElement.setDescripcion("Exportación contenedor: " + t.getNumeroContenedor() + " desde "
                        + t.getOrigen() + " hasta " + t.getDestino() + ".");
                billElement.setImporte(t.getIngreso().toString());
            } else {
                String contNumber = t.getNumeroContenedor();
                if (contNumber != null) {
                    billElement.setDescripcion("Transporte contenedor " + t.getNumeroContenedor() + ". ");
                } else {
                    billElement.setDescripcion("Transporte contenedor.");
                }
                if (t.getIva().equals("S")) {
                    //the client pays iva
                    //we set the amount without iva. The field 'importe' is total money we receive for the travel
                    billElement.setImporte(t.getIngreso().divide(new BigDecimal(1.21), 2).toString());
                } else {
                    billElement.setImporte(t.getIngreso().toString());
                }
            }
            billList.add(billElement);
        }
        return billList;
    }
}
