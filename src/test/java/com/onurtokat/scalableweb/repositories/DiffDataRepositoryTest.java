package com.onurtokat.scalableweb.repositories;

import com.onurtokat.scalableweb.domain.DiffData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class DiffDataRepositoryTest {

    private final static String SIDE_LEFT = "left";
    private final static String SIDE_RIGHT = "right";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private DiffDataRepository diffDataRepository;

    @Before
    public void setUp() {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(SIDE_LEFT, "X");
        dataMap.put(SIDE_RIGHT, "Y");
        DiffData diffData = new DiffData("1", dataMap);
        diffDataRepository.save(diffData);
    }

    @Test
    public void saveTest() {
        assertNotNull(mongoTemplate.findById("1", DiffData.class));
        assertEquals("1",mongoTemplate.findById("1", DiffData.class).getId());
        assertNotNull(mongoTemplate.findById("1", DiffData.class).getData());
        assertEquals("X",mongoTemplate.findById("1", DiffData.class).getData().get(SIDE_LEFT));
        assertEquals("Y",mongoTemplate.findById("1", DiffData.class).getData().get(SIDE_RIGHT));
    }
}