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
public class SouthTest {

    @Mock
    private Position position;

    private South south;

    @Before
    public void setup() {
        south = new South(position);
    }

    @Test
    public void testMove_callsPositionDecreaseY() {
        south.move();

        verify(position).decreaseY();
    }

    @Test
    public void testRight_returnsWest() {
        VehicleState vehicleState = south.right();

        assertTrue("Turns west", vehicleState instanceof West);
        assertSame("Maintains position", south.getPosition(), ((West) vehicleState).getPosition());
    }

    @Test
    public void testLeft_returnsEast() {
        VehicleState vehicleState = south.left();

        assertTrue("Turns east", vehicleState instanceof East);
        assertSame("Maintains position", south.getPosition(), ((East) vehicleState).getPosition());
    }
}
