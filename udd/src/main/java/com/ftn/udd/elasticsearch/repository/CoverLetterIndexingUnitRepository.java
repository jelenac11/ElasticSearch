package com.ftn.udd.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ftn.udd.elasticsearch.model.CoverLetterIndexingUnit;

public interface CoverLetterIndexingUnitRepository extends ElasticsearchRepository<CoverLetterIndexingUnit, Long> {

}
