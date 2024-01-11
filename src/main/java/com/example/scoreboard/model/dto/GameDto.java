package com.example.scoreboard.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.scoreboard.model.entity.Contest;
import com.example.scoreboard.model.entity.Game;

import lombok.Data;

@Data
public class GameDto {

    private Long id;
    private String title;
    private Integer minPlayers;
    private Integer maxPlayers;
    private List<Long> contests;

    public GameDto(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.minPlayers = game.getMinPlayers();
        this.maxPlayers = game.getMaxPlayers();
        List<Long> contest_id = new ArrayList<Long>();

        if (game.getContests() != null) {
            for (Contest contest : game.getContests()) {
                contest_id.add(contest.getId());
            }
            this.contests = contest_id;
        }
    }
}
