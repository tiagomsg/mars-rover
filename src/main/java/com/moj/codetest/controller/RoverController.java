package com.moj.codetest.controller;


import com.moj.codetest.command.Command;
import com.moj.codetest.model.Vehicle;

import java.util.List;

/**
 * Implementation of a controller for the Mars Rover.
 * <p>
 * It allows the ordered execution of commands on a vehicle.
 * <p>
 * Serves as an interface between the vehicle and the client, ensuring the vehicle is correctly managed.
 */
public class RoverController implements VehicleController {

    private Vehicle vehicle;
    private List<Command> commands;

    public RoverController(Vehicle vehicle, List<Command> commands) {
        this.vehicle = vehicle;
        this.commands = commands;
    }

    /**
     * Executes the provided commands on the vehicle
     */
    @Override
    public void executeCommands() {
        commands.stream().forEach(command -> {
            command.execute(vehicle);
        });
    }

    /**
     * Provides a string representation of the state of the vehicle.
     *
     * @return Vehicle state in the format <i>"x y d"</i> where:
     *         <ul>
     *             <li><i>x</i> - horizontal coordinate</li>
     *             <li><i>y</i> - vertical coordinate</li>
     *             <li><i>d</i> - direction</li>
     *         </ul>
     */
    @Override
    public String getVehicleState() {
        return vehicle.toString();
    }

    @Override
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Returns the vehicle state
     *
     * @return Vehicle state
     */
    @Override
    public String toString() {
        return getVehicleState();
    }
}
