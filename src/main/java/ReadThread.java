import java.util.HashMap;
import java.util.List;

public class ReadThread implements Runnable {
    Thread t;
    List<String[]> allRows;
    HashMap<String, Integer> hotelMap;
    int start;
    int end;


    public ReadThread(List<String[]> allRows, int start, int end, HashMap hotelMap) {
        t = new Thread(this);
        this.start = start;
        this.end = end;
        this.allRows = allRows;
        this.hotelMap = hotelMap;
    }

    @Override
    public void run() {
        int col = 3;
        for(int i = start; i < end; i++){
            try {
                String data = allRows.get(i)[col];
                System.out.println("№" + i + ": " + data);
                synchronized (hotelMap) {
                    if (!hotelMap.containsKey(data)) {
                        hotelMap.put(data, 1);
                    } else
                        hotelMap.put(data, hotelMap.get(data) + 1);

                }
            } catch (ArrayIndexOutOfBoundsException e){
                continue;
            }
        }
    }
}