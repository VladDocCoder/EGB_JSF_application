package co.dmssolutions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;
import static org.junit.Assert.*;


public class StatDataSetTest {

    /**
     * Test of parseSensorData method, of class StatDataSet.
     */
    @Test
    public void parseSensorData_test() {
        System.out.println("parseSensorData");
        assertEquals(0L, StatDataSet.parseSensorData(null));
        assertEquals(1624044L, StatDataSet.parseSensorData("A1624044"));
        assertEquals(1624047L, StatDataSet.parseSensorData("B1624047"));
    }


    /**
     * Test of getTimeStamp method, of class StatDataSet.
     */
    @Test
    public void getTimeStamp_test1() {
        System.out.println("getTimeStamp");
        String valueA1 = "A1624044";
        long valueA1_long = 1624044L;
        String valueB1 = "B1624047";
        long valueB1_long = 1624047L;
        String valueA2 = "A1624188";
        long valueA2_long = 1624188L;
        String valueB2 = "B1624191";
        long valueB2_long = 1624191L;

        StatDataSet instance = new StatDataSet(valueA1, valueB1, valueA2, valueB2);
        assertEquals(new Date(valueA1_long).getTime(), instance.getTimeStamp().getTime());

        assertEquals(new Date(valueA1_long).getTime(), instance.getTimeStampA1());
        assertEquals(new Date(valueB1_long).getTime(), instance.getTimeStampB1());
        assertEquals(new Date(valueA2_long).getTime(), instance.getTimeStampA2());
        assertEquals(new Date(valueB2_long).getTime(), instance.getTimeStampB2());
    }


    @Test
    public void getTimeStamp_test2() {
        System.out.println("getTimeStamp");
        String valueA1 = "A86351522";
        String valueB1 = "B86351525";
        String valueA2 = "A86351669";
        String valueB2 = "B86351672";

        StatDataSet instance = new StatDataSet(valueA1, valueB1, valueA2, valueB2);

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals("1970-01-01 23:59:11", sdf.format(instance.getTimeStamp()));
    }

}
