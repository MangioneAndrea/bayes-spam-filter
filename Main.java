import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var spamFilter = new SpamFilter();

        // This takes a bit!
        var spamTest = new Db("spam_test").allFiles();
        var hamTest = new Db("ham_test").allFiles();

        double spamAsSpam = spamTest.stream().filter(spamFilter::isSpam).count();
        double hamAsSpam = hamTest.stream().filter(spamFilter::isSpam).count();

        DecimalFormat numberFormat = new DecimalFormat("#.00");
        System.out.println("The threshold for spam emails is: " + SpamFilter.THRESHOLD * 100 + "%");
        System.out.println("The α is: " + new DecimalFormat("0.00000").format(Db.MIN_PROBABILITY * 100) + "%");

        System.out.println();
        System.out.println(spamAsSpam + " out of " + spamTest.size() + " spam mails have be marked as spam. Percentage: " + numberFormat.format(spamAsSpam / spamTest.size() * 100) + "%");
        System.out.println(hamAsSpam + " out of " + hamTest.size() + " ham mails have be marked as spam. Percentage: " + numberFormat.format(hamAsSpam / hamTest.size() * 100) + "%");
    }


}
