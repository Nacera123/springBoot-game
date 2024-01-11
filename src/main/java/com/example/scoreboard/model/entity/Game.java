package com.example.scoreboard.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "game")
@Data /* permet de ne pas ajouter explicitement les g(s)etters dans la classe */
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     * auto increment
     * la valer GenerationType.IDENTITY permet de preciser que c'est
     * le SGBD qui va gerer les valeurs des identifiants
     */
    private Long id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "min_players")
    private Integer minPlayers;

    @Column(name = "max_players")
    private Integer maxPlayers;

    @OneToMany(mappedBy = "game")
    private List<Contest> contests;

}
