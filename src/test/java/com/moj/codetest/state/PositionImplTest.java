package com.moj.codetest.state;


import com.moj.codetest.model.Coordinates;
import com.moj.codetest.model.MapLimits;
import com.moj.codetest.state.Position;
import com.moj.codetest.state.PositionImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PositionImplTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private static final int START_X = 0;
    private static final int START_Y = 0;

    @Mock
    private MapLimits map;

    private Position position;
    private Coordinates startCoordinates;

    @Before
    public void setup() {
        startCoordinates = new Coordinates(START_X, START_Y);
        instanciatePosition();
    }

    @Test
    public void testConstructor_whenValidInputs_success() {
        isInLimits();

        Position position = new PositionImpl(map, startCoordinates);

        assertTrue("Returns instance of PositionImpl", position instanceof PositionImpl);
    }

    @Test
    public void testConstructor_whenNullMapLimits_throwException() {
        expected.expect(NullPointerException.class);
        expected.expectMessage("Please provide map limits");

        new PositionImpl(null, startCoordinates);
    }

    @Test
    public void testConstructor_whenNullCoordinates_throwException() {
        expected.expect(NullPointerException.class);
        expected.expectMessage("Please provide coordinates");

        new PositionImpl(map, null);
    }

    @Test
    public void testConstructor_callsIsInLimits() {
        isInLimits();
        Coordinates coords = new Coordinates(1,1);
        new PositionImpl(map, coords);

        verify(map).isInLimits(eq(coords));
    }

    @Test
    public void testConstructor_whenCoordinatesOutOfLimits_throwException() {
        isNotInLimits();
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid start position.");

        new PositionImpl(map, startCoordinates);
    }

    @Test
    public void testIncreaseY_whenValidMove_thenIncreasesYInOneUnit() {
        isInLimits();
        position = new PositionImpl(map, startCoordinates);

        position.increaseY();

        assertEquals("Increases y", position.getCoordinates().getY(), START_Y + 1);
        assertEquals("Maintains x", position.getCoordinates().getX(), START_X);
    }

    @Test
    public void testIncreaseY_whenMovingOutOfLimits_throwException() {
        isNotInLimits();
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Can't go out of limits!");

        position.increaseY();

        assertEquals("Maintains y", position.getCoordinates().getY(), START_Y);
        assertEquals("Maintains x", position.getCoordinates().getX(), START_X);
    }

    @Test
    public void testIncreaseX_whenValidMove_thenIncreasesXInOneUnit() {
        isInLimits();
        position = new PositionImpl(map, startCoordinates);

        position.increaseX();

        assertEquals("Maintains y", position.getCoordinates().getY(), START_Y);
        assertEquals("Increases x", position.getCoordinates().getX(), START_X + 1);
    }

    @Test
    public void testIncreaseX_whenMovingOutOfLimits_throwException() {
        isNotInLimits();
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Can't go out of limits!");

        position.increaseX();

        assertEquals("Maintains y", position.getCoordinates().getY(), START_Y);
        assertEquals("Maintains x", position.getCoordinates().getX(), START_X);
    }

    @Test
    public void testDecreaseY_whenValidMove_thenDecreasesYInOneUnit() {
        isInLimits();
        position = new PositionImpl(map, startCoordinates);

        position.decreaseY();

        assertEquals("Decreases y", position.getCoordinates().getY(), START_Y - 1);
        assertEquals("Maintains x", position.getCoordinates().getX(), START_X);
    }

    @Test
    public void testDecreaseY_whenMovingOutOfLimits_throwException() {
        isNotInLimits();
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Can't go out of limits!");

        position.decreaseY();

        assertEquals("Maintains y", position.getCoordinates().getY(), START_Y);
        assertEquals("Maintains x", position.getCoordinates().getX(), START_X);
    }

    @Test
    public void testDecreaseX_whenValidMove_thenDecreasesXInOneUnit() {
        isInLimits();
        position = new PositionImpl(map, startCoordinates);

        position.decreaseX();

        assertEquals("Maintains y", position.getCoordinates().getY(), START_Y);
        assertEquals("Decreases x", position.getCoordinates().getX(), START_X - 1);
    }

    @Test
    public void testDecreaseX_whenMovingOutOfLimits_throwException() {
        isNotInLimits();
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Can't go out of limits!");

        position.decreaseX();

        assertEquals("Maintains y", position.getCoordinates().getY(), START_Y);
        assertEquals("Maintains x", position.getCoordinates().getX(), START_X);
    }

    private void isInLimits() {
        when(map.isInLimits(any(Coordinates.class))).thenReturn(true);
    }

    private void isNotInLimits() {
        when(map.isInLimits(any(Coordinates.class))).thenReturn(false);
    }

    private void instanciatePosition() {
        when(map.isInLimits(eq(startCoordinates))).thenReturn(true);
        position = new PositionImpl(map, startCoordinates);
    }
}
