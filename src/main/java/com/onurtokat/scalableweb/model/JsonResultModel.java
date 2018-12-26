package com.onurtokat.scalableweb.model;

import java.util.Arrays;

/**
 * JsonResultModel
 *
 * @author onurtokat
 */
public class JsonResultModel {

    private boolean equals;
    private boolean size;
    private int[] Diff;

    /**
     * Constructor
     *
     * @param equals when pair's content is equal then it is true, otherwise false
     * @param size when pair's content size is equal then it is true, otherwise false
     * @param diff when pair's content size is equal then it has offset lines
     */
    public JsonResultModel(boolean equals, boolean size, int[] diff) {
        this.equals = equals;
        this.size = size;
        Diff = diff;
    }

    @Override
    public String toString() {
        return "JsonResultModel{" +
                "equals=" + equals +
                ", size=" + size +
                ", Diff=" + Arrays.toString(Diff) +
                '}';
    }
}
