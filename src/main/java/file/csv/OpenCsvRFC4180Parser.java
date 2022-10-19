package file.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

@Slf4j
public class OpenCsvRFC4180Parser {

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
        } catch (IOException | CsvValidationException e) {
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

    private BufferedReader getReader(String csvFilePath) throws IOException {
        return newBufferedReader(Paths.get(csvFilePath));
    }

    private CSVReader getCsvReader(BufferedReader reader, int skipLines) {
        return new CSVReaderBuilder(reader)
                .withCSVParser(new RFC4180Parser())
                .withSkipLines(skipLines)
                .build();
    }
}
