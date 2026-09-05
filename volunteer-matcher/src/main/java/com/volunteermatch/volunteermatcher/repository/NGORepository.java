package com.volunteermatch.volunteermatcher.repository;

import com.volunteermatch.volunteermatcher.model.NGO;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NGORepository extends Neo4jRepository<NGO, String> {
}