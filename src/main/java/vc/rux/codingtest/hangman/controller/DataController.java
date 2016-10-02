package vc.rux.codingtest.hangman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.misc.Utils;
import vc.rux.codingtest.hangman.repository.GameRepository;
import vc.rux.codingtest.hangman.service.GameService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class DataController {

    private GameRepository gameRepository;
    private GameService gameService;

    @Autowired
    public DataController(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }


    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public GameState gameState(HttpSession session) {
        Game game = gameService.getOrCreateGame(session.getId());
        return new GameState(game);
    }


    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public GameState guess(HttpSession session, @RequestBody NewGuessRequest req) {
        Game game = gameService.onChar(session.getId(), req.character);
        return new GameState(game);
    }

    static final class NewGuessRequest {
        public char character;

        public NewGuessRequest() {
        }

        NewGuessRequest(char character) {
            this.character = character;
        }
    }

    /**
     * Game State DTO
     */
    static final class GameState {
        public Set<Character> wrong = new HashSet<>();
        public List<Character> word = new ArrayList<>();
        public Long gameId;
        public List<Character> guesses = new ArrayList<>();
        public Boolean finished = false;
        public Boolean won = false;
        public String answer;

        public GameState(Game game) {
            gameId = game.getId();
            won = game.getWon();
            finished = game.isFinished();

            guesses = Utils.stringToList(game.getChars());
            List<Character> wordChars = Utils.stringToList(game.getWord());


            Set<Character> correct = new HashSet<>();
            for (char ch : guesses) {
                if (!wordChars.contains(ch)) {
                    wrong.add(ch);
                } else {
                    correct.add(ch);
                }
            }

            word = wordChars.stream()
                    .map(c -> correct.contains(c) ? c : '_')
                    .collect(Collectors.toList());

            answer = finished ? game.getWord() : null;
        }

    }



}
