package com.onurtokat.scalableweb.controllers;

import com.google.gson.Gson;
import com.onurtokat.scalableweb.exceptions.DataNotFoundException;
import com.onurtokat.scalableweb.exceptions.PostRequestException;
import com.onurtokat.scalableweb.utils.JsonDecoder;
import com.onurtokat.scalableweb.utils.OffsetCalculator;
import com.onurtokat.scalableweb.exceptions.MatchedDataDoesNotFoundException;
import com.onurtokat.scalableweb.domain.DiffData;
import com.onurtokat.scalableweb.model.JsonResultModel;
import com.onurtokat.scalableweb.services.DiffDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AppController provides POST and GET endpoints to compare encoded
 * data. Two endpoints posting JSON Base64 encoded data to storing
 * layer.
 *
 * One endpoint checks the two data which stored in MongoDB according to
 * id. Due to Stored data format is raw, it is decoded firstly. Decoded
 * data is formatted as pretty JSON string. Two pretty JSON strings is
 * evaluated for comparison. If difference exists, offsets which contain
 * difference are returned.
 *
 *
 * @author onurtokat
 */
@RestController
@RequestMapping("v1/diff")
public class AppController {

    private final static String SIDE_LEFT = "left";
    private final static String SIDE_RIGHT = "right";
    private DiffDataService diffDataService;

    @Autowired
    public void setProductService(DiffDataService diffDataService) {
        this.diffDataService = diffDataService;
    }

    /**
     * postLeft method achieve file id from URL, and data from
     * content
     *
     * @param id file id for matching
     * @param data POST content which is encoded JSON
     * @return if posting success, returns string. But if exception occurs, throws exception
     */
    @PostMapping("{id}/left")
    public ResponseEntity<String> postLeft(@PathVariable String id, @RequestBody String data) {
        try {
            saveData(id, SIDE_LEFT, data);
            return ResponseEntity.ok("Left data posted");
        } catch (Exception e) {
            throw new PostRequestException("Error occured when posting Left data", e);
        }
    }

    /**
     * postRight method achieve file id from URL, and data from
     * content
     *
     * @param id file id for matching
     * @param data POST content which is encoded JSON
     * @return if posting success, returns string. But if exception occurs, throws exception
     */
    @PostMapping("{id}/right")
    public ResponseEntity<String> postRight(@PathVariable String id, @RequestBody String data) {
        try {
            saveData(id, SIDE_RIGHT, data);
            return ResponseEntity.ok("Right data posted");
        } catch (Exception e) {
            throw new PostRequestException("Error occured when posting right data", e);
        }
    }

    /**
     * getDiff method compares two data content with same id
     *
     * @param id file id for using matching
     * @return comparing result as JSON format
     */
    @GetMapping("{id}")
    public String getDiff(@PathVariable String id) {
        DiffData diffData = diffDataService.getById(id);

        //if no data post before getting result, null pointer checking
        if (diffData == null) {
            throw new DataNotFoundException(" There is no data for comparison");
        }

        //if only pair exists
        if (diffData.getData().get(SIDE_LEFT) == null) {
            throw new MatchedDataDoesNotFoundException(SIDE_LEFT + " does not exist for id: " + id);
        } else if (diffData.getData().get(SIDE_RIGHT) == null) {
            throw new MatchedDataDoesNotFoundException(SIDE_RIGHT + " does not exist for id: " + id);
        } else {
            String decodeLeft = JsonDecoder.decode(diffData.getData().get(SIDE_LEFT));
            String decodeRight = JsonDecoder.decode(diffData.getData().get(SIDE_RIGHT));
            long sizeLeft = JsonDecoder.decode(diffData.getData().get(SIDE_LEFT)).length();
            long sizeRight = JsonDecoder.decode(diffData.getData().get(SIDE_RIGHT)).length();
            if (decodeLeft.equals(decodeRight)) {
                return new Gson().toJson(new JsonResultModel(true, true, null));
            } else if (!decodeLeft.equals(decodeRight) && (sizeLeft == sizeRight)) {
                return new Gson().toJson(new JsonResultModel(false, true,
                        OffsetCalculator.getOffsetWithLength(decodeLeft, decodeRight)));
            } else {
                return new Gson().toJson(new JsonResultModel(false, false, null));
            }
        }
    }

    /**
     * saveData method storing POST data contents to MongoDB
     *
     * @param id   file id for using matching
     * @param side side of the endpoint
     * @param data POST content of the endpoint
     */
    private void saveData(String id, String side, String data) {
        DiffData tmpData = diffDataService.getById(id);
        if (tmpData != null) {
            tmpData.getData().put(side, data);
            diffDataService.saveOrUpdate(tmpData);
        } else {
            Map<String, String> mapData = new ConcurrentHashMap<>();
            mapData.put(side, data);
            diffDataService.saveOrUpdate(new DiffData(id, mapData));
        }
    }
}
