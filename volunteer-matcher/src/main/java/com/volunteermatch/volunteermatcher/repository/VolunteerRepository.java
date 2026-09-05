package com.volunteermatch.volunteermatcher.repository;

import com.volunteermatch.volunteermatcher.model.Volunteer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface VolunteerRepository extends Neo4jRepository<Volunteer, String> {
}