package com.project.panini.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM players p WHERE p.team.id = :teamId ORDER BY p.id ASC")
    List<Player> findPlayersByTeamId(@Param("teamId") Long id);
}
