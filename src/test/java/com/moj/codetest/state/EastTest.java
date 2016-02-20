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
public class EastTest {

    @Mock
    private Position position;

    private East east;

    @Before
    public void setup() {
        east = new East(position);
    }

    @Test
    public void testMove_callsPositionIncreaseX() {
        east.move();

        verify(position).increaseX();
    }

    @Test
    public void testRight_returnsSouth() {
        VehicleState vehicleState = east.right();

        assertTrue("Turns south", vehicleState instanceof South);
        assertSame("Maintains position", east.getPosition(), ((South) vehicleState).getPosition());
    }

    @Test
    public void testLeft_returnsNorth() {
        VehicleState vehicleState = east.left();

        assertTrue("Turns north", vehicleState instanceof North);
        assertSame("Maintains position", east.getPosition(), ((North) vehicleState).getPosition());
    }
}
