/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom.rootlocus.utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author fran
 */
public class UtilsTest {

    public UtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getEC method, of class Utils.
     */
    @Ignore
    @Test
    public void testGetEC_5args() {
        System.out.println("getEC");
        PolynomialFunction pNum = null;
        PolynomialFunction pDen = null;
        PolynomialFunction sNum = null;
        PolynomialFunction sDen = null;
        double k = 0.0;
        Utils instance = new Utils();
        PolynomialFunction expResult = null;
        PolynomialFunction result = instance.getEC(pNum, pDen, sNum, sDen, k);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEC method, of class Utils.
     */
    @Ignore
    @Test
    public void testGetEC_3args() {
        System.out.println("getEC");
        PolynomialFunction num = null;
        PolynomialFunction den = null;
        double k = 0.0;
        Utils instance = new Utils();
        PolynomialFunction expResult = null;
        PolynomialFunction result = instance.getEC(num, den, k);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toPolynomialFunction method, of class Utils.
     */
    @Ignore
    @Test
    public void testToPolynomialFunction() {
        System.out.println("toPolynomialFunction");
        String[] coef = null;
        Utils instance = new Utils();
        PolynomialFunction expResult = null;
        PolynomialFunction result = instance.toPolynomialFunction(coef);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoots method, of class Utils.
     */
    @Ignore
    @Test
    public void testGetRoots() {
        System.out.println("getRoots");
        PolynomialFunction p = null;
        Utils instance = new Utils();
        Complex[] expResult = null;
        Complex[] result = instance.getRoots(p);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stepMax method, of class Utils.
     */
    @Ignore
    @Test
    public void testStepMax() {
        System.out.println("stepMax");
        Complex[] poles = null;
        Utils instance = new Utils();
        double expResult = 0.0;
        double result = instance.stepMax(poles);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  

    /**
     * Test of getModule method, of class Utils.
     */
    @Test
    public void testGetModule() {
        System.out.println("Testing getModule");
        Complex n = new Complex(2, 3);
        Utils instance = new Utils();
        double expResult = 3.605551275;
        double result = instance.getModule(n);
        double e=0.01;
        assertEquals(expResult, result,e);
        n = new Complex(-2, 0);
        expResult = 2;
        result = instance.getModule(n);
        assertEquals(expResult, result,e);
         }

    /**
     * Test of substractComplex method, of class Utils.
     */
    @Test
    public void testSubstractComplex() {
        System.out.println("Testing substractComplex");
        Complex a = new Complex(5, 2);
        Complex b = new Complex(-1, 2);
        Utils instance = new Utils();
        Complex expResult = new Complex(6, 0);
        Complex result = instance.substractComplex(a, b);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of addValuetoSerie method, of class Utils.
     */
    @Ignore
    @Test
    public void testAddValuetoSerie() {
        System.out.println("addValuetoSerie");
        Complex[] roots = null;
        List<List<Complex>> series = null;
        Utils instance = new Utils();
        instance.addValuetoSerie(roots, series);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeSeries method, of class Utils.
     */
    @Ignore
    @Test
    public void testInitializeSeries() {
        System.out.println("initializeSeries");
        int listNum = 0;
        Utils instance = new Utils();
        List<List<Complex>> expResult = null;
        List<List<Complex>> result = instance.initializeSeries(listNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countComplexPairRoots method, of class Utils.
     */
    @Ignore
    @Test
    public void testCountComplexPairRoots() {
        System.out.println("countComplexPairRoots");
        Complex[] r = null;
        Utils instance = new Utils();
        int expResult = 0;
        int result = instance.countComplexPairRoots(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRootsSimplified method, of class Utils.
     */
    @Ignore
    @Test
    public void testGetRootsSimplified() {
        System.out.println("getRootsSimplified");
        Complex[] r = null;
        Utils instance = new Utils();
        Complex[] expResult = null;
        Complex[] result = instance.getRootsSimplified(r);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializePlotSeries method, of class Utils.
     */
    @Ignore
    @Test
    public void testInitializePlotSeries() {
        System.out.println("initializePlotSeries");
        int listNum = 0;
        Utils instance = new Utils();
        List<LineChartSeries> expResult = null;
        List<LineChartSeries> result = instance.initializePlotSeries(listNum, true);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDrawableData method, of class Utils.
     */
    @Ignore
    @Test
    public void testGetDrawableData() {
        System.out.println("getDrawableData");
        ArrayList<List<Complex>> series = null;
        List<LineChartSeries> plotSeries = null;
        
        Utils instance = new Utils();
        LineChartModel model = null;
        Complex[]poles = null,zeros = null;
        LineChartModel result = instance.getDrawableData(series, model, poles, zeros);
        assertEquals(model, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
