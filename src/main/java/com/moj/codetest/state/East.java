package com.moj.codetest.state;

/**
 * East representation of the vehicle's direction.
 */
public class East implements VehicleState {

    private Position position;

    public East(Position position) {
        this.position = position;
    }

    @Override
    public VehicleState left() {
        return new North(position);
    }

    @Override
    public VehicleState right() {
        return new South(position);
    }

    @Override
    public void move() {
        position.increaseX();
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Returns a string representation of the state of the vehicle.
     *
     * @return String in the format <i>"currentPosition E"</i> where E represents East.
     */
    @Override
    public String toString() {
        return position.toString() + " " + "E";
    }
}
