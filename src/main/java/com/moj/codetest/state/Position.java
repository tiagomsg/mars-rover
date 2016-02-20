package com.moj.codetest.state;


import com.moj.codetest.model.Coordinates;

/**
 * Interface for the position of a an object in a grid.
 */
public interface Position {

    Coordinates getCoordinates();

    void increaseY();

    void increaseX();

    void decreaseY();

    void decreaseX();

}
