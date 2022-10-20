package u1;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var spamFilter = new SpamFilter();
        System.out.println("spam filter created");
        System.out.println(spamFilter.spamProbability(new Db.File("u1/spam_test/00235.45b5f386cf62b5865d9d4440d8b78aab")));
    }


}
