import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HotelStats {
    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "C:\\Users\\Eugene\\Downloads\\archive\\hotels.csv";
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> allRows = reader.readAll();
        int size = allRows.size();
        int numOfThreads = 50;
        HashMap<String , Integer> hotelMap = new HashMap<>();
        ReadThread[] threads = new ReadThread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new ReadThread(filePath, (size/numOfThreads)*i, (size/numOfThreads)*(i+1), hotelMap);
            threads[i].t.start();
        }

        for (int i = 0; i < numOfThreads; i++) {
            threads[i].t.join();
        }

        System.out.println(hotelMap.keySet());
    }
}

