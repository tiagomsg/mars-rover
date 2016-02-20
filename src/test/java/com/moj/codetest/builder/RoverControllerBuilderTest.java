package com.moj.codetest.builder;


import com.moj.codetest.command.*;
import com.moj.codetest.controller.VehicleController;
import com.moj.codetest.model.Coordinates;
import com.moj.codetest.model.MapLimits;
import com.moj.codetest.state.Position;
import com.moj.codetest.state.VehicleState;
import com.moj.codetest.state.VehicleStateFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( {VehicleStateFactory.class, CommandFactory.class })
public class RoverControllerBuilderTest {

    @Mock
    private MapLimits map;

    @Mock
    private VehicleState vehicleState;

    @Mock
    private Position position;

    @Mock
    private Command commmand;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void setup() {
        when(map.isInLimits(any(Coordinates.class))).thenReturn(true);
    }

    @Test
    public void testWithStartState_whenInputWithInvalidFormat_throwException() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Start state must be in format 'x y d' where x and y are valid numbers and d is a valid char");

        RoverControllerBuilder.aRoverController().withStartState("invalid");
    }

    @Test
    public void testBuild_callsDirectionFactoryWithInputDirection() {
        PowerMockito.mockStatic(VehicleStateFactory.class);
        when(VehicleStateFactory.create(anyChar(), any(Position.class))).thenReturn(vehicleState);

        RoverControllerBuilder.aRoverController()
                .withMapLimits(map)
                .withStartState("1 2 N")
                .build();

        PowerMockito.verifyStatic();
        VehicleStateFactory.create(eq('N'), any(Position.class));
    }

    @Test
    public void testBuild_whenValidStartState_thenRoverContainsDirectionFromFactory() {
        String startState = "1 2 N";
        PowerMockito.mockStatic(VehicleStateFactory.class);
        when(VehicleStateFactory.create(anyChar(), any(Position.class))).thenReturn(vehicleState);
        when(vehicleState.toString()).thenReturn(startState);

        VehicleController controller = RoverControllerBuilder.aRoverController()
                .withMapLimits(map)
                .withStartState(startState)
                .build();

        assertSame("Vehicle has direction from factory", controller.getVehicleState(), startState);
    }

    @Test
    public void testBuild_whenValidStartState_thenRoverIsInRightPosition() {
        ArgumentCaptor<Position> argument = ArgumentCaptor.forClass(Position.class);
        PowerMockito.mockStatic(VehicleStateFactory.class);
        when(VehicleStateFactory.create(anyChar(), any(Position.class))).thenReturn(vehicleState);

        VehicleController controller = RoverControllerBuilder.aRoverController()
                .withMapLimits(map)
                .withStartState("-1 2 N")
                .build();

        PowerMockito.verifyStatic();
        VehicleStateFactory.create(eq('N'), argument.capture());
        Position startPosition = argument.getValue();
        assertSame("Vehicle is in right x coordinates", startPosition.getCoordinates().getX(), -1);
        assertSame("Vehicle is in right y coordinates", startPosition.getCoordinates().getY(), 2);
    }

    @Test
    public void testBuildGetCommands_whenSingleCommand_callsCommandFactoryOnce() {
        PowerMockito.mockStatic(VehicleStateFactory.class);
        when(VehicleStateFactory.create(anyChar(), any(Position.class))).thenReturn(vehicleState);
        PowerMockito.mockStatic(CommandFactory.class);
        when(CommandFactory.toCommand(eq('M'))).thenReturn(commmand);

        RoverControllerBuilder.aRoverController()
                .withMapLimits(map)
                .withStartState("1 2 N")
                .withInstructions("M")
                .build();

        PowerMockito.verifyStatic();
        CommandFactory.toCommand(eq('M'));
    }

    @Test
    public void testBuildGetCommands_whenMultipleCommands_callsCommandFactoryMultipleTimes() {
        PowerMockito.mockStatic(VehicleStateFactory.class);
        when(VehicleStateFactory.create(anyChar(), any(Position.class))).thenReturn(vehicleState);
        PowerMockito.mockStatic(CommandFactory.class);
        when(CommandFactory.toCommand(anyChar())).thenReturn(commmand);

        RoverControllerBuilder.aRoverController()
                .withMapLimits(map)
                .withStartState("1 2 N")
                .withInstructions("MLR")
                .build();

        PowerMockito.verifyStatic();
        CommandFactory.toCommand(eq('M'));
        PowerMockito.verifyStatic();
        CommandFactory.toCommand(eq('L'));
        PowerMockito.verifyStatic();
        CommandFactory.toCommand(eq('R'));
    }

    @Test
    public void testBuildGetCommands_whenValidCommand_thenControllerContainsRightCommandsInRightOrder() {
        PowerMockito.mockStatic(VehicleStateFactory.class);
        when(VehicleStateFactory.create(anyChar(), any(Position.class))).thenReturn(vehicleState);

        VehicleController controller = RoverControllerBuilder.aRoverController()
                .withMapLimits(map)
                .withStartState("1 2 N")
                .withInstructions("MLR")
                .build();

        List<Command> commandList = controller.getCommands();
        assertEquals("Contains all commands", commandList.size(), 3);
        assertTrue("Contains 1st command", commandList.get(0) instanceof MoveCmd);
        assertTrue("Contains 2nd command", commandList.get(1) instanceof LeftCmd);
        assertTrue("Contains 3rd command", commandList.get(2) instanceof RightCmd);
    }

    @Test
    public void testBuild_whenNoStartState_throwException() {
        expected.expect(NullPointerException.class);
        expected.expectMessage("Must provide start state for the vehicle.");

        RoverControllerBuilder.aRoverController().withMapLimits(map).build();
    }

    @Test
    public void testBuild_whenNoMapLimits_throwException() {
        expected.expect(NullPointerException.class);
        expected.expectMessage("Must provide map limits.");

        RoverControllerBuilder.aRoverController().withStartState("1 2 N").build();
    }

    @Test
    public void testBuild_whenNoInstruction_thenControllerWithNoCommands() {
        PowerMockito.mockStatic(VehicleStateFactory.class);
        when(VehicleStateFactory.create(anyChar(), any(Position.class))).thenReturn(vehicleState);

        VehicleController controller = RoverControllerBuilder.aRoverController()
                .withMapLimits(map)
                .withStartState("1 2 N")
                .build();

        List<Command> commandList = controller.getCommands();
        assertEquals("Contains no commands", commandList.size(), 0);
    }
}
