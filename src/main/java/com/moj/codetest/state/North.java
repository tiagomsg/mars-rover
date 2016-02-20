package com.moj.codetest.state;

/**
 * North representation of the vehicle's direction.
 */
public class North implements VehicleState {

    private Position position;

    public North(Position position) {
        this.position = position;
    }

    @Override
    public VehicleState left() {
        return new West(position);
    }

    @Override
    public VehicleState right() {
        return new East(position);
    }

    @Override
    public void move() {
        position.increaseY();
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Returns a string representation of the state of the vehicle.
     *
     * @return String in the format <i>"currentPosition N"</i> where N represents North.
     */
    @Override
    public String toString() {
        return position.toString() + " " + "N";
    }
}
