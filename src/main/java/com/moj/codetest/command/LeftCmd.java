package com.moj.codetest.command;

import com.moj.codetest.model.Vehicle;

/**
 * Left instruction implementation. Turns the vehicle to the left.
 */
public class LeftCmd implements Command {
    @Override
    public void execute(Vehicle vehicle) {
        vehicle.rotateLeft();
    }
}
