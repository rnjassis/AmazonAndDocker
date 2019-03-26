package com.self.amazon.persistence.dynamodb.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.self.amazon.persistence.dynamodb.entity.DynamoTable;

@Repository
public interface DynamoTableRepository extends PagingAndSortingRepository<DynamoTable, String>{

	@EnableScan
	@EnableScanCount
	Page<DynamoTable> findAll(Pageable pageable);
}
