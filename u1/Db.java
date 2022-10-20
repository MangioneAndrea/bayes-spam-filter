package u1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Db {
    private final static double MIN_PROBABILITY = 0.5;

    public static class File {
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
    private Map<String, Integer> filesContainingWords = new HashMap<>();

    public Db(String folder) throws URISyntaxException, IOException {
        var abs = FileSystems.getDefault().getPath(folder).normalize().toAbsolutePath().toString();
        for (java.io.File file : Objects.requireNonNull(new java.io.File(abs).listFiles())) {
            var f = new File(file.getAbsolutePath());
            files.put(file.getName(), f);
            allWords.addAll(f.getAllWords());
        }

        // How many files contain each word
        for (String word : allWords) {
            filesContainingWords.put(word, (int) files.values().stream().filter(file -> file.hasWord(word)).count());
        }
    }

    public Collection<File> allFiles() {
        return files.values();
    }

    // P ( word | Emails ) == 0
    public boolean hasWord(String word) {
        return allWords.contains(word);
    }

    // P ( Emails )
    public double getAllWordsSize() {
        return allWords.size();
    }

    // P ( Emails with word | Emails )
    public double getWordFrequency(String word) {
        if (!filesContainingWords.containsKey(word)) return MIN_PROBABILITY;
        return ((double) filesContainingWords.get(word)) / files.size();
    }
}
