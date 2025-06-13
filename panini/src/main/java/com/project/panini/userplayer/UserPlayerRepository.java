package com.project.panini.userplayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlayerRepository extends JpaRepository<UserPlayer, Long> {
}
