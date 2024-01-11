package com.example.scoreboard.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.scoreboard.model.entity.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    /*
     * Select * from player where nickname = "nickname"
     */
    public Optional<Player> findByNickname(String nickname);

    /*
     * Recuperer tous les joueur dont l'email contient un mot passé en argument
     */

    // public Optional<Player> findByEmailHave(String word);
}
