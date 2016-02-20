package com.moj.codetest.state;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WestTest {

    @Mock
    private Position position;

    private West west;

    @Before
    public void setup() {
        west = new West(position);
    }

    @Test
    public void testMove_callsPositionDecreaseX() {
        west.move();

        verify(position).decreaseX();
    }

    @Test
    public void testRight_returnsNorth() {
        VehicleState vehicleState = west.right();

        assertTrue("Turns north", vehicleState instanceof North);
        assertSame("Maintains position", west.getPosition(), ((North) vehicleState).getPosition());
    }

    @Test
    public void testLeft_returnsSouth() {
        VehicleState vehicleState = west.left();

        assertTrue("Turns south", vehicleState instanceof South);
        assertSame("Maintains position", west.getPosition(), ((South) vehicleState).getPosition());
    }
}
