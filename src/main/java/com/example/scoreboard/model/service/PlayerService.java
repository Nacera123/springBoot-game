package com.example.scoreboard.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scoreboard.model.entity.Player;
import com.example.scoreboard.model.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    /* Create- update */
    public Player save(Player p) {
        return playerRepository.save(p);
    }

    /* Afficher tout */

    public Iterable<Player> getAll() {
        return playerRepository.findAll();
    }

    /*
     * Methode 2 avec list
     * public List<Player> getAll() {
     * Iterable<Player> all = this.playerRepository.findAll();
     * List<Player> players = new ArrayList<Player>();
     * all.forEach(players::add);
     * return players;
     * 
     * }
     */

    /* recuperer l'Id */

    public Optional<Player> getById(Long id) {
        return playerRepository.findById(id);
    }

    /* Select by nickname */
    public Optional<Player> getByNickName(String nickname) {
        return playerRepository.findByNickname(nickname);
    }

    /* Supprimer par id */
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    /* Supprimer */
    public void delete(Player p) {
        playerRepository.delete(p);
    }

    /* Methode autre */
    /* recuperer */
}
