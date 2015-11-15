import util.InviteHelper;

import java.io.File;


/**
 * Created by tmolloy on 14/11/2015.
 */
public class App {

    public static void main(String[] args){
        InviteHelper helper = new InviteHelper();

        File input = new File("src/main/resources/people");
        long count = helper.printInvites(input);
        System.out.println("Head count:" + count);
    }
}
