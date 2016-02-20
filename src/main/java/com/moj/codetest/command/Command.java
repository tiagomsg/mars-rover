package com.moj.codetest.command;


import com.moj.codetest.model.Vehicle;

/**
 * Interface for the Command pattern. Execute actions on a vehicle.
 */
public interface Command {

    void execute(Vehicle vehicle);
}
