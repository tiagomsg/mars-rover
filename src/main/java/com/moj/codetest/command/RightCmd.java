package com.moj.codetest.command;

import com.moj.codetest.model.Vehicle;

/**
 * Right instruction implementation. Turns the vehicle to the right.
 */
public class RightCmd implements Command {
    @Override
    public void execute(Vehicle vehicle) {
        vehicle.rotateRight();
    }
}
