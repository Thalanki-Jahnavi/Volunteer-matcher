package com.volunteermatch.volunteermatcher.model;

import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashSet;
import java.util.Set;

@Node
public class Volunteer {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;
    private String location;

    @Relationship(type = "HAS_SKILL", direction = Relationship.Direction.OUTGOING)
    private Set<Skill> skills = new HashSet<>();

    public Volunteer() {}

    public Volunteer(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
}