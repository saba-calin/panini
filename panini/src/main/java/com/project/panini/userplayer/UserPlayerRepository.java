package com.project.panini.userplayer;

import com.project.panini.player.PlayerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlayerRepository extends JpaRepository<UserPlayer, Long> {

    @Query(value = """
        SELECT new com.project.panini.player.PlayerDto(p.id, p.name, p.shirtNumber, p.photo)
        FROM user_player up INNER JOIN players p ON up.player.id = p.id
        WHERE up.user.id = :userId
        GROUP BY p.id, p.name, p.shirtNumber, p.photo
        HAVING COUNT(p.id) > 1
        ORDER BY p.id ASC
    """)
    List<PlayerDto> getDoubles(@Param("userId") Long userId);
}
