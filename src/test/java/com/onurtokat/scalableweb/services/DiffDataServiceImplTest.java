package com.onurtokat.scalableweb.services;

import com.onurtokat.scalableweb.domain.DiffData;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiffDataServiceImplTest {

    private final static String SIDE_LEFT = "left";
    private final static String SIDE_RIGHT = "right";
    private DiffDataService diffDataService;
    private DiffData diffData;

    @Before
    public void setUp() {
        diffDataService = mock(DiffDataService.class);
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(SIDE_LEFT, "X");
        dataMap.put(SIDE_RIGHT, "Y");
        diffData = new DiffData("1", dataMap);
        when(diffDataService.getById("1")).thenReturn(diffData);
    }

    @Test
    public void getByIdTest() {
        assertNotNull(diffDataService.getById("1"));
        assertEquals("1",diffDataService.getById("1").getId());
        assertEquals(2,diffDataService.getById("1").getData().size());
    }
}