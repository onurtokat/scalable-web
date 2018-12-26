package com.onurtokat.scalableweb.services;

import com.onurtokat.scalableweb.domain.DiffData;

/**
 * DiffDataService
 *
 * @author onurtokat
 */
public interface DiffDataService {

    /**
     * getById gets DiffData with file id
     *
     * @param id file id
     * @return DiffData
     */
    DiffData getById(String id);

    /**
     *saveOrUpdate saves DiffData into MongoDB
     *
     * @param diffData to save DiffData
     * @return DiffData
     */
    DiffData saveOrUpdate(DiffData diffData);
}
