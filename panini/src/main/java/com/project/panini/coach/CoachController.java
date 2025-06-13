package com.project.panini.coach;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Coach> getCoachById(@RequestParam("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.coachService.getCoachById(id));
    }
}
