package file.csv;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class UnivocityCsvParser {

    public List<List<String>> parse(String filePath, int skipLines, int cellTotal) {
        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader reader = getReader(filePath)) {
            CsvParserSettings settings = getCsvParserSettings(skipLines);

            // creates a CSV parser
            CsvParser parser = new CsvParser(settings);

            // call beginParsing to read records one by one, iterator-style.
            parser.beginParsing(reader);

            String[] currentRecord;
            while ((currentRecord = parser.parseNext()) != null) {
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

            // The resources are closed automatically when the end of the input is reached,
            // or when an error happens, but you can call stopParsing() at any time.

            // You only need to use this if you are not parsing the entire content.
            // But it doesn't hurt if you call it anyway.
            parser.stopParsing();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public long countParsedRows(String filePath, int skipLines, int cellTotal) {
        long rowCount = 0;
        try (BufferedReader reader = getReader(filePath)) {
            CsvParserSettings settings = getCsvParserSettings(skipLines);

            CsvParser parser = new CsvParser(settings);

            for (String[] currentRecord : parser.iterate(reader)) {
                if (currentRecord.length == cellTotal) {
                    List<String> row = new ArrayList<>();
                    for (int cellIndex = 0; cellIndex < cellTotal; cellIndex++) {
                        row.add(currentRecord[cellIndex]);
                    }
                    rowCount++;
                } else {
                    log.error("record has invalid cell count: {}", Arrays.toString(currentRecord));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    private CsvParserSettings getCsvParserSettings(int skipLines) {
//        CsvParserSettings settings = Csv.parseRfc4180();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setNumberOfRowsToSkip(skipLines);
        settings.setQuoteDetectionEnabled(true);
        return settings;
    }

    private BufferedReader getReader(String filePath) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
    }
}
