package co.dmssolutions;

import java.util.Date;


/**
 * Class holds sensor data for one physical vehicle<br>
 * It can be based on either 2 sensor values data:<br>
 * A12341<br>
 * A12345<br><br>
 * or 4 sensor values data:<br>
 * A12341<br>
 * B12342<br>
 * A12345<br>
 * B12346<br>
 *
 * @author DMS Solutions
 */
public class StatDataSet {

    private final String _valueA1;
    private final String _valueB1;
    private final String _valueA2;
    private final String _valueB2;

    private final long _timeStampA1;
    private final long _timeStampB1;
    private final long _timeStampA2;
    private final long _timeStampB2;


    /**
     * Creates instance based on provided sensor data values
     *
     * @param valueA1 value for sensor A (front axel), not null
     * @param valueB1 value for sensor B (front axel), can be null
     * @param valueA2 value for sensor A (rear axel), not null
     * @param valueB2 value for sensor B (rear axel), can be null
     */
    public StatDataSet(String valueA1, String valueB1, String valueA2, String valueB2) {
        _valueA1 = valueA1;
        _valueB1 = valueB1;
        _valueA2 = valueA2;
        _valueB2 = valueB2;

        _timeStampA1 = parseSensorData(valueA1);
        _timeStampB1 = parseSensorData(valueB1);
        _timeStampA2 = parseSensorData(valueA2);
        _timeStampB2 = parseSensorData(valueB2);
    }


    /**
     * Parses sensor raw data into long value of milliseconds
     *
     * @param sensorData raw sensor data string in format AXXXXXX | BXXXXXX
     * @return parsed part of XXXXXX
     */
    public static long parseSensorData(String sensorData) {
        if (sensorData != null) {
            return Long.valueOf(sensorData.replaceAll("^\\w", ""));
        }
        return 0L;
    }


    // Getters & Setters --------------------------------------------------------------------------
    /**
     * Return TimeStamp based on the most first sensor data A1
     *
     * @return
     */
    public Date getTimeStamp() {
        return new Date(_timeStampA1);
    }


    public String getValueA1() {
        return _valueA1;
    }


    public String getValueB1() {
        return _valueB1;
    }


    public String getValueA2() {
        return _valueA2;
    }


    public String getValueB2() {
        return _valueB2;
    }


    public long getTimeStampA1() {
        return _timeStampA1;
    }


    public long getTimeStampB1() {
        return _timeStampB1;
    }


    public long getTimeStampA2() {
        return _timeStampA2;
    }


    public long getTimeStampB2() {
        return _timeStampB2;
    }

}
