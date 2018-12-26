package com.onurtokat.scalableweb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * DiffData MongoDB Document object
 *
 * @author onurtokat
 */
@Document
public class DiffData {

    @Id
    private String id;

    private Map<String, String> data;

    /**
     * Constructor
     *
     * @param id file id for using matching
     * @param data POST content of the endpoint
     */
    public DiffData(String id, Map<String, String> data) {
        this.id = id;
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public String getId() {
        return id;
    }
}
