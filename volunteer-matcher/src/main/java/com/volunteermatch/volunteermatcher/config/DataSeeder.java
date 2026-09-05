package com.volunteermatch.volunteermatcher.config;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataSeeder implements CommandLineRunner {

    private final Neo4jClient neo4jClient;

    public DataSeeder(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public void run(String... args) {
        Long count = neo4jClient.query("MATCH (v:Volunteer) RETURN count(v) AS c")
                .fetchAs(Long.class)
                .one()
                .orElse(0L);

        if (count > 0) {
            System.out.println("✅ Data already seeded, skipping.");
            return;
        }

        System.out.println("🌱 Seeding sample data...");

        neo4jClient.query("""
            CREATE (t:Skill {id: randomUUID(), name: 'Teaching'})
            CREATE (fa:Skill {id: randomUUID(), name: 'First Aid'})
            CREATE (ck:Skill {id: randomUUID(), name: 'Cooking'})
            CREATE (fr:Skill {id: randomUUID(), name: 'Fundraising'})
            CREATE (wd:Skill {id: randomUUID(), name: 'Web Development'})
            CREATE (co:Skill {id: randomUUID(), name: 'Counseling'})
            CREATE (ep:Skill {id: randomUUID(), name: 'Event Planning'})
            CREATE (ph:Skill {id: randomUUID(), name: 'Photography'})

            CREATE (hope:NGO {id: randomUUID(), name: 'Hope Foundation', location: 'Hyderabad', causeType: 'Education'})
            CREATE (green:NGO {id: randomUUID(), name: 'Green Earth Trust', location: 'Hyderabad', causeType: 'Environment'})
            CREATE (care:NGO {id: randomUUID(), name: 'Care Health Initiative', location: 'Bangalore', causeType: 'Health'})
            CREATE (tech:NGO {id: randomUUID(), name: 'Tech For Good', location: 'Hyderabad', causeType: 'Technology'})

            CREATE (hope)-[:NEEDS_SKILL]->(t)
            CREATE (hope)-[:NEEDS_SKILL]->(ep)
            CREATE (green)-[:NEEDS_SKILL]->(fr)
            CREATE (green)-[:NEEDS_SKILL]->(ph)
            CREATE (care)-[:NEEDS_SKILL]->(fa)
            CREATE (care)-[:NEEDS_SKILL]->(co)
            CREATE (tech)-[:NEEDS_SKILL]->(wd)
            CREATE (tech)-[:NEEDS_SKILL]->(t)

            CREATE (priya:Volunteer {id: randomUUID(), name: 'Priya Sharma', location: 'Hyderabad'})
            CREATE (rahul:Volunteer {id: randomUUID(), name: 'Rahul Verma', location: 'Hyderabad'})
            CREATE (anita:Volunteer {id: randomUUID(), name: 'Anita Desai', location: 'Bangalore'})
            CREATE (karan:Volunteer {id: randomUUID(), name: 'Karan Mehta', location: 'Hyderabad'})
            CREATE (sneha:Volunteer {id: randomUUID(), name: 'Sneha Iyer', location: 'Hyderabad'})

            CREATE (priya)-[:HAS_SKILL]->(t)
            CREATE (priya)-[:HAS_SKILL]->(fa)
            CREATE (rahul)-[:HAS_SKILL]->(wd)
            CREATE (rahul)-[:HAS_SKILL]->(ph)
            CREATE (anita)-[:HAS_SKILL]->(co)
            CREATE (anita)-[:HAS_SKILL]->(fa)
            CREATE (karan)-[:HAS_SKILL]->(fr)
            CREATE (karan)-[:HAS_SKILL]->(ep)
            CREATE (sneha)-[:HAS_SKILL]->(t)
            CREATE (sneha)-[:HAS_SKILL]->(wd)
        """).run();

        System.out.println("✅ Seed data created successfully!");
    }
}