package com.moj.codetest.controller;


import com.moj.codetest.command.Command;
import com.moj.codetest.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoverControllerTest {

    @Mock
    private Vehicle vehicle;

    @Mock
    private Command command1;
    @Mock
    private Command command2;

    private RoverController controller;

    @Before
    public void setup() {
        List<Command> commands = Arrays.asList(command1, command2);
        controller = new RoverController(vehicle, commands);
    }

    @Test
    public void testGetVehicleState_returnsVehicleToString() {
        String expectedState = "test state";
        when(vehicle.toString()).thenReturn(expectedState);

        String state = controller.getVehicleState();

        assertSame("Returns vehicle toString result", state, expectedState);
    }

    @Test
    public void testExecuteCommands_thenExecutesAllCommandsInOrder() {
        InOrder inOrder = inOrder(command1, command2);

        controller.executeCommands();

        inOrder.verify(command1).execute(eq(vehicle));
        inOrder.verify(command2).execute(eq(vehicle));
    }

    @Test
    public void testToString_returnsVehicleToString() {
        String expectedState = "test state";
        when(vehicle.toString()).thenReturn(expectedState);

        String state = controller.toString();

        assertSame("Returns vehicle toString result", state, expectedState);
    }
}
