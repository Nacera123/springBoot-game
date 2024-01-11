package com.example.scoreboard.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.scoreboard.model.entity.Contest;

import lombok.Data;

@Data
public class ContestDto {

    private Long id;
    private Date startDate;
    private GameDto game;
    private PlayerDto winner;
    private List<PlayerDto> players = new ArrayList<PlayerDto>();

    public ContestDto(Contest contest) {
        id = contest.getId();
        startDate = contest.getStartDate();
        game = new GameDto(contest.getGame());
        if (winner != null) {
            winner = new PlayerDto(contest.getWinner());
        }
        contest.getPlayers().forEach(p -> players.add(new PlayerDto(p)));

    }

}
