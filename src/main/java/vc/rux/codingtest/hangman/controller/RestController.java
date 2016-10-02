package vc.rux.codingtest.hangman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.misc.Utils;
import vc.rux.codingtest.hangman.repository.GameRepository;
import vc.rux.codingtest.hangman.service.GameService;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/data")
public class RestController {

    private GameRepository gameRepository;
    private GameService gameService;

    @Autowired
    public RestController(GameRepository gameRepository, GameService gameService) {
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
        private Set<Character> wrong = new HashSet<>();
        private List<Character> word = new ArrayList<>();
        private Long gameId;
        private List<Character> guesses = new ArrayList<>();

        public GameState(Game game) {
            gameId = game.getId();

            guesses = Utils.stringToList(game.getChars());
            List<Character> word = Utils.stringToList(game.getWord());
            Set<Character> correct = new HashSet<>();
            for (char ch : guesses) {
                if (!word.contains(ch)) {
                    wrong.add(ch);
                } else {
                    correct.add(ch);
                }
            }
            System.out.println(game.getWord());
            System.out.println(word);
            System.out.println(correct);
            System.out.println(wrong);

            this.word = word.stream()
                    .map(c -> correct.contains(c) ? c : '_')
                    .collect(Collectors.toList());
        }

        public Set<Character> getWrong() {
            return wrong;
        }

        public List<Character> getWord() {
            return word;
        }

        public Long getGameId() {
            return gameId;
        }

        public List<Character> getGuesses() {
            return guesses;
        }
    }



}
