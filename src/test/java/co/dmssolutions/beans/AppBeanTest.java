package co.dmssolutions.beans;

import co.dmssolutions.Vehicle;
import co.dmssolutions.Util;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;


/**
 *
 * @author DMS Solutions
 */
public class AppBeanTest {

    // project containse reference sample of data for test purposes
    private static final String EMBEDDED_TEST_DATA_FILE = "testData.txt";
    private static final long EMBEDDED_TEST_DATA_FILE_SIZE = 136775;
    private static final int EMBEDDED_TEST_DATA_LIST_SIZE = 13700;
    private static final int EMBEDDED_TEST_DATA_LIST_VEHICLES_NUMBER = 4540;


    @Test
    public void embedded_data_load_test() {
        System.out.println("Testing load of embedded data file");
        AppBean appBean = new AppBean();
        File data = appBean.loadEmbeddedDataFile(EMBEDDED_TEST_DATA_FILE);
        assertNotNull(data);
        if (data.length() != EMBEDDED_TEST_DATA_FILE_SIZE) {
            fail("Reference test file is not valid");
        }
    }


    @Test
    public void convertRawDataToVehicleList_test() {
        System.out.println("Testing of data file parsing");
        AppBean appBean = new AppBean();
        File data = appBean.loadEmbeddedDataFile(EMBEDDED_TEST_DATA_FILE);

        ArrayList<String> carsByDays = null;
        try {
            carsByDays = Util.processRawStatData(new FileInputStream(data));
        } catch (FileNotFoundException ex) {
            fail(ex.toString());
        }

        assertNotNull(carsByDays);
        assertEquals("Wrong number of lines found", EMBEDDED_TEST_DATA_LIST_SIZE, carsByDays.size());

        ArrayList<Vehicle> vehicleList = Util.convertRawDataToVehicleList(carsByDays);
        assertEquals("rawStatList to Vehicle list conversion error.", EMBEDDED_TEST_DATA_LIST_VEHICLES_NUMBER, vehicleList.size());
    }

}
