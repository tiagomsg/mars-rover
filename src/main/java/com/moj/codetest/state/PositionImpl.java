package com.moj.codetest.state;


import com.moj.codetest.model.Coordinates;
import com.moj.codetest.model.MapLimits;

import java.util.Objects;


/**
 * Representation of the position of an object in a grid.
 * <p>
 * Used to represent the position of a vehicle in a map. Validates that it stays within the limits.
 */
public class PositionImpl implements Position {

    private MapLimits mapLimits;
    private Coordinates coordinates;

    /**
     * @param mapLimits   Map limits
     * @param coordinates Coordinates for the position
     * @throws IllegalArgumentException If the coordinates are out of the map's limits.
     * @throws NullPointerException     If any of the parameters is null.
     */
    public PositionImpl(MapLimits mapLimits, Coordinates coordinates) {
        Objects.requireNonNull(mapLimits, "Please provide map limits");
        Objects.requireNonNull(coordinates, "Please provide coordinates");

        this.mapLimits = mapLimits;
        if (mapLimits.isInLimits(coordinates))
            this.coordinates = coordinates;
        else
            throw new IllegalArgumentException("Invalid start position.");
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Increases the Y coordinate of the position in one unit.
     *
     * @throws IllegalStateException If it goes out of the map's limits.
     */
    public void increaseY() {
        Coordinates nextPosition = new Coordinates(coordinates.getX(), coordinates.getY() + 1);
        if (!mapLimits.isInLimits(nextPosition))
            throw new IllegalStateException("Can't go out of limits!");
        this.coordinates = nextPosition;
    }

    /**
     * Increases the X coordinate of the position in one unit.
     *
     * @throws IllegalStateException If it goes out of the map's limits.
     */
    public void increaseX() {
        Coordinates nextPosition = new Coordinates(coordinates.getX() + 1, coordinates.getY());
        if (!mapLimits.isInLimits(nextPosition))
            throw new IllegalStateException("Can't go out of limits!");
        this.coordinates = nextPosition;
    }

    /**
     * Decreases the Y coordinate of the position in one unit.
     *
     * @throws IllegalStateException If it goes out of the map's limits.
     */
    public void decreaseY() {
        Coordinates nextPosition = new Coordinates(coordinates.getX(), coordinates.getY() - 1);
        if (!mapLimits.isInLimits(nextPosition))
            throw new IllegalStateException("Can't go out of limits!");
        this.coordinates = nextPosition;
    }

    /**
     * Decreases the X coordinate of the position in one unit.
     *
     * @throws IllegalStateException If it goes out of the map's limits.
     */
    public void decreaseX() {
        Coordinates nextPosition = new Coordinates(coordinates.getX() - 1, coordinates.getY());
        if (!mapLimits.isInLimits(nextPosition))
            throw new IllegalStateException("Can't go out of limits!");
        this.coordinates = nextPosition;
    }

    @Override
    public String toString() {
        return coordinates.toString();
    }
}
