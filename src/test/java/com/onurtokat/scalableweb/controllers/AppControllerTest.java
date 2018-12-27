package com.onurtokat.scalableweb.controllers;

import com.onurtokat.scalableweb.domain.DiffData;
import com.onurtokat.scalableweb.exceptions.DataNotFoundException;
import com.onurtokat.scalableweb.exceptions.MatchedDataDoesNotFoundException;
import com.onurtokat.scalableweb.services.DiffDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author onurtokat
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
public class AppControllerTest {

    private static final String LEFT_SIDE="left";
    private static final String RIGHT_SIDE="right";
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

    @Test
    public void getDiffTest() throws Exception {
        Map<String,String> mapData = new ConcurrentHashMap<>();
        mapData.put(LEFT_SIDE,"X");
        mapData.put(RIGHT_SIDE,"X");
        DiffData tmpDiffData = new DiffData("1",mapData);
        when(diffDataService.getById("1")).thenReturn(tmpDiffData);
        this.mockMvc.perform(get("/v1/diff/1"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("equals").value("true"));
    }

    @Test(expected = DataNotFoundException.class)
    public void getDiffTestWithDataNotFoundException(){
        try{
            this.mockMvc.perform(get("/v1/diff/1"));
        }catch (Exception e){
            throw new DataNotFoundException("There is no data for comparison");
        }
    }

    @Test(expected = MatchedDataDoesNotFoundException.class)
    public void getDiffTestWithMatchedDataDoesNotFoundException() {
        Map<String,String> mapData = new ConcurrentHashMap<>();
        mapData.put(LEFT_SIDE,"X");
        DiffData tmpDiffData = new DiffData("1",mapData);
        when(diffDataService.getById("1")).thenReturn(tmpDiffData);
        try{
            this.mockMvc.perform(get("/v1/diff/1"));
        }catch (Exception e){
            throw new MatchedDataDoesNotFoundException("One of the pairs does not exist");
        }
    }
}