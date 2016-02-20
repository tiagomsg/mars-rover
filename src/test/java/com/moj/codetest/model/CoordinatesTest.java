package com.moj.codetest.model;


import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.builders.NullBuilder;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

public class CoordinatesTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void testStringConstructor_whenNull_throwException() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Please provide coordinates in the right format ('x y').");

        new Coordinates("asd");
    }

    @Test
    public void testStringConstructor_whenInvalidStringFormat_throwException() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Please provide coordinates in the right format ('x y').");

        new Coordinates("asd");
    }

    @Test
    public void testStringConstructor_whenInvalidInt_throwException() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Please provide coordinates in the following format 'x y' where x and y are valid numbers.");

        new Coordinates("-1 A");
    }

    @Test
    public void testStringConstructor_whenValidaString_returnsCorrectCoordinates() {
        Coordinates coord = new Coordinates("-1 2");

        assertEquals("Should map X coordinate", coord.getX(), -1);
        assertEquals("Should map Y coordinate", coord.getY(), 2);
    }

    @Test
    public void testToString_returnsRightFormat() {
        Coordinates coord = new Coordinates(5, 3);

        assertEquals("Should have correct format", coord.toString(), "5 3");
    }
}
