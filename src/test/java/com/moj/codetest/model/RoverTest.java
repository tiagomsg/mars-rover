package com.moj.codetest.model;


import com.moj.codetest.state.Position;
import com.moj.codetest.state.VehicleState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoverTest {

    @Mock
    private VehicleState vehicleState;

    @Mock
    private Position position;

    private Vehicle rover;

    @Before
    public void setup() {
        rover = new Rover(vehicleState);
    }


    @Test
    public void testMove_callsDirectionMove() {
        rover.move();

        verify(vehicleState).move();
    }

    @Test
    public void testRotateRight_callsDirectionRight() {
        rover.rotateRight();

        verify(vehicleState).right();
    }

    @Test
    public void testRotateLeft_callsDirectionLeft() {
        rover.rotateLeft();

        verify(vehicleState).left();
    }
}
