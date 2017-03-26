package co.dmssolutions;

import java.util.Date;


/**
 * Class represents physical vehicle
 *
 * @author DMS Solutions
 */
public class Vehicle {

    private final StatDataSet _statDataSet;
    private double _axleBase = 2.5;


    public Vehicle(StatDataSet statDataSet) {
        _statDataSet = statDataSet;
    }


    // Getters & Setters --------------------------------------------------------------------------
    /**
     * Returns vehicle axle wheelbase in meters
     *
     * @return vehicle axle wheelbase in meters
     */
    public double getAxleBase() {
        return _axleBase;
    }


    /**
     * Sets vehicle axle wheelbase in Meters<br>
     * By default it set to 2.5m
     *
     * @param axleBaseInMeters vehicle axle wheelbase in meters
     */
    public void setAxleBase(double axleBaseInMeters) {
        _axleBase = axleBaseInMeters;
    }


    /**
     * Returns main timestamp when front axle of the vehicle crossed sensor for the first time
     *
     * @return
     */
    public Date getTimeStamp() {
        return _statDataSet.getTimeStamp();
    }


    /**
     * Returns speed of the vehicle (calculated based on sensor A)
     *
     * @return
     */
    public int getSpeed() {
        double delta = ((double) (_statDataSet.getTimeStampA2() - _statDataSet.getTimeStampA1())) / (double) 1000 / (double) 60 / (double) 60; // delta in ms, converted to sec -> min -> hours
        return (int) ((_axleBase / (double) 1000) / delta); // axleBase in m converted to km
    }


    /**
     * Returns vehicle direction
     *
     * @return
     */
    public int getDirection() {
        if (_statDataSet.getValueB1() == null) {
            return RoadSection.DIRECTION_A;
        } else {
            return RoadSection.DIRECTION_B;
        }
    }
    
    public StatDataSet getStatDataSet(){
        return _statDataSet;
    }
}
