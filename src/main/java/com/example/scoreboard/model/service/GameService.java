package com.example.scoreboard.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scoreboard.model.entity.Game;
import com.example.scoreboard.model.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    /*
     * L'annotation Autowired permet de lier automatiquement une propriete a un type
     * par l'injection de dependance.
     * Si on utilise pas l'annotation, l'injection de dépendance se fait dans un
     * constructor
     * 
     * public GameService(GameRepository gr){
     * this.gameRepository =gr;
     * 
     * }
     */

    /** Recuperer tous les jeux */

    public Iterable<Game> getAll() {

        return this.gameRepository.findAll();
    }

    /* Get bu id */

    public Optional<Game> get(Long id) {
        return gameRepository.findById(id);
    }

    /* Save : Ajout + modif */

    public Game save(Game g) {
        return this.gameRepository.save(g);
    }

    /** delete ById */

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

    public void delete(Game g) {
        gameRepository.delete(g);
    }

}
