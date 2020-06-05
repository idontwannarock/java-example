package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LargeCsvGenerator {

    public void generate(String fromFilePath, String toFilePath, long copyLinesPerRow) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fromFilePath));
        FileWriter csvWriter = new FileWriter(toFilePath);
        String row;
        int i = 0;
        while ((row = csvReader.readLine()) != null) {
            if (i == 0) {
                csvWriter.append(row);
                csvWriter.append("\n");
            } else {
                for(int j = 0; j < copyLinesPerRow; j ++) {
                    csvWriter.append(row);
                    csvWriter.append("\n");
                    csvWriter.flush();
                }
            }
            i = i + 1;
        }
        csvWriter.close();
        csvReader.close();
    }
}
