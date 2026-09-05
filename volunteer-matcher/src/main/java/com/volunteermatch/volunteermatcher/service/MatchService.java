package com.volunteermatch.volunteermatcher.service;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    private final Neo4jClient neo4jClient;

    public MatchService(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    // Find NGOs that need a skill this volunteer has, in the same location,
    // ranked by how many matching skills they share (multi-hop + skill overlap)
    public List<Map<String, Object>> findMatchesForVolunteer(String volunteerId) {
        String cypher = """
            MATCH (v:Volunteer {id: $volunteerId})-[:HAS_SKILL]->(s:Skill)<-[:NEEDS_SKILL]-(n:NGO)
            WHERE n.location = v.location
            WITH n, collect(s.name) AS matchedSkills, count(s) AS skillMatchCount
            RETURN n.id AS ngoId, n.name AS ngoName, n.location AS location,
                   n.causeType AS causeType, matchedSkills, skillMatchCount
            ORDER BY skillMatchCount DESC
            """;

        return neo4jClient.query(cypher)
                .bind(volunteerId).to("volunteerId")
                .fetch()
                .all()
                .stream()
                .toList();
    }

    // Get all volunteers (for the dropdown/list on the matches page)
    public List<Map<String, Object>> getAllVolunteers() {
        String cypher = """
            MATCH (v:Volunteer)
            RETURN v.id AS id, v.name AS name, v.location AS location
            ORDER BY v.name
            """;
        return neo4jClient.query(cypher).fetch().all().stream().toList();
    }
}