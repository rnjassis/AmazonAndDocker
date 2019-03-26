package com.self.amazon.persistence.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.amazon.persistence.sql.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
