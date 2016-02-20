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
public class NorthTest {

    @Mock
    private Position position;

    private North north;

    @Before
    public void setup() {
        north = new North(position);
    }

    @Test
    public void testMove_callsPositionIncreaseY() {
        north.move();

        verify(position).increaseY();
    }

    @Test
    public void testRight_returnsEast() {
        VehicleState vehicleState = north.right();

        assertTrue("Turns east", vehicleState instanceof East);
        assertSame("Maintains position", north.getPosition(), ((East) vehicleState).getPosition());
    }

    @Test
    public void testLeft_returnsWest() {
        VehicleState vehicleState = north.left();

        assertTrue("Turns west", vehicleState instanceof West);
        assertSame("Maintains position", north.getPosition(), ((West) vehicleState).getPosition());
    }

}
