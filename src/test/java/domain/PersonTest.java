package domain;

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
public class PersonTest extends TestCase {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Comparitor only compares on user id
     */
    @Test
    public void testEqualComparitor(){
        Person p1 = new Person("Tom", 1, 4, 6);
        Person p2 = new Person("Jack", 1, 7, 8);

        int result = p1.compareTo(p2);
        assertTrue("expected to be equal", result == 0);
    }

    /**
     * Comparitor only compares on user id
     */
    @Test
    public void testLessThan(){
        Person p1 = new Person("Tom", 1, 4, 6);
        Person p2 = new Person("Jack", 2, 7, 8);

        int result = p1.compareTo(p2);
        assertTrue("expected to be equal", result <= -1);
    }

    /**
     * Comparitor only compares on user id
     */
    @Test
    public void testGreaterThan(){
        Person p1 = new Person("Tom", 2, 4, 6);
        Person p2 = new Person("Jack", 1, 7, 8);

        int result = p1.compareTo(p2);
        assertTrue("expected to be equal", result >= 1);
    }

    /**
     * equals compares on everything
     */
    @Test
    public void testEqualsMethodValid(){
        Person p1 = new Person("Tom", 2, 4, 6);
        Person p2 = new Person("Tom", 2, 4, 6);

        assertTrue("expected to be equal", p1.equals(p2));
        assertTrue("expected to be equal", p2.equals(p1));
    }

    /**
     * equals compares on everything
     */
    @Test
    public void testEqualsMethodNotValid(){
        Person p1 = new Person("Tom1", 2, 4, 6);
        Person p2 = new Person("Tom", 2, 4, 6);

        assertTrue("expected to be equal", !p1.equals(p2));
        assertTrue("expected to be equal", !p2.equals(p1));
    }

    @Test
    public void testHashCodevalid(){
        Person p1 = new Person("Tom", 2, 4, 6);
        Person p2 = new Person("Tom", 2, 4, 6);
        assertTrue("expected to be equal", p1.hashCode() == p2.hashCode());
    }

    @Test
    public void testHashCodeNotValid(){
        Person p1 = new Person("Tom", 2, 4, 6);
        Person p2 = new Person("Tom", 2, 1, 6);
        assertTrue("expected to be equal", p1.hashCode() != p2.hashCode());
    }

    @Test
    public void testPersonConstructor(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Null not a valid argument");
        Person p1 = new Person(null, 2, 4, 6);

    }
}
