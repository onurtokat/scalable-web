package com.onurtokat.scalableweb.repositories;

import com.onurtokat.scalableweb.domain.DiffData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * DiffDataRepository
 *
 * @author onurtokat
 */
public interface DiffDataRepository extends MongoRepository<DiffData, String> {
}
