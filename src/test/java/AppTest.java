import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

/**
 * Created by tmolloy on 15/11/2015.
 */
public class AppTest extends TestCase {

    @Test
    public void testPrintInvites(){
        App app = new App();
        long result = app.printInvites(new File("src/main/resources/people"));

        assertTrue("should have 16 people: ", result == 16);
    }

    @Test
    public void testBadFilePrintInvites(){
        App app = new App();
        long result = app.printInvites(new File("fakefile"));

        assertTrue("should have 0 people: ", result == 0);
    }
}
