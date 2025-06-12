package com.project.panini.coach;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coach")
@AllArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @GetMapping
    public Coach getCoachById(@RequestParam("id") long id) {
        return this.coachService.getCoachById(id);
    }
}
