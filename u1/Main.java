package u1;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var spam=new Db("./u1/spam");
        var ham = new Db("./u1/ham");
        System.out.println(spam.countFilesContainingWords());
    }



}
