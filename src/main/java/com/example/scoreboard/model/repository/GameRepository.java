package com.example.scoreboard.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.scoreboard.model.entity.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}
