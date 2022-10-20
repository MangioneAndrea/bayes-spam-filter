import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
        assertTrue( bayesFilter.isSpam(file));
    }

    static Iterator<Db.File> allSpam() throws URISyntaxException, IOException {
        var tests = new Db("spam_cal");
        return tests.allFiles().iterator();
    }

    @ParameterizedTest
    @MethodSource
    void allHam(Db.File file) {
        assertFalse( bayesFilter.isSpam(file));
    }

    static Iterator<Db.File> allHam() throws URISyntaxException, IOException {
        var tests = new Db("ham_cal");
        return tests.allFiles().iterator();
    }
}