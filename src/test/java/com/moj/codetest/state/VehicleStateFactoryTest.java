package com.moj.codetest.state;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertSame;

public class VehicleStateFactoryTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    private Position position;

    @Test
    public void testCreateDirection_whenN_returnNorth() {
        VehicleState vehicleState = VehicleStateFactory.create('N', position);

        assertTrue("Returns north", vehicleState instanceof North);
        assertSame("Adds position", position, ((North) vehicleState).getPosition());
    }

    @Test
    public void testCreateDirection_whenE_returnEast() {
        VehicleState vehicleState = VehicleStateFactory.create('E', position);

        assertTrue("Returns east", vehicleState instanceof East);
        assertSame("Adds position", position, ((East) vehicleState).getPosition());
    }

    @Test
    public void testCreateDirection_whenS_returnSouth() {
        VehicleState vehicleState = VehicleStateFactory.create('S', position);

        assertTrue("Returns south", vehicleState instanceof South);
        assertSame("Adds position", position, ((South) vehicleState).getPosition());
    }

    @Test
    public void testCreateDirection_whenW_returnWest() {
        VehicleState vehicleState = VehicleStateFactory.create('W', position);

        assertTrue("Returns west", vehicleState instanceof West);
        assertSame("Adds position", position, ((West) vehicleState).getPosition());
    }

    @Test
    public void testCreateDirection_whenInvalidDirection_throwException() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid direction: 'C'.");

        VehicleState vehicleState = VehicleStateFactory.create('C', position);
    }

}
