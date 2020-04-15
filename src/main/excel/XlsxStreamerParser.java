package excel;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;

public class XlsxStreamerParser {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public List<List<String>> parse(String xlsxFilePath, long skipLines, long cellTotal) {
        File file = new File(xlsxFilePath);
        List<List<String>> rows = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file);
             Workbook workbook = StreamingReader.builder().rowCacheSize(1000).bufferSize(100).open(inputStream)) {
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex = 0;
            for (Row excelRow : sheet) {
                if (rowIndex < skipLines) {
                    rowIndex++;
                    continue;
                }
                List<String> row = new ArrayList<>();
                for (int cellIndex = 0; cellIndex < cellTotal; cellIndex++) {
                    Cell excelRowCell = excelRow.getCell(cellIndex, RETURN_NULL_AND_BLANK);
                    String cellValue = extractCellValue(excelRowCell, dataFormatter);
                    System.out.println("Row " + rowIndex + ", cell " + cellIndex + " value: " + cellValue);
                    row.add(cellValue);
                }
                rows.add(row);
                rowIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    private String extractCellValue(Cell cell, DataFormatter dataFormatter) {
        String value;
        if (cell == null) {
            value = null;
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            value = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            value = cell.getStringCellValue();
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                Instant instant = date.toInstant();
                LocalDateTime ldt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                value = ldt.format(formatter);
            } else {
                value = dataFormatter.formatCellValue(cell);
            }
        } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
            value = cell.getCellFormula();
        } else if (cell.getCellTypeEnum() == CellType.ERROR) {
            value = String.valueOf(cell.getErrorCellValue());
        } else if (cell.getCellTypeEnum() == CellType.BLANK) {
            value = null;
        } else {
            value = dataFormatter.formatCellValue(cell);
        }
        return value;
    }
}
