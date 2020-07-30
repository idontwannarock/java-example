package file.excel;

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

public class XlsParser {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public List<List<String>> parse(String xlsFilePath, long skipLines, long cellTotal) {
        File file = new File(xlsFilePath);
        List<List<String>> rows = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(inputStream)) {
            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);
            sheet.getPhysicalNumberOfRows();
            for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                if (rowIndex < skipLines) {
                    continue;
                }
                Row excelRow = sheet.getRow(rowIndex);
                List<String> row = new ArrayList<>();
                for (int cellIndex = 0; cellIndex < cellTotal; cellIndex++) {
                    Cell excelRowCell = excelRow.getCell(cellIndex, RETURN_NULL_AND_BLANK);
                    String cellValue = extractCellValue(excelRowCell, evaluator, dataFormatter);
                    System.out.println("Row " + rowIndex + ", cell " + cellIndex + " value: " + cellValue);
                    row.add(cellValue);
                }
                rows.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    private String extractCellValue(Cell cell, FormulaEvaluator evaluator, DataFormatter dataFormatter) {
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
            CellValue cellValue = evaluator.evaluate(cell);
            switch (cellValue.getCellTypeEnum()) {
                case BOOLEAN:
                    value = String.valueOf(cellValue.getBooleanValue());
                    break;
                case NUMERIC:
                    value = String.valueOf(cellValue.getNumberValue());
                    break;
                case ERROR:
                    value = String.valueOf(cellValue.getErrorValue());
                    break;
                case STRING:
                    value = cellValue.getStringValue();
                    break;
                case BLANK:
                case FORMULA: // FORMULA will never happen
                default:
                    value = null;
            }
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
