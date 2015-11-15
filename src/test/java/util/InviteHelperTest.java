package util;

import domain.Person;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Optional;

/**
 * Created by tmolloy on 15/11/2015.
 */
@RunWith(value = BlockJUnit4ClassRunner.class)
public class InviteHelperTest extends TestCase {

    InviteHelper helper = new InviteHelper();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testJson2StringNull(){
        Optional<Person> result = helper.json2Person(null);
        assertTrue("Should be an empty optional", result == Optional.<Person>empty());
    }

    @Test
    public void testJson2StringValidArgument(){
        String jsonString = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        Person p = new Person("Christina McArdle", 12, 52.986375, -6.043701);

        Optional<Person> opt = helper.json2Person(jsonString);
        Person result = opt.get();

        assertTrue("Person is equal: ", result.equals(p));

    }

    @Test
    public void testJson2StringMissingArguments1(){
        String jsonString = "{\"latitude\": , \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        Optional<Person> result = helper.json2Person(jsonString);
        assertTrue("Should be an empty optional", result == Optional.<Person>empty());
    }

    @Test
    public void testJson2StringMissingArguments2(){
        String jsonString = "{\"latitude\": \"52.986375\", \"user_id\": , \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        Optional<Person> result = helper.json2Person(jsonString);
        assertTrue("Should be an empty optional", result == Optional.<Person>empty());
    }

    @Test
    public void testJson2StringMissingArguments3(){
        String jsonString = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": , \"longitude\": \"-6.043701\"}";
        Optional<Person> result = helper.json2Person(jsonString);
        assertTrue("Should be an empty optional", result == Optional.<Person>empty());
    }

    @Test
    public void testJson2StringMissingArguments4(){
        String jsonString = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": }";
        Optional<Person> result = helper.json2Person(jsonString);
        assertTrue("Should be an empty optional", result == Optional.<Person>empty());
    }


    @Test
    public void testJson2StringNumberFormat(){
        String jsonString = "{\"latitude\": \"52.986375\", \"user_id\": \"67\", \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        Optional<Person> result = helper.json2Person(jsonString);
        Person p = result.get();

        assertTrue("Should be valid number", p.getUser_id() == 67);
    }

    @Test
    public void testJson2StringNumberFormatBad(){
        String jsonString = "{\"latitude\": \"52.986375\", \"user_id\": \"67h\", \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        Optional<Person> result = helper.json2Person(jsonString);

        assertTrue("Should be an empty optional due to letter in user_id", result == Optional.<Person>empty());
    }

    @Test
    public void testNullCaseLocation(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Null not a valid argument");

        helper.setLocation(null);
    }

    @Test
    public void testNullCasedistanceStrategy(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Null not a valid argument");

        helper.setDistanceStrategy(null);
    }

}
