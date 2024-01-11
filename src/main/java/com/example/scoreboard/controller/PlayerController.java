package com.example.scoreboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.scoreboard.model.dto.PlayerDto;
import com.example.scoreboard.model.entity.Player;
import com.example.scoreboard.model.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /* Ajouter un player */
    @PostMapping("/add")
    public PlayerDto addPlayer(@RequestBody Player player) {
        // return playerService.save(player);
        return new PlayerDto(playerService.save(player));
    }

    /* Afficher les player */
    @GetMapping
    public Iterable<PlayerDto> getAllPlayers() {
        // return playerService.getAll();
        List<PlayerDto> playerDtos = new ArrayList<>();
        playerService.getAll().forEach(p -> playerDtos.add(new PlayerDto(p)));
        return playerDtos;
    }

    /* Afficher par Id */
    @GetMapping("/{id}")
    public PlayerDto getPlayerById(@PathVariable Long id) {
        // return playerService.getById(id).orElse(null);
        return new PlayerDto(playerService.getById(id).orElse(null));
    }

    /* Afficher par Nickname */
    @GetMapping("/name/{nickname}")
    public PlayerDto getMethodName(@PathVariable String nickname) {
        // return playerService.getByNickName(nickname).orElse(null);
        return new PlayerDto(playerService.getByNickName(nickname).orElse(null));
    }

    /* Modifier par Id */
    @PutMapping("/update/{id}")
    public PlayerDto updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> p = playerService.getById(id);

        if (p.isPresent()) {
            player.setId(id);
            // return playerService.save(player);
            return new PlayerDto(playerService.save(player));
        } else {
            return null;
        }
    }

    /* Supprimer par Id */
    @DeleteMapping("/player/delete/{id}")
    public Boolean deletePlayerById(@PathVariable long id) {
        Optional<Player> p = playerService.getById(id);

        if (p.isPresent()) {
            playerService.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    /* Methode supplementaires */

}
