package com.project.panini.coach;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
@AllArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;

    public Coach getCoachById(long id) {
        return this.coachRepository.findById(id).orElseThrow();
    }
}
