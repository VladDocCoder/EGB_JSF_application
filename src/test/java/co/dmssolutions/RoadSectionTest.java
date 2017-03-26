/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.dmssolutions;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author DMS Solutions
 */
public class RoadSectionTest {

    @Test
    public void getMinMeanMaxSpeed_test() {
        System.out.println("getMaxSpeed");
        ArrayList<Vehicle> data = new ArrayList<>();
        data.add(new Vehicle(new StatDataSet("A98186", null, "A98333", null)));
        data.add(new Vehicle(new StatDataSet("A1016488", null, "A1016648", null)));
        data.add(new Vehicle(new StatDataSet("A1058535", "B1058538", "A1058659", "B1058662")));
        data.add(new Vehicle(new StatDataSet("A3280268", "B3280271", "A3280386", "B3280388")));
        data.add(new Vehicle(new StatDataSet("A3318189", null, "A3318355", null)));
        RoadSection roadSection = new RoadSection(data);
        assertEquals(54, (int) roadSection.getStats().getMin());
        assertEquals(63, (int) roadSection.getStats().getMean());
        assertEquals(76, (int) roadSection.getStats().getMax());
    }
}
