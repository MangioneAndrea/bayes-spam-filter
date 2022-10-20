package u1;

import java.io.IOException;
import java.net.URISyntaxException;

public class SpamFilter {
    public static double THRESHOLD = .5;

    private final Db spam;
    private final Db ham;

    public SpamFilter() throws URISyntaxException, IOException {
        this.spam = new Db("./u1/spam");
        this.ham = new Db("./u1/ham");

        this.spam.ensureAllWords(this.ham.getAllWords());
        this.ham.ensureAllWords(this.spam.getAllWords());
    }

    // Score as 0 - Infinity
    public double spamScore(Db.File file) {
        double pSpam = spam.getAllWordsSize();
        double pHam = ham.getAllWordsSize();

        for (String word : file.getAllWords()) {
            // Ignore words which are not in the dbs
            if (!spam.hasWord(word) && !ham.hasWord(word)) continue;
            var pspam2 = pSpam;
            pSpam *= spam.getWordFrequency(word);
            pHam *= ham.getWordFrequency(word);

            // Avoid very small numbers to be rounded to 0
            if (pSpam < 0.001 && pHam < 0.001) {
                pSpam *= 1000;
                pHam *= 1000;
            }
        }

        // If the word is not found, return 1 (neither span nor ham)
        if (pSpam == 0 && pHam == 0) return 1;
        // If the word is in spam, but not in ham, return a high value (to avoid division by 0)
        if (pHam == 0) return 100;
        return pSpam / pHam;
    }

    // Score as 0 - 1
    public double spamProbability(Db.File file) {
        var score = spamScore(file);
        return score / (1 + score);
    }

    public boolean isSpam(Db.File file) {
        return spamProbability(file) > 0.5;
    }
}


