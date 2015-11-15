package formulas;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by tmolloy on 15/11/2015.
 */
@RunWith(value = BlockJUnit4ClassRunner.class)
public class LocationTest extends TestCase {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testValidCase(){
        Location l1 = new Location(1.0,2.0);
        assertTrue("Valid location", l1.getLatitude() == 1.0 & l1.getLongitude() == 2.0);
    }

    @Test
    public void testLowerLatitudeBoundCase1(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Latitude should be between 0 and 90 degrees");
        Location l1 = new Location(-1.0, 2);
    }

    @Test
    public void testLowerLatitudeBoundCase2(){
        Location l1 = new Location(0.0, 2);
        assertTrue("Valid location", l1.getLatitude() == 0.0 & l1.getLongitude() == 2.0);
    }

    @Test
    public void testUpperLatitudeBoundCase1(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Latitude should be between 0 and 90 degrees");
        Location l1 = new Location(90.1, 2);
    }

    @Test
    public void testUpperLatitudeBoundCase2(){
        Location l1 = new Location(90.0, 2);
        assertTrue("Valid location", l1.getLatitude() == 90.0 & l1.getLongitude() == 2.0);
    }

    @Test
    public void testLowerLongitudeBoundCase1(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Longitude should be between -180 and 180 degrees");
        Location l1 = new Location(1.0, -180.1);
    }

    @Test
    public void testLowerLongitudeBoundCase2(){
        Location l1 = new Location(1.0, -180.0);
        assertTrue("Valid location", l1.getLatitude() == 1.0 && l1.getLongitude() == -180.0);
    }

    @Test
    public void testUpperLongitudeBoundCase1(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Longitude should be between -180 and 180 degrees");
        Location l1 = new Location(1.0, 180.1);
    }

    @Test
    public void testUpperLongitudeBoundCase2(){
        Location l1 = new Location(1.0, 180.0);
        assertTrue("Valid location", l1.getLatitude() == 1.0 & l1.getLongitude() == 180.0);
    }
}
