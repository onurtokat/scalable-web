package com.onurtokat.scalableweb.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonDecoderTest {

    private static final String TEST_DECODED =
            "W3sidXNlck5hbWUiOiAib251ciIsImFnZSI6MzB9LHsidXNlck5hbWUiOiAib3phbiIsImFnZSI6Mn1d";
    private static final String TEST_ENCODED =
            "[{\"userName\": \"onur\",\"age\":30},{\"userName\": \"ozan\",\"age\":2}]";

    @Test
    public void decode() {
        assertEquals(TEST_ENCODED, JsonDecoder.decode(TEST_DECODED));
    }
}