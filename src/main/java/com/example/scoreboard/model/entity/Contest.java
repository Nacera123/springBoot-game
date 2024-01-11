package com.example.scoreboard.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Game game;

    @ManyToOne
    private Player winner;

    @ManyToMany
    @JoinTable(name = "player_contest", joinColumns = { @JoinColumn(name = "contest_id") }, inverseJoinColumns = {
            @JoinColumn(name = "player_id") })
    private List<Player> players = new ArrayList<Player>();

}
