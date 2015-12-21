/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom.rootlocus.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;
import org.omg.CORBA.INTERNAL;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author fran
 */
public class Utils {

    public PolynomialFunction getEC(PolynomialFunction pNum, PolynomialFunction pDen, PolynomialFunction sNum, PolynomialFunction sDen, double k) {
        double[] gain = {k};
        return pNum.multiply(sNum).multiply(new PolynomialFunction(gain)).add(pDen.multiply(sDen));
    }

    public PolynomialFunction getEC(PolynomialFunction num, PolynomialFunction den, double k) {
        double[] gain = {k};
        return num.multiply(new PolynomialFunction(gain)).add(den);
    }

    public PolynomialFunction toPolynomialFunction(String[] coef) {

        double[] cf = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            cf[i] = Double.parseDouble(coef[i]);
        }
        return new PolynomialFunction(cf);
    }

    /**
     * method returns the maximum increment of the last step of each serie
     *
     * @param roots:new roots
     * @param series:series of complex numbers
     * @param maxStep:maximum step allowed between values of the same serie.
     *
     */
    public boolean newPointsValid(Complex[] roots, final ArrayList<List<Complex>> series, double maxStep) {
        ArrayList<List<Complex>> auxSeries = new ArrayList<>();
        for (List<Complex> serie : series) {
            auxSeries.add(new ArrayList<>(serie));
        }

        addValuetoSerie(roots, auxSeries);
        double maxRel = 0;
        for (int i = 0; i < auxSeries.size(); i++) {
            List<Complex> serie = auxSeries.get(i);
            double d = getModule(substractComplex(serie.get(serie.size() - 1), serie.get(serie.size() - 2)));
            if (d > maxRel) {
                maxRel = d;
            }
        }
        if (maxRel < maxStep) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * estimates the value for k
     *
     * @param increment: value of the maximum increment between the two last
     * values of each serie
     * @param k:value of the last k;
     * @param maxIncrement :maximum increment allowed
     * @return the estimated value for k
     */
    public double estimatedKValue(double increment, double k, double maxIncrement) {
        return k * maxIncrement / increment;
    }

    public Complex[] getRoots(PolynomialFunction p) {
        double[] coefficients = p.getCoefficients();
        LaguerreSolver solver = new LaguerreSolver(1e-10);
        Complex[] r = solver.solveAllComplex(p.getCoefficients(), 0);
        return r;
//        Complex[] roots = new Complex[r.length];
//        for (int i = 0; i < r.length; i++) {
//            double Re = new BigDecimal(r[i].getReal()).setScale(5, RoundingMode.HALF_UP).doubleValue();
//            double Img = new BigDecimal(r[i].getImaginary()).setScale(5, RoundingMode.HALF_UP).doubleValue();
//            roots[i] = new Complex(Re, Img);
//        }
//        return roots;
    }

    /**
     * maximum distance between points. The criteria is 5% of the module of the
     * pole value with minimum module
     *
     * @param poles
     * @return maximum distance
     */
    public double stepMax(Complex[] poles) {
        double minDistance = 0.0;
        for (int i = 0; i < poles.length; i++) {
            double d = getModule(poles[i]) * 0.02;
            if (i == 0) {
                minDistance = d;
            } else if (d < minDistance) {
                minDistance = d;
            }
        }
        return minDistance;
    }

    /**
     * get the module of a complex number
     *
     * @param n:the number that we are interested in its module
     * @return the module
     *
     */
    public double getModule(Complex n) {
        BigDecimal Re = new BigDecimal(n.getReal());
        BigDecimal Img = new BigDecimal(n.getImaginary());
        return Math.sqrt(Re.pow(2).add(Img.pow(2)).doubleValue());
    }

    /**
     * a-b being a and b complex numbers
     *
     * @param a:complex number
     * @param b: comlex number
     * @return substraction
     */
    public Complex substractComplex(Complex a, Complex b) {
        return new Complex(a.getReal() - b.getReal(), a.getImaginary() - b.getImaginary());
    }

    /**
     * establishes if the computation of roots is finished. The method applies
     * different criterias for this estimation.
     *
     * @param zeros:zeros of the transfer function
     * @param series:series in which roots are added.
     * @param maxStep the step maximum between the last element of the serie and
     * zero
     * @param k: gain value
     */
    public boolean fishisComputation(Complex[] zeros, List<List<Complex>> series, double maxStep, double k) {
        int branchesOk = 0;
        boolean zerosOk = false;//variable to determine if roots are near enough to zeros.

        if (zeros.length > 0) {

            for (int i = 0; i < zeros.length; i++) {
                Iterator it = series.iterator();
                while (it.hasNext()) {
                    /**
                     * TODO no se mete en el iterador las veces que toca
                     */
                    List<Complex> serie = (List<Complex>) it.next();
                    Complex serieVal = serie.get(serie.size() - 1);
                    double c = getModule(substractComplex(serieVal, zeros[i]));
                    if (c < maxStep) {
                        branchesOk++;
                        break;
                    }

                }
            }

        }
        if (branchesOk == zeros.length && k > 100) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * method that adds a value to the correct serie
     *
     * @param roots: an array of values for a specific k value.
     * @param series:an arrayList on which series of values are stored.
     */
    public void addValuetoSerie(Complex[] roots, List<List<Complex>> series) {

        if (series.get(0).isEmpty()) {
            for (int i = 0; i < roots.length; i++) {
                series.get(i).add(roots[i]);
            }

        } else {
            int lastValue = series.get(0).size() - 1;
            ArrayList seriesAdded = new ArrayList();

            for (int i = 0; i < roots.length; i++) {
                double distance = 0;
                int serieToadd = 0;
                Complex sol = roots[i];
                for (int j = 0; j < series.size(); j++) {
                    double module = getModule(substractComplex(series.get(j).get(lastValue), sol));
                    if (j == 0) {
                        distance = module;
                    } else if (j > 0 && module <= distance && !seriesAdded.contains(j)) {
                        serieToadd = j;
                        distance = module;

                    } else if (module == 0.0 && !seriesAdded.contains(j)) {
                        series.get(j).add(sol);
                        j = series.size();
                    }
                }
                seriesAdded.add(serieToadd);
                series.get(serieToadd).add(sol);
            }
        }
    }

    /**
     * initialize the list in which the roots values are stored.
     *
     * @param listNum: the number of series;
     * @return series List ready and initialized to store plot values.
     */
    public ArrayList<List<Complex>> initializeSeries(int listNum) {
        ArrayList<List<Complex>> series = new ArrayList<>();
        for (int i = 0; i < listNum; i++) {
            series.add(new ArrayList<Complex>());
        }
        return series;
    }

    public int countComplexPairRoots(Complex[] r) {
        int counter = 0;
        for (int i = 0; i < r.length; i++) {
            if (r[i].getImaginary() != 0) {
                counter++;
            }
        }
        return counter;
    }

    public Complex[] getRootsSimplified(Complex[] r) {
//        int pCompRoots = countComplexPairRoots(r);
        ArrayList<Complex> sr = new ArrayList<>();

        for (int i = 0; i < r.length; i++) {
            if (r[i].getImaginary() == 0) {
                sr.add(r[i]);
            } else {
                Iterator it = sr.iterator();
                int count = 0;
                while (it.hasNext()) {
                    Complex c = (Complex) it.next();
                    double Re = new BigDecimal(c.getReal()).setScale(4, RoundingMode.HALF_UP).doubleValue();
                    double Img = new BigDecimal(c.getImaginary()).setScale(4, RoundingMode.HALF_UP).doubleValue();
                    double rootRe = new BigDecimal(r[i].getReal()).setScale(4, RoundingMode.HALF_UP).doubleValue();
                    double rootImg = new BigDecimal(r[i].getImaginary()).setScale(4, RoundingMode.HALF_UP).doubleValue();
                    if (Re == rootRe && (Img == rootImg || Img == -rootImg)) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    sr.add(r[i]);
                }

            }
        }
        return sr.toArray(new Complex[sr.size()]);
    }

    /**
     * initialize series for ploting the chart
     *
     * @param listNum number of LineChartSeries
     *
     * @return LinieChartSeries initialized
     */
    public List<LineChartSeries> initializePlotSeries(int listNum, boolean isLabel) {
        List<LineChartSeries> plotSeries = new ArrayList<>();
        for (int i = 0; i < listNum; i++) {
            LineChartSeries chartSerie = new LineChartSeries();
            if (isLabel) {
                chartSerie.setLabel("serie" + new Integer(i).toString());
            }
            plotSeries.add(chartSerie);
        }
        return plotSeries;
    }

    public void addtoLineChartSeries(ArrayList<List<Complex>> series, List<LineChartSeries> plotSeries, LineChartModel model, boolean marker) {
        Iterator it = series.iterator();
        int i = 0;//counter for plotSeries
        while (it.hasNext()) {
            List<Complex> serie = (List<Complex>) it.next();
            LineChartSeries chartSeries = plotSeries.get(i);
            if (marker) {
                chartSeries.setShowMarker(marker);
            } else {
                chartSeries.setShowMarker(false);
            }
            for (Complex c : serie) {
                chartSeries.set(c.getReal(), c.getImaginary());
            }
            model.addSeries(chartSeries);
            i++;
        }

    }

    public LineChartModel getDrawableData(ArrayList<List<Complex>> series, LineChartModel model, Complex[] poles, Complex[] zeros) {
        List<LineChartSeries> plotSeries = initializePlotSeries(series.size(), false);
        List<LineChartSeries> zpPlotSeries = initializePlotSeries(poles.length + zeros.length, true);
        model.setTitle("Root locus plot.");
        model.setZoom(true);
        model.setExtender("extend");
        addtoLineChartSeries(series, plotSeries, model, false);
//        Iterator it = series.iterator();
//        int i = 0;//counter for plotSeries
//        while (it.hasNext()) {
//            List<Complex> serie = (List<Complex>) it.next();
//            LineChartSeries chartSeries = plotSeries.get(i);
//            chartSeries.setShowMarker(false);
//            for (Complex c : serie) {
//                chartSeries.set(c.getReal(), c.getImaginary());
//            }
//            model.addSeries(chartSeries);
//            i++;
//        }
        ArrayList<List<Complex>> zpSeries = new ArrayList<>();
        if (poles.length > 0) {
            initComplexZeroSeries(zpSeries, poles);
        }
        if (zeros.length > 0) {
            initComplexZeroSeries(zpSeries, zeros);
        }
        addtoLineChartSeries(zpSeries, zpPlotSeries, model, true);
        return model;

    }

    /**
     * add to an arrayList each pole and zero stored in one list each one.
     *
     * @params zpSeries:series in wich zero and poles will be stored.
     * @params comp: an array of zero or poles.
     */
    public void initComplexZeroSeries(ArrayList<List<Complex>> zpSeries, Complex comp[]) {
        for (int j = 0; j < comp.length; j++) {
            Complex c[] = new Complex[1];
            c[0] = new Complex(comp[j].getReal(), comp[j].getImaginary());
            zpSeries.add(Arrays.asList(c));
        }
    }

}
