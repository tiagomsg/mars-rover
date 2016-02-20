package com.moj.codetest.model;

/**
 * Implementation for a map that is represented by a rectangular grid which bottom left corner is coordinate (0,0)
 * and the top right corner is within the first quadrant of the grid (x &gt; 0, Y &gt; 0)
 */
public class Map implements MapLimits {

    private Coordinates topRight;

    private Map(Coordinates topRight) {
        this.topRight = topRight;
    }

    /**
     * @param coord The coordinates to check if are within the map limits
     * @return True if the coordinates are within the map's boundaries. False otherwise.
     */
    public boolean isInLimits(Coordinates coord) {
        if (coord.getX() < 0 || coord.getX() > topRight.getX()
                || coord.getY() < 0 || coord.getY() > topRight.getY())
            return false;

        return true;
    }


    /**
     * Builder for the Map implementation.
     * <p>
     * Validates if the provided coordinates are within the first quadrant of the grid.
     */
    public static class MapBuilder {

        private Coordinates topRightCoordinates;

        public MapBuilder() {
        }

        public MapBuilder withTopRightCoordinates(String input) {
            topRightCoordinates = new Coordinates(input);
            return this;
        }

        /**
         * Builds the map.
         *
         * @return Map instance
         * @throws IllegalStateException If the top right coordinates are not provided
         * @throws IllegalStateException If the top right coordinates are not in the first quadrant
         */
        public Map build() {
            validateMap();
            return new Map(topRightCoordinates);
        }

        private void validateMap() {
            if (topRightCoordinates == null)
                throw new IllegalStateException("Please provide top right coordinates.");
            if (topRightCoordinates.getX() <= 0 || topRightCoordinates.getY() <= 0)
                throw new IllegalStateException("Please provide valid top right coordinates of the map (x > 0 && y > 0).");
        }
    }
}
