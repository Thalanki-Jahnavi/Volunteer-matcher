package com.volunteermatch.volunteermatcher.controller;

import com.volunteermatch.volunteermatcher.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/matches")
    public String matches(@RequestParam(required = false) String volunteerId, Model model) {
        List<Map<String, Object>> volunteers = matchService.getAllVolunteers();
        model.addAttribute("volunteers", volunteers);

        if (volunteerId != null && !volunteerId.isEmpty()) {
            List<Map<String, Object>> results = matchService.findMatchesForVolunteer(volunteerId);
            model.addAttribute("results", results);
            model.addAttribute("selectedVolunteerId", volunteerId);
        }

        return "matches";
    }
}