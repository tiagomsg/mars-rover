package com.moj.codetest.model;


import com.moj.codetest.state.VehicleState;


/**
 * Vehicle implementation for the Rover.
 * <p>
 * State pattern applied to manage its direction and position
 */
public class Rover implements Vehicle {

    /**
     * The state of the vehicle
     */
    private VehicleState currentVehicleState;

    /**
     * Constructor
     *
     * @param startVehicleState The first state of the vehicle
     */
    public Rover(VehicleState startVehicleState) {
        this.currentVehicleState = startVehicleState;
    }

    public void move() {
        currentVehicleState.move();
    }

    public void rotateRight() {
        currentVehicleState = currentVehicleState.right();
    }

    public void rotateLeft() {
        currentVehicleState = currentVehicleState.left();
    }

    /**
     * @return String representing the vehicle state with the format
     */
    @Override
    public String toString() {
        return currentVehicleState.toString();
    }
}
