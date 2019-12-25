package main.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LargeCSVGenerator {

    public static void main(String[] arg) throws IOException {
        FileWriter csvWriter = new FileWriter("file/big.csv");
        BufferedReader csvReader = new BufferedReader(new FileReader("file/supermarket_sales.csv"));
        String row = null;
        int i = 0;
        while ((row = csvReader.readLine()) != null) {
            System.out.println(i);
            if(i == 0) {
                csvWriter.append(row);
                csvWriter.append("\n");
            } else {
                for(int j = 0; j < 10000; j ++) {
                    csvWriter.append(row);
                    csvWriter.append("\n");
                }
            }
            i = i + 1;
        }
        csvWriter.flush();
        csvWriter.close();
        csvReader.close();
    }
}
