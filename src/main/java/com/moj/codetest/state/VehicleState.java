package com.moj.codetest.state;

/**
 * Vehicle state interface that enables the State pattern.
 * <p>
 * Each implementation represents a possible direction of the vehicle and contains its current position in the map.
 * <p>
 * Each must update the position accordingly when the move action is invoked.
 * i.e. North increments Y coordinate in one unit.
 * <p>
 * The left and right method return a new state according to the current one.
 * i.e. East returns South when right() is invoked.
 */
public interface VehicleState {

    VehicleState left();

    VehicleState right();

    void move();

    Position getPosition();
}
