import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HotelCSVParser {
    public void parse(HashMap<String, Integer> hotelMap, String filePath) {
        File largeFile = new File(filePath);
        int splitSize = 1024 * 1024 * 20; // размер части в байтах (здесь 20 МБ)
        ExecutorService executor = Executors.newFixedThreadPool(5); // например, 5 потоков
        List<byte[]> data = new ArrayList<>();

        try {
            // Разделение файла
            long fileSize = largeFile.length();
            int partCounter = 1;
            int read;
            try (FileInputStream fis = new FileInputStream(largeFile)) {
                byte[] buffer = new byte[splitSize];
                while ((read = fis.read(buffer)) != -1) {
                    data.add(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (byte[] el: data){
            // обработка части csv файла
        }
    }
}

