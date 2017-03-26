package co.dmssolutions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author DMS Solutions
 */
public class Util {

    /**
     * Processes raw statistics data from sensor data file and loads it to List<br>
     * Skips empty lines and trims line values as well
     *
     * @param is data resource
     * @return processed info in friendly format
     */
    public static ArrayList<String> processRawStatData(InputStream is) {
        ArrayList<String> result;
        try (Scanner scanner = new Scanner(is)) {
            result = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        }
        return result;
    }


    /**
     * Converts raw sensor data into Vehicle objects saving the original order<br>
     * Each Vehicle object represents one physical vehicle<br>
     * <b>Method assumes that provided data is valid! Otherwise it will thrown unchecked exceptions!</b>
     *
     * @param rawStatData
     * @return
     */
    public static ArrayList<Vehicle> convertRawDataToVehicleList(final ArrayList<String> rawStatData) {
        ArrayList<Vehicle> result = new ArrayList<>();
        int cursor = 0;
        int cursorOffset;
        StatDataSet statDataSet;

        while (true) {
            // Checking values in the list for pattern:
            // either: A A
            // or: A B A B
            String value0 = rawStatData.get(cursor);
            String value1 = rawStatData.get(cursor + 1);

            if (value1.startsWith(RoadSection.SENSOR_A)) {
                statDataSet = new StatDataSet(value0, null, value1, null);
                cursorOffset = 2;
            } else {
                statDataSet = new StatDataSet(value0, value1, rawStatData.get(cursor + 2), rawStatData.get(cursor + 3));
                cursorOffset = 4;
            }

            result.add(new Vehicle(statDataSet));
            cursor += cursorOffset;

            if (cursor >= rawStatData.size()) {
                break;
            }
        }
        return result;
    }
}
