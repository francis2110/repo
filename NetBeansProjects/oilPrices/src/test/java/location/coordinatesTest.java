/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package location;

import com.oilPrices.Utils.Utils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fran
 */
public class coordinatesTest {
    Utils utils;
    
    public coordinatesTest() {
        utils=new Utils();
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        }
    
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void haversineDistanceTest(){
        System.out.println("test of calculating distance through haversine.");
        Double distanceHaversine = utils.getDistanceHaversine("39.619145" ,"39.4274468","-0.307803","-0.41840930000000753");
        long methodVal=distanceHaversine.longValue();
        long expedtedVal=(long) 23;
        assertEquals(expedtedVal, methodVal); 
    
    }
}
