import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;

public class HotelStats {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Eugene\\Downloads\\archive\\hotels.csv";
        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath));
        System.out.println("Number of rows: " + reader.readNext()[3]);
    }
}

