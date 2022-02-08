package com.ftn.udd.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ftn.udd.dto.JobApplicationDTO;
import com.ftn.udd.elasticsearch.dto.SearchParamDTO;
import com.ftn.udd.elasticsearch.model.IndexingUnit;

public interface SearchService {

	String index(JobApplicationDTO jobApplication) throws Exception;

	Page<IndexingUnit> advancedQuery(List<SearchParamDTO> searchParams, int page, int size);

	Page<IndexingUnit> simpleQuery(String searchValue, int page, int size);

	List<IndexingUnit> geoSearch(String place, int radius) throws Exception;

}
