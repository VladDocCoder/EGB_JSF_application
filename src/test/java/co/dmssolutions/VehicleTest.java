package co.dmssolutions;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;


public class VehicleTest {

    /**
     * Test of getTimeStamp method, of class Vehicle.
     */
    @Test
    public void getTimeStamp_test() {
        System.out.println("getTimeStamp");

        String valueA1 = "A1624044";
        long valueA1_long = Long.parseLong("1624044");
        String valueB1 = "B1624047";
        String valueA2 = "A1624188";
        String valueB2 = "B1624191";

        Vehicle instance = new Vehicle(new StatDataSet(valueA1, valueB1, valueA2, valueB2));

        assertEquals(new Date(valueA1_long).getTime(), instance.getTimeStamp().getTime());

    }


    @Test
    public void getSpeed_test() {
        System.out.println("getSpeed");

        String valueA1 = "A1624044";
        String valueB1 = "B1624047";
        String valueA2 = "A1624188";
        String valueB2 = "B1624191";

        Vehicle instance = new Vehicle(new StatDataSet(valueA1, valueB1, valueA2, valueB2));
        assertEquals(62, instance.getSpeed());
    }

}
