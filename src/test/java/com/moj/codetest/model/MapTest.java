package com.moj.codetest.model;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MapTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private Map map;

    @Before
    public void setup() {
        map = new Map.MapBuilder()
                .withTopRightCoordinates("5 5")
                .build();
    }

    @Test
    public void testIsInLimits_whenInLimits_returnsTrue() {
        Coordinates coordinates = new Coordinates(3, 3);

        assertTrue("Should be in limits", map.isInLimits(coordinates));
    }

    @Test
    public void testIsInLimits_whenOverX_returnsFalse() {
        Coordinates coordinates = new Coordinates(6, 3);

        assertFalse("Should be off limits", map.isInLimits(coordinates));
    }

    @Test
    public void testIsInLimits_whenUnderX_returnsFalse() {
        Coordinates coordinates = new Coordinates(-1, 3);

        assertFalse("Should be off limits", map.isInLimits(coordinates));
    }

    @Test
    public void testIsInLimits_whenOverY_returnsFalse() {
        Coordinates coordinates = new Coordinates(3, 6);

        assertFalse("Should be off limits", map.isInLimits(coordinates));
    }

    @Test
    public void testIsInLimits_whenUnderY_returnsFalse() {
        Coordinates coordinates = new Coordinates(3, -1);

        assertFalse("Should be off limits", map.isInLimits(coordinates));
    }

    @Test
    public void testIsInLimits_whenUnderYAndUnderX_returnsFalse() {
        Coordinates coordinates = new Coordinates(-1, -1);

        assertFalse("Should be off limits", map.isInLimits(coordinates));
    }

    @Test
    public void testIsInLimits_whenOverYAndOverX_returnsFalse() {
        Coordinates coordinates = new Coordinates(6, 6);

        assertFalse("Should be off limits", map.isInLimits(coordinates));
    }

    @Test
    public void testBuilder_whenValidCoordinates_success() {

        new Map.MapBuilder()
                .withTopRightCoordinates("5 5")
                .build();
    }

    @Test
    public void testBuilder_whenNoCoordinates_throwException() {
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Please provide top right coordinates.");

        new Map.MapBuilder()
                .build();
    }

    @Test
    public void testBuilder_whenXLessThan0_throwException() {
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Please provide valid top right coordinates of the map (x > 0 && y > 0).");

        new Map.MapBuilder()
                .withTopRightCoordinates("-1 3")
                .build();
    }

    @Test
    public void testBuilder_whenYLessThan0_throwException() {
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Please provide valid top right coordinates of the map (x > 0 && y > 0).");

        new Map.MapBuilder()
                .withTopRightCoordinates("1 -3")
                .build();
    }

    @Test
    public void testBuilder_whenXEquals0_throwException() {
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Please provide valid top right coordinates of the map (x > 0 && y > 0).");

        new Map.MapBuilder()
                .withTopRightCoordinates("0 3")
                .build();
    }

    @Test
    public void testBuilder_whenYEquals0_throwException() {
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Please provide valid top right coordinates of the map (x > 0 && y > 0).");

        new Map.MapBuilder()
                .withTopRightCoordinates("1 0")
                .build();
    }
}
