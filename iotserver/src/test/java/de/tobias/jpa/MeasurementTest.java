package de.tobias.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import de.tobias.jpa.Measurement;

public class MeasurementTest {

    @Test
    public void testMeasurement() {
        final Measurement m = new Measurement();
        assertEquals(1, 1);
    }

    @Test
    public void testId() {
    	final Measurement m = new Measurement();
        m.setId(123l);
        assertEquals(123l, m.getId());
    }
/*
    @Test
    public void testDevice() {
        final Sensor s = new Sensor();
        s.setDevice("device");
        assertEquals("device", s.getDevice());

    }

    @Test
    public void testType() {
        final Sensor s = new Sensor();
        s.setType("type1");
        assertEquals("type1", s.getType());
    }

    @Test
    public void testDescription() {
        final Sensor s = new Sensor();
        s.setDescription("desc1");
        assertEquals("desc1", s.getDescription());
    }
*/
}
