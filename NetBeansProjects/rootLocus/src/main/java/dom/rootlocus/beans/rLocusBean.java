/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom.rootlocus.beans;

import dom.rootlocus.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author fran
 */
@Named(value = "rLocusBean")
@RequestScoped
public class rLocusBean{

    private String pNum, pDen, sNum, sDen;
    private PolynomialFunction proNum, proDen, sensNum, sensDen;
    private Utils utils;
    private ArrayList<LineChartSeries> plotData;
    private LineChartModel model;

    @PostConstruct
    public void init() {
        System.out.println("Initializing rLocusBean.");
        utils = new Utils();
        plotData = new ArrayList<>();
        model = new LineChartModel();
    }

    public LineChartModel getModel() {
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }

    public ArrayList<LineChartSeries> getPlotData() {
        return plotData;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("Initializing rLocusBean.");
//        utils = new Utils();
//    }
    public void setPlotData(ArrayList<LineChartSeries> plotData) {
        this.plotData = plotData;
    }

    public String getpNum() {
        return pNum;
    }

    public void setpNum(String pNum) {
        this.pNum = pNum;
    }

    public String getpDen() {
        return pDen;
    }

    public void setpDen(String pDen) {
        this.pDen = pDen;
    }

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public String getsDen() {
        return sDen;
    }

    public void setsDen(String sDen) {
        this.sDen = sDen;
    }

    public PolynomialFunction getProNum() {
        return proNum;
    }

    public void setProNum(PolynomialFunction proNum) {
        this.proNum = proNum;
    }

    public PolynomialFunction getProDen() {
        return proDen;
    }

    public void setProDen(PolynomialFunction proDen) {
        this.proDen = proDen;
    }

    public PolynomialFunction getSensNum() {
        return sensNum;
    }

    public void setSensNum(PolynomialFunction sensNum) {
        this.sensNum = sensNum;
    }

    public PolynomialFunction getSensDen() {
        return sensDen;
    }

    public void setSensDen(PolynomialFunction sensDen) {
        this.sensDen = sensDen;
    }

    public void calculateRootLocus() {
         System.out.println("Calculating root locus.");
        System.out.println("Calculating zeros.");
        PolynomialFunction numOLTF = utils.toPolynomialFunction(getsNum().split(" ")).multiply(utils.toPolynomialFunction(getpNum().split(" ")));
        PolynomialFunction denOLTF = utils.toPolynomialFunction(getsDen().split(" ")).multiply(utils.toPolynomialFunction(getpDen().split(" ")));
        Complex[] zeros, poles;
        zeros = new Complex[numOLTF.degree()];
        if (numOLTF.degree() > 0) {
            zeros = utils.getRoots(numOLTF);
        }
        poles = new Complex[denOLTF.degree()];
        if (denOLTF.degree() > 0) {
            System.out.println("Calculating poles");
            poles = utils.getRoots(denOLTF);
        }
        double maxStep = utils.stepMax(poles);
        double k = 0.0;
        Complex[] roots = utils.getRoots(utils.getEC(numOLTF, denOLTF, k));
               ArrayList<List<Complex>> series = utils.initializeSeries(roots.length);
        utils.addValuetoSerie(roots, series);
        double defaultInc = 0.5;

        while (!utils.fishisComputation(zeros, series, maxStep, k)) {
            k = k + defaultInc;
            roots = utils.getRoots(utils.getEC(numOLTF, denOLTF, k));
            boolean validRoots = utils.newPointsValid(roots, series, maxStep);
            if (validRoots) {
                utils.addValuetoSerie(roots, series);
                defaultInc = 0.5;
            } else {
                //recalculate k value;
                k = k - defaultInc;
                defaultInc = defaultInc * 3 / 4;
            }
            System.out.println("k=" + k);
        }
        System.out.println("Values of root locus calculated.");
        
        setModel(utils.getDrawableData(series, getModel(),poles,zeros));
    }

}
