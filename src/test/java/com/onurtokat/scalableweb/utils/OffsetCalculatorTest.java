package com.onurtokat.scalableweb.utils;


import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class OffsetCalculatorTest {

    private static final String LEFT_STRING = "[{\"userName\": \"onur\",\"age\":30},{\"userName\": \"ozan\",\"age\":2}]";
    private static final String RIGHT_STRING = "[{\"userName\": \"onur\",\"age\":35},{\"userName\": \"ozan\",\"age\":2}]";

    @Test
    public void getOffsetWithLengthTestWithSameFile() {
        int[] diff = OffsetCalculator.getOffsetWithLength(LEFT_STRING, LEFT_STRING);//Assume that same file
        assertNotNull(diff);//always not null
        for (int value : diff) {
            assertSame(0, value);//due to no diff, each line defined as 0
        }
    }

    @Test
    public void getOffsetWithLengthTestWithDifferentFile() {
        int[] diff = OffsetCalculator.getOffsetWithLength(LEFT_STRING, RIGHT_STRING);
        assertNotNull(diff);//always not null
        int counter = 0;
        for (int value : diff) {
            if (value != 0) {
                counter++;
            }
        }
        assertTrue(counter > 0);
    }

    @Test
    public void isDifferentLengthTest() {
        String[] prettyLeft = PrettyJsonFormatter.getPrettyFormat(LEFT_STRING).split("\n");
        String[] prettyRight = PrettyJsonFormatter.getPrettyFormat(RIGHT_STRING).split("\n");
        int maxIndex = Math.max(prettyLeft.length, prettyRight.length);
        if (prettyLeft.length > prettyRight.length) {
            assertNotNull(OffsetCalculator.isDifferentLength(maxIndex, prettyRight, prettyLeft));
            assertSame(OffsetCalculator.isDifferentLength(maxIndex, prettyRight, prettyLeft).length, maxIndex);
        } else if (prettyRight.length > prettyLeft.length) {
            assertNotNull(OffsetCalculator.isDifferentLength(maxIndex, prettyLeft, prettyRight));
            assertSame(OffsetCalculator.isDifferentLength(maxIndex, prettyLeft, prettyRight).length, maxIndex);
        } else {
            assertNotNull(OffsetCalculator.isDifferentLength(maxIndex, prettyRight, prettyLeft));
            assertSame(OffsetCalculator.isDifferentLength(maxIndex, prettyRight, prettyLeft).length, maxIndex);
        }
    }
}