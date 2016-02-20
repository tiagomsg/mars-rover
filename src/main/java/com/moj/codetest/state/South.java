package com.moj.codetest.state;

/**
 * South representation of the vehicle's direction.
 */
public class South implements VehicleState {

    private Position position;

    public South(Position position) {
        this.position = position;
    }

    @Override
    public VehicleState left() {
        return new East(position);
    }

    @Override
    public VehicleState right() {
        return new West(position);
    }

    @Override
    public void move() {
        position.decreaseY();
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Returns a string representation of the state of the vehicle.
     *
     * @return  String in the format <i>"currentPosition S"</i> where S represents South.
     */
    @Override
    public String toString() {
        return position.toString() + " " + "S";
    }
}
