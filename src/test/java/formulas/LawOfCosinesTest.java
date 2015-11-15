package formulas;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static java.lang.Math.*;

/**
 * Created by tmolloy on 15/11/2015.
 */
@RunWith(value = BlockJUnit4ClassRunner.class)
public class LawOfCosinesTest extends TestCase {

    /**
     *  Could have used Mockito to mock location objects but using actual objects in this case
     */

    LawOfCosines lawOfCosines = new LawOfCosines();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testValidCase(){
        Location l1 = new Location(10.0,10.0);
        Location l2 = new Location(11.0,11.0);

        double dist = lawOfCosines.distance(l1, l2);
        assertTrue("Distance bewteen (10,10) and (11,11) ~156", round(dist) == 156);
    }

    @Test
    public void testSameValuesCase(){
        Location l1 = new Location(10.0,10.0);
        Location l2 = new Location(10.0,10.0);

        double dist = lawOfCosines.distance(l1, l2);
        assertTrue("Distance bewteen (10,10) and (10,10) ~0", round(dist) == 0);
    }

    @Test
    public void testNegValuesCase1(){
        Location l1 = new Location(10.0,-10.0);
        Location l2 = new Location(11.0,-11.0);

        double dist = lawOfCosines.distance(l1, l2);
        assertTrue("Distance bewteen (10,10) and (10,10) ~0", round(dist) == 156);
    }

    @Test
    public void testNegValuesCase2(){
        Location l1 = new Location(10.0,10.0);
        Location l2 = new Location(11.0,-11.0);

        double dist = lawOfCosines.distance(l1, l2);
        assertTrue("Distance bewteen (10,10) and (10,10) ~0", round(dist) == 2298);
    }

    @Test
    public void testZeroValuesCase(){
        Location l1 = new Location(0.0,0.0);
        Location l2 = new Location(11.0,-11.0);

        double dist = lawOfCosines.distance(l1, l2);
        assertTrue("Distance bewteen (10,10) and (10,10) ~0", round(dist) == 1724);
    }

    @Test
    public void testNullCase1(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Null not a valid argument");

        Location l1 = null;
        Location l2 = null;
        double dist = lawOfCosines.distance(l1, l2);
    }

    @Test
    public void testNullCase2(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Null not a valid argument");

        Location l1 = new Location(1.0,1.0);
        Location l2 = null;
        double dist = lawOfCosines.distance(l1, l2);
    }


    @Test
    public void testNullCase3(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Null not a valid argument");

        Location l1 = null;
        Location l2 = new Location(1.0,1.0);
        double dist = lawOfCosines.distance(l1, l2);
    }
}
