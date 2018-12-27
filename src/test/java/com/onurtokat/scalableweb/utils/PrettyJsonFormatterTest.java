package com.onurtokat.scalableweb.utils;

import com.onurtokat.scalableweb.exceptions.PrettyJsonFormatException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author onurtokat
 */
public class PrettyJsonFormatterTest {

    private static final String jsonObject = "{\"balance\": 1000.21, \"num\":100, \"is_vip\":true, \"name\":\"foo\"}";
    private static final String jsonArray = "[{\"userName\": \"onur\",\"age\":30},{\"userName\": \"ozan\",\"age\":2}]";

    @Test
    public void getPrettyFormatTestWithJsonObject() {
        assertTrue(PrettyJsonFormatter.getPrettyFormat(jsonObject).startsWith("{"));
        assertFalse(PrettyJsonFormatter.getPrettyFormat(jsonObject).startsWith("["));
        assertTrue(PrettyJsonFormatter.getPrettyFormat(jsonObject).contains("\n"));
    }

    @Test
    public void getPrettyFormatTestWithJsonArray() {
        assertTrue(PrettyJsonFormatter.getPrettyFormat(jsonArray).startsWith("["));
        assertFalse(PrettyJsonFormatter.getPrettyFormat(jsonArray).startsWith("{"));
        assertTrue(PrettyJsonFormatter.getPrettyFormat(jsonArray).contains("\n"));
    }

    @Test(expected = PrettyJsonFormatException.class)
    public void exceptionTest() {
        String tmp = "test";
        PrettyJsonFormatter.getPrettyFormat(tmp);
    }
}