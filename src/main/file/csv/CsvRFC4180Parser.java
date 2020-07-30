package file.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

public class CsvRFC4180Parser {

    public List<List<String>> parse(String csvFilePath, long skipLines, long cellTotal) {
        List<List<String>> rows = new ArrayList<>();

        try (BufferedReader reader = newBufferedReader(Paths.get(csvFilePath));
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withCSVParser(new RFC4180Parser())
                     .build()) {
            String[] currentRecord;
            int rowIndex = 0;
            while ((currentRecord = csvReader.readNext()) != null) {
                if (rowIndex < skipLines) {
                    rowIndex++;
                    continue;
                }
                List<String> row = new ArrayList<>();
                for (int cellIndex = 0; cellIndex < cellTotal; cellIndex++) {
                    String value = currentRecord[cellIndex];
                    System.out.println(value + " is" + (value == null ? "" : " not") + " null");
                    row.add(value);
                }
                rows.add(row);
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
