package u1;

import java.io.IOException;
import java.net.URISyntaxException;

public class SpamFilter {
    private final Db spam;
    private final Db ham;
    private final Db spamCal;
    private final Db hamCal;

    public SpamFilter() throws URISyntaxException, IOException {
        this.spam = new Db("./u1/spam");
        this.ham = new Db("./u1/ham");
        this.spamCal = new Db("./u1/spam_cal");
        this.hamCal = new Db("./u1/ham_cal");
    }

    public double spamProbability(Db.File file) {
        double pSpam = spam.getAllWordsSize();
        double pHam = ham.getAllWordsSize();

        for (String word : file.getAllWords()) {
            pSpam *= spam.getWordFrequency(word);
            pHam *= ham.getWordFrequency(word);
        }
        return pSpam / pHam;
    }
}
