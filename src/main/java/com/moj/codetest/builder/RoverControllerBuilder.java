package com.moj.codetest.builder;


import com.moj.codetest.command.Command;
import com.moj.codetest.command.CommandFactory;
import com.moj.codetest.controller.RoverController;
import com.moj.codetest.controller.VehicleController;
import com.moj.codetest.model.Coordinates;
import com.moj.codetest.model.MapLimits;
import com.moj.codetest.model.Rover;
import com.moj.codetest.state.Position;
import com.moj.codetest.state.PositionImpl;
import com.moj.codetest.state.VehicleState;
import com.moj.codetest.state.VehicleStateFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Builder for the Rover Controller.
 */
public class RoverControllerBuilder {

    private MapLimits mapLimits;
    private Coordinates startCoordinates;
    private char startDirection;
    private String instructions;

    private RoverControllerBuilder() {
        instructions = "";
    }

    public static RoverControllerBuilder aRoverController() {
        return new RoverControllerBuilder();
    }

    public RoverControllerBuilder withMapLimits(MapLimits mapLimits) {
        this.mapLimits = mapLimits;
        return this;
    }

    /**
     * Provide the initial state of the vehicle (position and direction)
     *
     * @param input string representation of the vehicle's initial state in the format <i>"x y d"</i>:
     *              <ul>
     *                  <li><i>x</i> - horizontal coordinate</li>
     *                  <li><i>y</i> - vertical coordinate</li>
     *                  <li><i>d</i> - direction</li>
     *              </ul>
     * @return Instance of RoverControllerBuilder.
     * @throws IllegalArgumentException If the input is in an invalid format.
     */
    public RoverControllerBuilder withStartState(String input) {

        String[] splittedInput = input.split(" ");
        if (splittedInput.length != 3)
            throw new IllegalArgumentException("Start state must be in format 'x y d' where x and y are valid numbers and d is a valid char");

        this.startCoordinates = new Coordinates(splittedInput[0] + " " + splittedInput[1]);
        this.startDirection = splittedInput[2].charAt(0);

        return this;
    }

    public RoverControllerBuilder withInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    /**
     * Builds the controller with the provided information.
     * <p>
     * Uses the VehicleStateFactory to ensure a proper initial state for the vehicle.
     * <p>
     * Uses the CommandFactory to translate the client instructions into commands.
     *
     * @return Instance of RoverController to control a Mars Rover vehicle.
     * @throws NullPointerException If MapLimits or StartState are not provided.
     */
    public VehicleController build() {
        Objects.requireNonNull(mapLimits, "Must provide map limits.");
        Objects.requireNonNull(startCoordinates, "Must provide start state for the vehicle.");

        Position position = new PositionImpl(mapLimits, startCoordinates);
        VehicleState vehicleState = VehicleStateFactory.create(startDirection, position);
        Rover rover = new Rover(vehicleState);

        List<Command> commandList = getCommands();
        return new RoverController(rover, commandList);
    }

    private List<Command> getCommands() {
        return instructions.chars()
                .mapToObj(i -> CommandFactory.toCommand((char) i))
                .collect(Collectors.toList());
    }
}
