package u1;

import java.io.IOException;
import java.net.URISyntaxException;

public class main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var db=new Db("./u1/data");
        System.out.println(db.CountFilesContainingWords());
    }



}
