import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReadThread implements Runnable {
    Thread t;
    HashMap<String, Integer> hotelMap;
    int start; int end;
    String filePath;


    public ReadThread(String filePath, int start, int end, HashMap hotelMap) {
        t = new Thread(this);
        this.start = start;
        this.end = end;
        this.filePath = filePath;
        this.hotelMap = hotelMap;
    }

    @Override
    public void run() {
        int col = 3; // Third column is CityName
        String nextLine;
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(start).build();
            for (int i = start; i < end; i++) {
                nextLine = reader.readNext()[col];
                if(!hotelMap.containsKey(nextLine))
                    hotelMap.put(nextLine, 1);
                else
                    hotelMap.put(nextLine, hotelMap.get(nextLine) + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}