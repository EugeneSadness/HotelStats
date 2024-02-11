import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HotelStats {
    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "C:\\Users\\Eugene\\Downloads\\archive\\hotels.csv";
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> allRows = reader.readAll();
        int numOfRows = allRows.size();
        System.out.println("Number of rows: " + numOfRows);
        Thread.sleep(2000);

        HashMap<String, Integer> hotelMap = new HashMap<>(); // Contains [CityName:NumberOfHotels]

        int numOfThreads = 1;
        ReadThread[] threads = new ReadThread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new ReadThread(allRows, (numOfRows/numOfThreads)*i, (numOfRows/numOfThreads)*(i+1), hotelMap);
            threads[i].t.start();
        }

        for (int i = 0; i < numOfThreads; i++) {
            threads[i].t.join();
        }

        System.out.println(hotelMap.keySet().isEmpty());
    }
}
