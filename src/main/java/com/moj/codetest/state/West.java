package com.moj.codetest.state;

/**
 * West representation of the vehicle's direction.
 */
public class West implements VehicleState {

    private Position position;

    public West(Position position) {
        this.position = position;
    }

    @Override
    public VehicleState left() {
        return new South(position);
    }

    @Override
    public VehicleState right() {
        return new North(position);
    }

    @Override
    public void move() {
        position.decreaseX();
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Returns a string representation of the state of the vehicle.
     *
     * @return String in the format <i>"currentPosition W"</i> where W represents West.
     */
    @Override
    public String toString() {
        return position.toString() + " " + "W";
    }
}
