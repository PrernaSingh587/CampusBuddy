package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.TestTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository1 extends MongoRepository<TestTable, Integer> {
}
