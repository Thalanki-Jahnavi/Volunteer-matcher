package com.volunteermatch.volunteermatcher.model;

import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashSet;
import java.util.Set;

@Node
public class NGO {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;
    private String location;
    private String causeType;

    @Relationship(type = "NEEDS_SKILL", direction = Relationship.Direction.OUTGOING)
    private Set<Skill> neededSkills = new HashSet<>();

    public NGO() {}

    public NGO(String name, String location, String causeType) {
        this.name = name;
        this.location = location;
        this.causeType = causeType;
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

    public String getCauseType() {
        return causeType;
    }

    public Set<Skill> getNeededSkills() {
        return neededSkills;
    }

    public void addNeededSkill(Skill skill) {
        this.neededSkills.add(skill);
    }
}