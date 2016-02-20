package com.moj.codetest.model;


import java.util.Objects;

/**
 * Represents a point in a map.
 */
public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct from string representation of coordinates.
     *
     * @param coordStr In the format <i>"x y"</i> where <i>x</i> and <i>y</i> are integers.
     * @throws IllegalArgumentException If string not in the right format.
     */
    public Coordinates(String coordStr) {
        Objects.requireNonNull(coordStr, "Please provide coordinates in the right format ('x y').");

        String[] coordsArray = coordStr.split(" ");
        if (coordsArray.length != 2)
            throw new IllegalArgumentException("Please provide coordinates in the right format ('x y').");

        try {
            x = Integer.parseInt(coordsArray[0]);
            y = Integer.parseInt(coordsArray[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please provide coordinates in the following format 'x y' where x and y are valid numbers.");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }


}
