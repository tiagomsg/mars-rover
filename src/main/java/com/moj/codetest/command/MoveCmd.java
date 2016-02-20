package com.moj.codetest.command;

import com.moj.codetest.model.Vehicle;

/**
 * Move instruction implementation. Moves the vehicle in its current direction.
 */
public class MoveCmd implements Command {
    @Override
    public void execute(Vehicle vehicle) {
        vehicle.move();
    }
}
