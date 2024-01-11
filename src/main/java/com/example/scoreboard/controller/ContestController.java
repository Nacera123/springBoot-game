package com.example.scoreboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.scoreboard.model.dto.ContestDto;
import com.example.scoreboard.model.dto.PlayerDto;
import com.example.scoreboard.model.entity.Contest;
import com.example.scoreboard.model.entity.Player;
import com.example.scoreboard.model.service.ContestService;
import com.example.scoreboard.model.service.GameService;
import com.example.scoreboard.model.service.PlayerService;

@RestController
@RequestMapping("/contest")
public class ContestController {
    /*
     * pathVariable c'est quand on ajoute {param}
     * requestParam : parametre qu'on va traiter
     * 
     */
    @Autowired
    private ContestService contestService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public Iterable<ContestDto> getAllcontests() {
        // Iterable<Contest> contests = contestService.getAll();
        List<ContestDto> contestDtos = new ArrayList<>();
        contestService.getAll().forEach(contest -> contestDtos.add(new ContestDto(contest)));
        return contestDtos;
    }

    @GetMapping("/{id}")
    public ContestDto getcontestById(@PathVariable Long id) {

        return new ContestDto(contestService.get(id).orElse(null));
        // return new ContestDto(contestService.get(id));
    }

    @PostMapping
    public ContestDto addcontest(@RequestBody Contest contest) {

        return new ContestDto(contestService.save(contest));
    }

    @PostMapping("/add")
    public ContestDto addcontest(@RequestParam("start_date") String startDate, @RequestParam Long game_id,
            @RequestParam Optional<Long> winner_id) {

        Contest contest = new Contest();
        contest.setStartDate(Date.valueOf(startDate));
        contest.setGame(gameService.get(game_id).orElse(null));
        if (winner_id.isPresent()) {
            contest.setWinner(playerService.getById(winner_id.get()).get());
        }

        return new ContestDto(contestService.save(contest));

    }

    @DeleteMapping("/{id}")
    public boolean deletecontest(@PathVariable Long id) {
        Optional<Contest> g = contestService.get(id);
        if (g.isPresent()) {
            contestService.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/contest/{id}")
    public ContestDto updatecontest(@PathVariable long id, @RequestBody Contest g) {
        Optional<Contest> option = contestService.get(id);
        if (option.isPresent()) {
            g.setId(id);
            return new ContestDto(contestService.save(g));
        } else {
            return null;
        }
    }

    @PutMapping("{id}/add-player/{player_id}")
    public ContestDto addPlayer(@PathVariable long id, @PathVariable long player_id) {
        Contest contest = contestService.get(id).orElse(null);

        if (contest != null) {
            Player player = playerService.getById(player_id).orElse(null);
            if (player != null) {
                /* ici on crée un objet qui va récuperer le max player dans game */
                int maxPlayerAllowed = contest.getGame().getMaxPlayers();
                /* Ici on crée un objet qu'on va comparer au max player */
                int maxPlayerinContest = contest.getPlayers().size();

                if (maxPlayerinContest < maxPlayerAllowed) {

                    if (!contest.getPlayers().contains(player)) {
                        contest.getPlayers().add(player);
                    }
                    throw new ResponseStatusException(431, "le joueur est déja inscrit dans la partie",
                            new Throwable());

                }
                throw new ResponseStatusException(430, "nb de joueur max atteint", new Throwable());
            }
        }

        return new ContestDto(contestService.save(contest));
    }

    @PutMapping("/{id}/set-players")
    public ContestDto addPlayersToContest(@PathVariable long id, @RequestBody List<Long> playerIds) {
        Contest contest = contestService.get(id).orElse(null);

        if (contest != null) {
            int minPlayersAllowed = contest.getGame().getMinPlayers();
            int maxPlayerAllowed = contest.getGame().getMaxPlayers();
            int countPlayers = playerIds.size();
            if (minPlayersAllowed >= countPlayers && maxPlayerAllowed <= countPlayers) {

                List<Player> playerList = new ArrayList<Player>();
                for (Long idp : playerIds) {
                    Player p = playerService.getById(idp).orElse(null);
                    if (p != null) {

                        // playerList.add(playerService.getById(idp).get());
                        playerList.add(p);
                    } else {

                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le joueur dans l'identifiant est "
                                + playerIds + "n'existe pas ou est deja present dans la liste");
                    }
                }
                contest.setPlayers(playerList);
                return new ContestDto(contestService.save(contest));
            }
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT,
                    "Le nombre de joueur doit etre compris entre " + minPlayersAllowed + " " + minPlayersAllowed);

            // contest.getPlayers().clear();
            // for (Long playerId : playerIds) {
            // Player player = playerService.getById(playerId).orElse(null);
            // if (player != null) {
            // contest.getPlayers().add(player);
            // }

            // }
            // return contestService.save(contest);
        }
        throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT,
                "je ne fais pas le café");

    }
    // EXERCICES : 1. ajouter une route pour enlever un joueur déjà inscrit à une
    // partie.
    // Si le joueur n'était pas inscrit pour cette partie, renvoyer un message
    // d'erreur (en plus des messages, si
    // la partie n'existe pas ou si le joueur n'existe pas)

    // 2. Ajouter une route qui retourne les participants d'une partie (l'id de la
    // partie sera dans le chemin de la route)
    // @PutMapping("{idcontest}/delete/{id}")
    // public ContestDto deletePlayer(@PathVariable long id){
    // Contest contest = contestService.get(id).orElse(null);
    // contest.getPlayers().remove(pla)
    // }

    @PutMapping("/{id}/set-player/{id_player}")
    public ContestDto putWinner(@PathVariable Long id, @PathVariable("id_player") long idPlayer) {
        Contest contest = contestService.get(id).orElse(null);
        if (contest == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cette partie n'existe pas");
        Player player = playerService.getById(idPlayer).orElse(null);
        if (player == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ce joueur n'existe pas");
        if (contest.getPlayers().contains(player)) {
            contest.setWinner(player);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ce joueur n'est pas dans la liste des participants");
        }
        return new ContestDto(contestService.save(contest));
    }

}
