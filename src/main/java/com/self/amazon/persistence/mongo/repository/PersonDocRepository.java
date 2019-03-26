package com.self.amazon.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.self.amazon.persistence.mongo.entity.PersonDoc;

@Repository
public interface PersonDocRepository extends MongoRepository<PersonDoc, String>{

}
