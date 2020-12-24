package file.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OpenCsvParser {

    public List<List<String>> parse(String filePath, int skipLines, int cellTotal) {
        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader reader = getReader(filePath);
             CSVReader csvReader = getCsvReader(reader, skipLines)) {
            String[] currentRecord;
            while ((currentRecord = csvReader.readNext()) != null) {
                if (currentRecord.length == cellTotal) {
                    List<String> row = new ArrayList<>();
                    for (int cellIndex = 0; cellIndex < cellTotal; cellIndex++) {
                        String value = currentRecord[cellIndex];
                        if (value == null) {
                            log.info("value is null");
                        }
                        row.add(value);
                    }
                    rows.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public long countParsedRows(String filePath, int skipLines, int cellTotal) {
        long rowCount = 0;
        try (BufferedReader reader = getReader(filePath);
             CSVReader csvReader = getCsvReader(reader, skipLines)) {
            String[] currentRecord;
            while ((currentRecord = csvReader.readNext()) != null) {
                if (currentRecord.length == cellTotal) {
                    List<String> row = new ArrayList<>();
                    for (int cellIndex = 0; cellIndex < cellTotal; cellIndex++) {
                        String value = currentRecord[cellIndex];
                        if (value == null) {
                            log.info("value is null");
                        }
                        row.add(value);
                    }
                    if (row.size() == cellTotal) {
                        rowCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    private BufferedReader getReader(String filePath) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
    }

    private CSVReader getCsvReader(BufferedReader reader, int skipLines) {
        return new CSVReaderBuilder(reader)
                .withSkipLines(skipLines)
                .build();
    }
}
