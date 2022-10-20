package u1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var spamFilter = new SpamFilter();

        // This takes a bit!
        var spamTest = new Db("./u1/spam_test").allFiles();
        var hamTest = new Db("./u1/ham_test").allFiles();

        double spamAsSpam = spamTest.stream().filter(spamFilter::isSpam).count();
        double hamAsSpam = hamTest.stream().filter(spamFilter::isSpam).count();

        DecimalFormat numberFormat = new DecimalFormat("#.00");
        System.out.println(spamAsSpam + " out of " + spamTest.size() + " spam mails has be marked as spam. Percentage: " + numberFormat.format(spamAsSpam / spamTest.size() * 100) + "%");
        System.out.println(hamAsSpam + " out of " + hamTest.size() + " ham mails has be marked as spam. Percentage: " + numberFormat.format(hamAsSpam / hamTest.size() * 100) + "%");
    }


}
