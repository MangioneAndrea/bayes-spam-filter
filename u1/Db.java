package u1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Db {

    private class File {
        private Map<String, Integer> words = new HashMap<>();

        File(String path) throws IOException {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            for (String s : new String(bytes).split(" ")) {
                if (words.containsKey(s)) {
                    words.put(s, words.get(s) + 1);
                } else {
                    words.put(s, 1);
                }
            }
        }

        public java.util.Set<String> getAllWords() {
            return words.keySet();
        }

        public boolean hasWord(String word) {
            return words.containsKey(word);
        }
    }

    private Map<String, File> files = new HashMap<>();
    private java.util.Set<String> allWords = new HashSet<String>();

    public Db(String folder) throws URISyntaxException, IOException {
        var abs = FileSystems.getDefault().getPath(folder).normalize().toAbsolutePath().toString();
        for (java.io.File file : Objects.requireNonNull(new java.io.File(abs).listFiles())) {
            var f = new File(file.getAbsolutePath());
            files.put(file.getName(), f);
            allWords.addAll(f.getAllWords());
        }
    }

    public HashMap<String, Integer> countFilesContainingWords() {
        var res = new HashMap<String, Integer>();
        for (String word : allWords) {
            res.put(word, (int) files.values().stream().filter(file -> file.hasWord(word)).count());
        }
        return res;
    }
}
