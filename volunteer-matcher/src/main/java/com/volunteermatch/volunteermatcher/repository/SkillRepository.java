package com.volunteermatch.volunteermatcher.repository;

import com.volunteermatch.volunteermatcher.model.Skill;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SkillRepository extends Neo4jRepository<Skill, String> {
}