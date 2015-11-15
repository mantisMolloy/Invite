import domain.PersonTest;
import formulas.LawOfCosinesTest;
import formulas.LocationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import util.InviteHelperTest;

/**
 * Created by tmolloy on 15/11/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        PersonTest.class,
        LawOfCosinesTest.class,
        LocationTest.class,
        InviteHelperTest.class
})
public class AllTests {
}
