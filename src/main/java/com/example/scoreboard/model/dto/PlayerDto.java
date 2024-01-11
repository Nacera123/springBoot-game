package com.example.scoreboard.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.scoreboard.model.entity.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {
    private Long id;
    private String nickname;
    private String email;
    private List<Long> wins = new ArrayList<Long>();
    private List<Long> contests = new ArrayList<Long>();

    public PlayerDto(Player player) {
        id = player.getId();
        nickname = player.getNickname();
        email = player.getEmail();
        player.getWins().forEach(c -> wins.add(c.getId()));
        player.getContests().forEach(c -> contests.add(c.getId()));

    }

}
