import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.InviteHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;


/**
 * Created by tmolloy on 14/11/2015.
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static long printInvites(File file){

        InviteHelper inviteHelper = new InviteHelper();
        long count = 0;

        System.out.println("The following people are cordially invited to our Box Social\n-----");
        try {
            count = Files.lines(file.toPath())
                    .map(inviteHelper::json2Person)
                    .filter(Optional::isPresent)   // in JDK 9 we these 2 lines
                    .map(Optional::get)            // as '.flatMap(Optional::stream)'
                    .filter(person -> inviteHelper.isWithin(person, 100D))
                    .sorted()
                    .peek(System.out::println)
                    .count();
        } catch (IOException e) {
            logger.error("Error reading file", e);
        }catch (Exception e){
            logger.error("Error creating invites", e);
        }
        return count;
    }

    public static void main(String[] args){
        File input = new File("src/main/resources/people");
        long count = printInvites(input);
        System.out.println("Head count:" + count);
    }
}
