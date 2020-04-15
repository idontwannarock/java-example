package csv;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public List<List<String>> parse(String xlsxFilePath, long skipLines, long cellTotal) {
        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(xlsxFilePath), StandardCharsets.UTF_8));
             CSVReader csvReader = new CSVReader(reader)) {
            String[] currentRecord;
            int rowIndex = 0;
            while ((currentRecord = csvReader.readNext()) != null) {
                if (rowIndex < skipLines) {
                    rowIndex++;
                    continue;
                }
                List<String> row = new ArrayList<>();
                for (int index = 0; index < cellTotal; index++) {
                    String recordValue = currentRecord[index];
                    if ("".equals(recordValue)) {
                        recordValue = null;
                    }
                    row.add(recordValue);
                }
                rows.add(row);
                rowIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
}
