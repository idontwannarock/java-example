package charset;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class CharsetDetector {

    public List<String> detect(String fileAbsolutePath) {
        List<String> viableCharsets = new ArrayList<>();

        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String k : charsets.keySet()) {
            int line = 0;
            boolean success = true;
            try (BufferedReader b = Files.newBufferedReader(Paths.get(fileAbsolutePath), charsets.get(k))) {
                while (b.ready()) {
                    b.readLine();
                    line++;
                }
            } catch (IOException e) {
                success = false;
                System.out.println(k + " failed on line " + line);
            }
            if (success) {
                System.out.println("*************************  Successs " + k);
                viableCharsets.add(k);
            }
        }

        return viableCharsets;
    }
}
