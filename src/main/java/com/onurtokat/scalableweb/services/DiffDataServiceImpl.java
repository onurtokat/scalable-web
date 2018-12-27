package com.onurtokat.scalableweb.services;

import com.onurtokat.scalableweb.domain.DiffData;
import com.onurtokat.scalableweb.repositories.DiffDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiffDataServiceImpl implements DiffDataService {

    private DiffDataRepository diffDataRepository;

    @Autowired
    public DiffDataServiceImpl(DiffDataRepository diffDataRepository) {
        this.diffDataRepository = diffDataRepository;
    }

    @Override
    public DiffData saveOrUpdate(DiffData diffData) {
        diffDataRepository.save(diffData);
        return diffData;
    }

    @Override
    public DiffData getById(String id) {
        return diffDataRepository.findById(id).orElse(null);
    }
}
