package com.example.scoreboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.scoreboard.model.dto.GameDto;
import com.example.scoreboard.model.entity.Game;
import com.example.scoreboard.model.service.GameService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public Iterable<GameDto> getAllGames() {
        // return gameService.getAll();
        List<GameDto> gto = new ArrayList<>();
        gameService.getAll().forEach(g -> gto.add(new GameDto(g)));
        return gto;
    }

    @GetMapping("/{id}")
    public GameDto getGameById(@PathVariable Long id) {
        Game game = gameService.get(id).orElse(null);
        if (game != null) {
            return new GameDto(game);
        }
        // return gameService.get(id).orElse(null);
        return null;
    }

    @PostMapping
    public GameDto addGame(@RequestBody Game game) {

        /*
         * Request body va récuperer les données dans le corpss
         * de la requete et les transformer en objet game
         */

        // return gameService.save(game);
        return new GameDto(gameService.save(game));
    }

    @DeleteMapping("/game/{id}")
    public boolean deleteGame(@PathVariable Long id) {
        Optional<Game> g = gameService.get(id);
        if (g.isPresent()) {
            gameService.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/game/{id}")
    public GameDto updateGame(@PathVariable long id, @RequestBody Game g) {
        Optional<Game> option = gameService.get(id);
        if (option.isPresent()) {
            g.setId(id);
            // return gameService.save(g);
            return new GameDto(gameService.save(g));
        } else {
            return new GameDto(g);
        }
    }

}
