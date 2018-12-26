package com.onurtokat.scalableweb.utils;

import java.util.Arrays;

/**
 * OffsetCalculator calculates line offset of the JSON string
 * JSON String data pretty is formatted with Google Gson.
 *
 * @author onurtokat
 */
public class OffsetCalculator {

    /**
     *getOffsetWithLength method provides offset diffs of the pair files
     *
     * @param left left posted data
     * @param right right posted data
     * @return int array which contains offset diffs
     */
    public static int[] getOffsetWithLength(String left, String right) {
        String[] splittedLeft = PrettyJsonFormatter.getPrettyFormat(left).split("\n");
        String[] splittedRight = PrettyJsonFormatter.getPrettyFormat(right).split("\n");
        int maxIndex = Math.max(splittedLeft.length, splittedRight.length);
        if (splittedLeft.length > splittedRight.length) {
            return isDifferentLength(maxIndex, splittedRight, splittedLeft);
        } else if (splittedRight.length > splittedLeft.length) {
            return isDifferentLength(maxIndex, splittedLeft, splittedRight);
        } else {
            return isDifferentLength(maxIndex, splittedRight, splittedLeft);
        }
    }

    /**
     *isDifferentLength method provides offset diffs of the pairs
     *
     * @param max diff array length
     * @param minPart minimum string sized data
     * @param maxPart maximum string sized data
     * @return offset diffs as array
     */
    public static int[] isDifferentLength(int max, String[] minPart, String[] maxPart) {
        int offset = -1;
        int[] result = new int[max];
        for (int i = 0; i < minPart.length; i++) {
            if (!maxPart[i].equals(minPart[i])) {
                offset = Arrays.toString(maxPart).indexOf(maxPart[i], offset + 1);
                result[i] = offset + maxPart[i].length();
            }
        }
        if (minPart.length == maxPart.length) {
            return result;
        }
        for (int i = minPart.length; i < maxPart.length; i++) {
            offset = Arrays.toString(maxPart).indexOf(maxPart[i], offset + 1);
            result[i] = offset + maxPart[i].length();
        }
        return result;
    }
}
