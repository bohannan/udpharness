package com.laurencebohannan.interveiw.udp.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by bohannan on 6/3/15.
 */
public class TestUtilities {

    @Test
    public void testGetNewRandomMovementNeg() {
        short result = Utilities.getNewRandomMovement(-1);
        assertTrue(-1 + result < 0);
    }
    @Test
    public void testGetNewRandomMovementPos() {
        short result = Utilities.getNewRandomMovement(1);
        assertTrue(1 + result > 0);
    }
    @Test
    public void testGetNewRandomMovementRange() {
        short result = Utilities.getNewRandomMovement(0);
        assertTrue(result<=1 && result>=-1);
    }
}
