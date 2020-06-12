package charset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
                System.out.println("*************************  Success " + k);
                viableCharsets.add(k);
            }
        }

        return viableCharsets;
    }

    public String convert(String value, String fromEncoding, String toEncoding) throws UnsupportedEncodingException {
        return new String(value.getBytes(fromEncoding), toEncoding);
    }

    public String charset(String value, String[] charsets) throws UnsupportedEncodingException {
        String probe = StandardCharsets.UTF_8.name();
        for(String c : charsets) {
            Charset charset = Charset.forName(c);
            if (value.equals(convert(convert(value, charset.name(), probe), probe, charset.name()))) {
                return c;
            }
        }
        return StandardCharsets.UTF_8.name();
    }

    public void decodeHexString(String orig) {
        try {
            byte[] bytes = new byte[orig.length() / 4];

            for (int i = 0; i < orig.length(); i += 4) {
                bytes[i / 4] = (byte) Integer.parseInt(orig.substring(i + 2, i + 4), 16);
            }

            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
