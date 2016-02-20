package com.moj.codetest.model;

/**
 * Interface to confirm the point is withing the boundaries of the map.
 */
public interface MapLimits {

    boolean isInLimits(Coordinates coord);
}
