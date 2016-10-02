package vc.rux.codingtest.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.service.GameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class StatisticsController {

    private final GameService gameService;

    @Autowired
    public StatisticsController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/management/statistics")
    public Statistics statistics(){
        Statistics statistics = new Statistics(gameService.allGames());
        return statistics;
    }

    /**
     * DTO specification for statistics
     */
    static class Statistics {
        public HashMap<String, List<Game>> games = new HashMap<String, List<Game>>();

        public Statistics(Iterable<Game> gamesList) {
            for (Game game : gamesList) {
                if (!games.containsKey(game.getSessionId())) {
                    games.put(game.getSessionId(), new ArrayList<>());
                }
                games.get(game.getSessionId()).add(game);
            }
        }
    }



}
