package com.moj.codetest.state;

/**
 * Factory to create new VehicleState.
 */
public class VehicleStateFactory {

    /**
     * Takes a direction and position and returns an object that represents a vehicle state.
     *
     * @param direction Char representation (<i>'N'</i>,<i>'S'</i>,...) of direction.
     * @param position  Position in a map.
     * @return Object that represents the direction and contains the position of the vehicle.
     * @throws IllegalArgumentException If the direction is not supported.
     */
    public static VehicleState create(char direction, Position position) {
        switch (direction) {
            case 'N':
                return new North(position);
            case 'E':
                return new East(position);
            case 'S':
                return new South(position);
            case 'W':
                return new West(position);
            default:
                throw new IllegalArgumentException("Invalid direction: '" + direction + "'.");
        }
    }
}
