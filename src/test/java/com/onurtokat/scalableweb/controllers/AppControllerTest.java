package com.onurtokat.scalableweb.controllers;

import com.onurtokat.scalableweb.services.DiffDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
public class AppControllerTest {

    private static final String LEFT_DATA =
            "WwogIHsKICAgICJ1c2VyTmFtZSI6ICJzYW5kZWVwIiwKICAgICJhZ2UiOiAzMAogIH0sCiAgewogICAgInVzZXJOYW1lI" +
                    "jogInZpdmFuIiwKICAgICJhZ2UiOiA1CiAgfQpd";
    private static final String RIGHT_DATA =
            "WwogIHsKICAgICJ1c2VyTmFtZSI6ICJzYW5kYWVwIiwKICAgICJhZ2UiOiAzMAogIH0sCiAgewogICAgInVzZXJOYW1lI" +
                    "jogInZpdmFuIiwKICAgICJhZ2UiOiA1CiAgfQpd";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiffDataService diffDataService;

    @Test
    public void postLeftTest() throws Exception {
        mockMvc.perform(post("/v1/diff/1/right").content(RIGHT_DATA))
                .andDo(print()).andExpect(status().isOk());
        verify(diffDataService, times(1)).getById("1");
    }

    @Test
    public void postRightTest() throws Exception {
        mockMvc.perform(post("/v1/diff/1/left").content(LEFT_DATA))
                .andDo(print()).andExpect(status().isOk());
        verify(diffDataService, times(1)).getById("1");
    }
}