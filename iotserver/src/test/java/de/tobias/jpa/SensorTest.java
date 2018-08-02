package de.tobias.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import de.tobias.jpa.Sensor;

public class SensorTest {

    @Test
    public void testSensor() {
        final Sensor s = new Sensor();
        assertEquals(1, 1);
    }

    @Test
    public void testId() {
        final Sensor s = new Sensor();
        s.setId(123l);
        assertEquals(123l, s.getId());
    }

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

}
