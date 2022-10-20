package u1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SpamFilterTest {
    static SpamFilter bayesFilter;

    static {
        try {
            bayesFilter = new SpamFilter();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @MethodSource
    void allSpam(Db.File file) {
        var p = bayesFilter.spamProbability(file);
        assertTrue(p >= 1);
    }

    static Iterator<Db.File> allSpam() throws URISyntaxException, IOException {
        var tests = new Db("u1/spam_cal");
        return tests.allFiles().iterator();
    }

    @ParameterizedTest
    @MethodSource
    void allHam(Db.File file) {
        var p = bayesFilter.spamProbability(file);
        assertTrue(p <= 1);
    }

    static Iterator<Db.File> allHam() throws URISyntaxException, IOException {
        var tests = new Db("u1/ham_cal");
        return tests.allFiles().iterator();
    }
}